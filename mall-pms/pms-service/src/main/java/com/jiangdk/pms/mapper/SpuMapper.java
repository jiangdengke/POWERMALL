package com.jiangdk.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiangdk.pms.pojo.entity.Spu;
import com.jiangdk.pms.pojo.query.SpuPageQuery;
import com.jiangdk.pms.pojo.vo.SpuVO;
import org.apache.ibatis.annotations.Param;

public interface SpuMapper extends BaseMapper<Spu> {
    /**
     * 商品详情
     * @param spuId
     * @return
     */
    SpuVO selectSpuById(@Param("spuId") Long spuId);
    /**
     * 商品分页
     * @param page
     * @param query
     * @return
     */
    IPage<SpuVO> selectSpuPage(IPage<SpuVO> page, @Param("param") SpuPageQuery query);
}