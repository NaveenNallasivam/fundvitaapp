package com.example.fundvita.entity;

import java.math.BigDecimal;

public interface Stock {
    Long getUidpk();
    void setUidpk(Long uidpk);

    String getCode();
    void setCode(String code);

    String getDescription();
    void setDescription(String description);

    public BigDecimal getCurrentPrice();

    public void setCurrentPrice(BigDecimal currentPrice);
}
