package com.example.fundvita.dto;

import lombok.Getter;
import lombok.Setter;
//import org.springframework.data.solr.core.mapping.SolrDocument;

import java.math.BigDecimal;

@Setter
@Getter
//@SolrDocument(collection = "stocks")
public class StockDto {
    // Getters and Setters
    private Long stockUidpk;
    private String code;
    private String description;
    private BigDecimal currentPrice;

    // Stock DE (from StockDeImpl)
    private Long stockDeUidpk;
    private BigDecimal stockDeValue;

    // Stock Markcap (from StockMarkcapImpl)
    private Long stockMarkcapUidpk;
    private BigDecimal stockMarkcapValue;

    // Stock PB (from StockPbImpl)
    private Long stockPbUidpk;
    private BigDecimal stockPbValue;

    // Stock PE (from StockPeImpl)
    private Long stockPeUidpk;
    private BigDecimal stockPeValue;

    // Constructors
    public StockDto() {
    }

    public StockDto(Long stockUidpk, String code, String description, BigDecimal currentPrice) {
        this.stockUidpk = stockUidpk;
        this.code = code;
        this.description = description;
        this.currentPrice = currentPrice;
    }

}
