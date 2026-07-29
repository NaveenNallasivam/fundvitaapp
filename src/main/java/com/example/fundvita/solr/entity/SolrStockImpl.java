package com.example.fundvita.solr.entity;

import com.example.fundvita.entity.impl.StockImpl;
import jakarta.persistence.*;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.math.BigDecimal;
import java.util.Objects;

@SolrDocument(collection = "fund_stock")
public class SolrStockImpl extends StockImpl {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Indexed(name = "uidpk", type = "long")
    private Long uidpk;

    @Indexed(name = "code", type = "string")
    private String code;

    private String description;

    private BigDecimal currentPrice;

    @Override
    public Long getUidpk() {
        return uidpk;
    }

    @Override
    public void setUidpk(Long uidpk) {
        this.uidpk = uidpk;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    @Override
    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        SolrStockImpl stock = (SolrStockImpl) o;
        if (uidpk != null && stock.uidpk != null) {
            return Objects.equals(uidpk, stock.uidpk);
        }
        return Objects.equals(code, stock.code);
    }

    @Override
    public int hashCode() {
        if (uidpk != null) {
            return Objects.hash(uidpk);
        }
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "StockImpl{" +
                "uidpk=" + uidpk +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
