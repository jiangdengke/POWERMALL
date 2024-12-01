package com.jiangdk.pms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiangdk.common.exception.BizException;
import com.jiangdk.oss.feign.MinioFeignClient;
import com.jiangdk.pms.pojo.entity.Spu;
import com.jiangdk.pms.pojo.form.CategoryForm;
import com.jiangdk.pms.service.CategoryService;
import com.jiangdk.pms.service.SkuService;
import com.jiangdk.pms.service.SpuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangdk.pms.mapper.CategoryMapper;
import com.jiangdk.pms.pojo.entity.Category;
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private SpuService spuService;
    @Autowired
    private MinioFeignClient minioFeignClient;
    /**
     * 获取商品分类树
     */
    public List<Tree<Long>> tree(){
        // 构建数节点的集合。
        List<TreeNode<Long>> nodeList = CollUtil.newArrayList();
       this.list().forEach(category -> {
           TreeNode treeNode = new TreeNode<>(category.getId(),
                   category.getParentId(),
                   category.getName(),
                   category.getWeight());
           // 添加扩展属性
           treeNode.setExtra(MapUtil.of("icon",category.getIcon()));
           nodeList.add(treeNode);
       });

        return TreeUtil.build(nodeList,0L);
    }

    /**
     * 新增商品分类
     * @param categoryForm
     */

    @Override
    public void addCategory(CategoryForm categoryForm) {
        Category category = new Category();
        setLevel(categoryForm,category);
        // 保存商品分类数据
        this.save(category);
    }

    /**
     * 更新商品分类
     *
     * @param categoryForm
     */
    @Override
    public void updateCategoryById(CategoryForm categoryForm) {
        Category category = this.getById(categoryForm.getId());
        if (category == null){
            throw new BizException(HttpStatus.HTTP_NOT_FOUND,"商品分类信息不存在");
        }
        setLevel(categoryForm,category);
        this.updateById(category);
    }

    /**
     * 删除商品分类
     *
     * @param categoryId
     */
    @Override
    public void deleteCategoryById(Long categoryId) {
        Category category = this.getById(categoryId);
        if (category == null){
            throw new BizException(HttpStatus.HTTP_NOT_FOUND,"商品分类不存在或已删除");
        }
        // 判断该分类是否有子分类
        boolean exists = this.baseMapper.exists(new LambdaQueryWrapper<Category>().eq(Category::getParentId, categoryId));
        if (exists){
            throw new BizException(HttpStatus.HTTP_BAD_REQUEST,"该分类下有子分类不能删除");
        }
        // 判断该分类下是否有商品
        QueryWrapper<Spu> spuQueryWrapper = new QueryWrapper<>();
        spuQueryWrapper.eq("category_id",categoryId);
        List<Spu> list = spuService.list(spuQueryWrapper);
        if (!list.isEmpty()){
            throw new BizException(HttpStatus.HTTP_BAD_REQUEST,"该分类下有商品");
        }
        this.removeById(categoryId);
        // 删除商品分类的图标
        minioFeignClient.removeFile("mall",category.getIcon().substring(category.getIcon().lastIndexOf("/")+1));
    }

    /**
     * 设置商品层级
     * @param categoryForm
     * @param category
     */
    private void setLevel(CategoryForm categoryForm,Category category){
        BeanUtils.copyProperties(categoryForm,category);
        if(category.getParentId() ==0){
            category.setLevel(1);
        }else {
            // 判断上级分类是否存在
            Category parent = this.getById(category.getParentId());
            if (parent == null){
                throw new BizException(HttpStatus.HTTP_NOT_FOUND,"上级分类ID不存在");
            }
            // 计算分类的层级
            category.setLevel(parent.getLevel()+1);
        }
    }
}
