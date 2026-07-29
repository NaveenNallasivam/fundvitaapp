package com.example.fundvita.dto;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * DTO to hold the result of the fetchStockMetrics query.
 * This provides type-safe mapping instead of using Map<String, Object>
 */
@Getter
public class StockMetricsResult {
    // Stock data
    private final Long stockUidpk;
    private final String code;
    private final String description;
    private final BigDecimal currentPrice;

    // Stock DE metrics
    private final Long deUidpk;
    private final BigDecimal deValue;

    // Stock Markcap metrics
    private final Long markcapUidpk;
    private final BigDecimal markcapValue;

    // Stock PB metrics
    private final Long pbUidpk;
    private final BigDecimal pbValue;

    // Stock PE metrics
    private final Long peUidpk;
    private final BigDecimal peValue;

    // Constructor
    public StockMetricsResult(
            Long stockUidpk, String code, String description, BigDecimal currentPrice,
            Long deUidpk, BigDecimal deValue,
            Long markcapUidpk, BigDecimal markcapValue,
            Long pbUidpk, BigDecimal pbValue,
            Long peUidpk, BigDecimal peValue) {
        this.stockUidpk = stockUidpk;
        this.code = code;
        this.description = description;
        this.currentPrice = currentPrice;
        this.deUidpk = deUidpk;
        this.deValue = deValue;
        this.markcapUidpk = markcapUidpk;
        this.markcapValue = markcapValue;
        this.pbUidpk = pbUidpk;
        this.pbValue = pbValue;
        this.peUidpk = peUidpk;
        this.peValue = peValue;
    }

}

