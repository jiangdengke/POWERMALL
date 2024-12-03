package com.jiangdk.search.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.jiangdk.search.document.GoodsDoc;
import com.jiangdk.search.pojo.page.PageResult;
import com.jiangdk.search.pojo.query.GoodsQuery;
import com.jiangdk.search.repository.GoodsRepository;
import com.jiangdk.search.service.GoodsService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: JiangDk
 * @date: 2024/12/3 19:11
 * @description:
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private ElasticsearchRestTemplate restTemplate;
    @Autowired
    private GoodsRepository goodsRepository;

    /**
     * 查询
     *
     * @param param
     * @return
     */
    @Override
    public PageResult<GoodsDoc> search(GoodsQuery param) {
        String name = param.getName(); // 商品名称
        Long categoryId = param.getCategoryId(); // 商品分类ID
        Double gtePrice = param.getGtePrice(); // 最低价
        Double ltePrice = param.getLtePrice(); // 最高价
        String direction = param.getDirection(); // 排序方向
        String orderBy = param.getOrderBy(); // 排序字段
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (StrUtil.isBlank(name)){
            // 全文检索
            boolQueryBuilder.must(QueryBuilders.matchQuery("name",name));
        }
        if (categoryId != null){
            // 精准查询
            boolQueryBuilder.must(QueryBuilders.termQuery("categoryId",categoryId));
        }
        if (ObjUtil.isAllNotEmpty(gtePrice,ltePrice)){
            // 范围查询
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(gtePrice).lte(ltePrice));
        }
        Query query = new NativeSearchQuery(boolQueryBuilder);
        // 排序
        if (StrUtil.isAllNotBlank(orderBy,direction)){
            if (direction.equalsIgnoreCase("DESC")){
                query.addSort(Sort.by(Sort.Direction.DESC,orderBy));
            }else {
                query.addSort(Sort.by(Sort.Direction.ASC,orderBy));
            }
        }
        // 分页
        query.setPageable(PageRequest.of(param.getCurrent()-1,param.getSize()));
        SearchHits<GoodsDoc> searchHits = restTemplate.search(query, GoodsDoc.class);
        // 记录
        List<GoodsDoc> records = searchHits.getSearchHits().stream()
                .map(item -> item.getContent())
                .collect(Collectors.toList());
        // 总记录数
        long total = searchHits.getTotalHits();
        return PageResult.success(records,total);
    }

    /**
     * 保存文档到搜索引擎
     *
     * @param goodsDoc
     * @return
     */
    @Override
    public void save(GoodsDoc goodsDoc) {
        goodsRepository.save(goodsDoc);
    }

    /**
     * 根据id删除文档
     *
     * @param docId
     */
    @Override
    public void deleteById(Long docId) {
        goodsRepository.deleteById(docId);
    }
}
