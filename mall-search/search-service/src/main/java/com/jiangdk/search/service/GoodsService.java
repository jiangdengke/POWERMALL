package com.jiangdk.search.service;

import com.jiangdk.search.document.GoodsDoc;
import com.jiangdk.search.pojo.page.PageResult;
import com.jiangdk.search.pojo.query.GoodsQuery;

public interface GoodsService {
    /**
     * 查询
     * @param param
     * @return
     */
    PageResult<GoodsDoc> search(GoodsQuery param);
    
    
    /**
     * 保存文档到搜索引擎
     * @param goodsDoc
     * @return
     */
    void save(GoodsDoc goodsDoc);
    
    /**
     * 根据id删除文档
     * @param docId
     */
    void deleteById(Long docId);
}