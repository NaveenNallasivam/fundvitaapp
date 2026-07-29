package com.example.fundvita.solr.repository;

import com.example.fundvita.entity.impl.StockImpl;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.Optional;

public interface SolrStockRepository extends SolrCrudRepository<StockImpl, Long> {
    Optional<StockImpl> findByCode(String code);
}
