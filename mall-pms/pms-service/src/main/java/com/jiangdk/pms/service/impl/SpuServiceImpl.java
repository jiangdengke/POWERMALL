package com.jiangdk.pms.service.impl;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangdk.common.exception.BizException;
import com.jiangdk.oss.feign.MinioFeignClient;
import com.jiangdk.pms.dto.GoodsDTO;
import com.jiangdk.pms.mapper.SpuMapper;
import com.jiangdk.pms.pojo.entity.Category;
import com.jiangdk.pms.pojo.entity.Sku;
import com.jiangdk.pms.pojo.entity.Spu;
import com.jiangdk.pms.pojo.form.SpuForm;
import com.jiangdk.pms.pojo.query.SpuPageQuery;
import com.jiangdk.pms.pojo.vo.SpuVO;
import com.jiangdk.pms.service.CategoryService;
import com.jiangdk.pms.service.SkuService;
import com.jiangdk.pms.service.SpuService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {
    @Autowired
    private SkuService skuService;
    @Autowired
    @Lazy // 避免循环依赖
    private CategoryService categoryService;
    @Autowired
    private MinioFeignClient minioFeignClient;
    @Autowired
    private RabbitTemplate rabbitTemplate;



    /**
     * 根据spuId获取商品详情
     *
     * @param spuId
     * @return
     */
    @Override
    @Cacheable(cacheNames = "goods",key = "#spuId")
    public SpuVO getSpuById(Long spuId) {
        return this.baseMapper.selectSpuById(spuId);
    }

    /**
     * 商品分页查询「管理端」
     *
     */

    /**
     * 商品分页查询
     *
     * @param query
     * @return
     */
    @Override
    public IPage<SpuVO> spuPage(SpuPageQuery query) {
        return this.baseMapper.selectSpuPage(Page.of(query.getCurrent(), query.getSize()), query);
    }

    /**
     * 新增商品
     *
     * @param spuForm
     * @return
     */
    @Override
    @Transactional //需要保证插入两张表同时成功
    public void addSpu(SpuForm spuForm) {
        Spu spu = new Spu();
        BeanUtils.copyProperties(spuForm, spu);
        // 判断商品分类是否存在
        Category category = categoryService.getById(spuForm.getCategoryId());
        if(category == null){
            throw new BizException(HttpStatus.HTTP_BAD_REQUEST,"商品分类不存在");
        }
        this.save(spu);
        List<Sku> skuList = spuForm.getSkuList().stream().map(skuForm -> {
            Sku sku = new Sku();
            BeanUtils.copyProperties(skuForm, sku);
            // 设置spuId
            sku.setSpuId(spu.getId());
            return sku;
        }).collect(Collectors.toList());
        // 批量保存商品的sku
        skuService.saveBatch(skuList);
        // 将商品信息发送到mq
        GoodsDTO goodsDTO = new GoodsDTO();
        BeanUtils.copyProperties(spu,goodsDTO);
        rabbitTemplate.convertAndSend("pms.goods","pms.goods.save",goodsDTO,
                new CorrelationData(String.valueOf(spu.getId())));
    }

    /**
     * 更新商品
     *
     * @param spuForm
     * @return
     */
    @Override
    @Transactional
    @CacheEvict(cacheNames = "goods",key = "#spuForm.id") // 把对应id的缓存删掉
    public void updateSpuById(SpuForm spuForm) {
        // 判断商品是否存在
        Spu spu = this.getById(spuForm.getId());
        if (spu == null) {
            throw new BizException(HttpStatus.HTTP_NOT_FOUND, "商品不存在或已删除");
        }
        // 判断商品分类是否存在
        Category category = categoryService.getById(spuForm.getCategoryId());
        if(category == null){
            throw new BizException(HttpStatus.HTTP_BAD_REQUEST,"商品分类不存在");
        }
        BeanUtils.copyProperties(spuForm, spu);
        this.updateById(spu);
        // 处理商品sku的信息
        List<Sku> skuList = spuForm.getSkuList().stream().map(skuForm -> {
            Sku sku = new Sku();
            BeanUtils.copyProperties(skuForm, sku);
            // 设置spuId
            sku.setSpuId(spu.getId());
            return sku;
        }).collect(Collectors.toList());
        // 批量更新或新增
        skuService.saveOrUpdateBatch(skuList);
        // 将商品信息发送到mq
        GoodsDTO goodsDTO = new GoodsDTO();
        BeanUtils.copyProperties(spu,goodsDTO);
        rabbitTemplate.convertAndSend("pms.goods","pms.goods.update",goodsDTO,
                new CorrelationData(String.valueOf(spu.getId())));
    }

    /**
     * 删除商品
     *
     * @param spuId
     */
    @Override
    @Transactional
    @CacheEvict(cacheNames = "goods",key = "#spuId")
    public void deleteSpuById(Long spuId) {
        Spu spu = this.getById(spuId);
        if (spu == null) {
            throw new BizException(HttpStatus.HTTP_NOT_FOUND, "商品信息不存在或已删除");
        }
        // 删除spu
        this.removeById(spuId);
        // 删除sku
        skuService.remove(new LambdaQueryWrapper<Sku>().eq(Sku::getSpuId, spuId));
        // 删除商品对应的图片
        String filename = spu.getImg().substring(spu.getImg().lastIndexOf("/")+1);
        List<String> filenames = spu.getImgList().stream().map(url -> {
            return url.substring(url.lastIndexOf("/") + 1);
        }).collect(Collectors.toList());
        filenames.add(filename);
        // 批量删除
        minioFeignClient.removeFile("mall",filenames.toArray(new String[filenames.size()]));
        // 将商品id发送到mq
        rabbitTemplate.convertAndSend("pms.goods","pms.goods.del",spuId,
                new CorrelationData(String.valueOf(spu.getId())));
    }

    /**
     * 根据分类id获取对应商品
     * @param categoryId
     * @return
     */
    @Override
    public List<SpuVO> getSpuListByCategoryId(Long categoryId) {
        QueryWrapper<Spu> spuQueryWrapper = new QueryWrapper<>();
        spuQueryWrapper.eq("category_id",categoryId);
        List<Spu> spuList = this.list(spuQueryWrapper);
        if (spuList == null || spuList.isEmpty()){
            throw new BizException(HttpStatus.HTTP_NOT_FOUND,"该分类下没有商品");
        }
        List<SpuVO> spuVOList = spuList.stream().map(spu -> {
            SpuVO spuVO = new SpuVO();
            BeanUtils.copyProperties(spu, spuVO);
            return spuVO;
        }).collect(Collectors.toList());
        return spuVOList;
    }

    /**
     * 获取所有商品
     * @param o
     * @return
     */
    @Override
    public List<SpuVO> getSpuList() {
        List<Spu> list = this.list();
        List<SpuVO> spuVOList = list.stream().map(spu -> {
            SpuVO spuVO = new SpuVO();
            BeanUtils.copyProperties(spu, spuVO);
            return spuVO;
        }).collect(Collectors.toList());
        return spuVOList;
    }

    @Override
    public List<SpuVO> searchByName(String name) {
        QueryWrapper<Spu> spuQueryWrapper = new QueryWrapper<>();
        spuQueryWrapper.like("name", name);
        List<Spu> spuList = this.baseMapper.selectList(spuQueryWrapper);
        List<SpuVO> spuVOList = spuList.stream()
                .map(spu -> {
                    SpuVO spuVO = new SpuVO();
                    BeanUtils.copyProperties(spu, spuVO);
                    return spuVO;
                }).collect(Collectors.toList());
        return spuVOList;
    }
}
