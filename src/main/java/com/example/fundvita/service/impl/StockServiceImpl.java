package com.example.fundvita.service.impl;

import com.example.fundvita.dto.StockMetricsResult;
import com.example.fundvita.entity.impl.StockImpl;
//import com.example.fundvita.solr.repository.SolrStockRepository;
import com.example.fundvita.repository.StockRepository;
import com.example.fundvita.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class StockServiceImpl implements StockService {

    private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private StockRepository stockRepository;

//    @Autowired
//    private SolrStockRepository solrStockRepository;

    /**
     * Fetch stock data by code with all metrics (DE, PB, PE, MarketCap).
     *
     * @param code The stock code
     * @return StockMetricsResult with all metrics, or null if stock not found
     * @throws IllegalArgumentException if code is empty or null
     */
    @Override
    public StockMetricsResult getStockByCode(String code) {
        // Input validation
        if (!StringUtils.hasText(code)) {
            logger.warn("Stock code is null or empty");
            throw new IllegalArgumentException("Stock code cannot be null or empty");
        }

        String trimmedCode = code.trim();
        logger.debug("Fetching stock data for code: {}", trimmedCode);

        try {
            StockImpl stock = stockRepository.findByCode(trimmedCode).orElse(null);
            if (stock == null) {
                logger.info("Stock not found for code: {}", trimmedCode);
                return null;
            }

            // Fetch all metrics in a single optimized query
            StockMetricsResult metrics = stockRepository.fetchStockMetrics(stock.getUidpk())
                    .orElse(null);

            if (metrics == null) {
                logger.warn("Stock metrics not found for code: {}", trimmedCode);
                return null;
            }

            logger.debug("Successfully fetched metrics for code: {}", trimmedCode);
            return metrics;
        } catch (Exception e) {
            logger.error("Error fetching stock data for code: {}", trimmedCode, e);
            throw new RuntimeException("Error fetching stock data", e);
        }
    }

//    @Override
//    public StockImpl getSolrStockData(String code) {
//        return solrStockRepository.findByCode(code).get();
//    }

}
