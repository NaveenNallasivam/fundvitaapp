package com.example.fundvita.controller;

import com.example.fundvita.dto.ApiResponse;
import com.example.fundvita.dto.StockMetricsResult;
import com.example.fundvita.entity.impl.StockImpl;
import com.example.fundvita.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stockdata")
public class StockDataController {

    private static final Logger logger = LoggerFactory.getLogger(StockDataController.class);

    @Autowired
    StockService stockService;


    /**
     * Fetch stock data by stock code
     *
     * @param stockCode The stock code (e.g., "AAPL", "GOOGL")
     * @return ResponseEntity containing ApiResponse with StockMetricsResult or error message
     */
    @GetMapping(value = "/getStockData")
    public ResponseEntity<ApiResponse<StockMetricsResult>> getStockData(@RequestParam("stockCode") String stockCode) {
        try {
            logger.info("Received request to fetch stock data for code: {}", stockCode);

                StockMetricsResult stockData = stockService.getStockByCode(stockCode);

            if (stockData == null) {
                logger.warn("Stock not found for code: {}", stockCode);
                ApiResponse<StockMetricsResult> response = new ApiResponse<>(
                        false,
                        "Stock not found for code: " + stockCode,
                        "STOCK_NOT_FOUND"
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            logger.info("Successfully fetched stock data for code: {}", stockCode);
            ApiResponse<StockMetricsResult> response = new ApiResponse<>(
                    true,
                    "Stock data retrieved successfully",
                    stockData
            );
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            logger.warn("Invalid input for stock code: {}", stockCode, e);
            ApiResponse<StockMetricsResult> response = new ApiResponse<>(
                    false,
                    "Invalid stock code: " + e.getMessage(),
                    "INVALID_INPUT"
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        } catch (Exception e) {
            logger.error("Error fetching stock data for code: {}", stockCode, e);
            ApiResponse<StockMetricsResult> response = new ApiResponse<>(
                    false,
                    "An error occurred while fetching stock data",
                    "INTERNAL_SERVER_ERROR"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

//    @GetMapping("/search")
//    public StockImpl getStockSearchData(@RequestParam("stockCode") String stockCode){
//        return stockService.getSolrStockData(stockCode);
//    }
}
