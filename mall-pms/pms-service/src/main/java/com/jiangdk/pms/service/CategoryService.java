package com.jiangdk.pms.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.map.MapUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangdk.pms.mapper.CategoryMapper;
import com.jiangdk.pms.pojo.entity.Category;
@Service
public class CategoryService extends ServiceImpl<CategoryMapper, Category> {
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
}
