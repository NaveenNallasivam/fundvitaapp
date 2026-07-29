package com.example.fundvita.service;

import com.example.fundvita.dto.StockDto;
import com.example.fundvita.dto.StockMetricsResult;
import com.example.fundvita.entity.impl.StockImpl;

public interface StockService {
    StockMetricsResult getStockByCode(String code);
//    StockImpl getSolrStockData(String code);
}
