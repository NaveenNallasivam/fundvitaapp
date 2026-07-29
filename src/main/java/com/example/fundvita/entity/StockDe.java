package com.example.fundvita.entity;

import java.math.BigDecimal;

public interface StockDe {
    Long getUidpk();
    void setUidpk(Long uidpk);

    Long getStockuid();
    void setStockuid(Long stockuid);

    BigDecimal getValue();
    void setValue(BigDecimal value);
}

