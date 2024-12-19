package com.jiangdk.pms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiangdk.pms.pojo.entity.Spu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangdk.pms.pojo.form.SpuForm;
import com.jiangdk.pms.pojo.query.SpuPageQuery;
import com.jiangdk.pms.pojo.vo.SpuVO;

public interface SpuService extends IService<Spu>{

    /**
     * 根据spuId获取商品详情
     * @param spuId
     * @return
     */
    SpuVO getSpuById(Long spuId);
    /**
     * 商品分页查询
     * @param query
     * @return
     */
    IPage<SpuVO> spuPage(SpuPageQuery query);

    /**
     * 新增商品
     * @param spuForm
     * @return
     */
    void addSpu(SpuForm spuForm);
    /**
     * 更新商品
     * @param spuForm
     * @return
     */
    void updateSpuById(SpuForm spuForm);
    /**
     * 删除商品
     */
    void deleteSpuById(Long spuId);
}
