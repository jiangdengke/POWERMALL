package com.jiangdk.search.repository;

import com.jiangdk.search.document.GoodsDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GoodsRepository extends ElasticsearchRepository<GoodsDoc, Long> {

}