package com.example.fundvita.repository;

import com.example.fundvita.dto.StockMetricsResult;
import com.example.fundvita.entity.impl.StockImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StockRepository extends JpaRepository<StockImpl, Long> {
    Optional<StockImpl> findByCode(String code);

    /**
     * Fetch all stock metrics (DE, PB, PE, MarketCap) for a given stock UID.
     * Uses LEFT JOINs to handle cases where metrics may not exist.
     * Returns type-safe StockMetricsResult instead of Map<String, Object>
     *
     * @param stockUidpk The unique ID of the stock
     * @return Optional containing StockMetricsResult with all metrics
     */
    @Query("SELECT new com.example.fundvita.dto.StockMetricsResult(" +
            "s.uidpk, " +
            "s.code, " +
            "s.description, " +
            "s.currentPrice, " +
            "de.uidpk, " +
            "de.value, " +
            "mk.uidpk, " +
            "mk.value, " +
            "pb.uidpk, " +
            "pb.value, " +
            "pe.uidpk, " +
            "pe.value) " +
            "FROM StockImpl s " +
            "LEFT JOIN StockDeImpl de ON s.uidpk = de.stock.uidpk " +
            "LEFT JOIN StockMarkcapImpl mk ON s.uidpk = mk.stock.uidpk " +
            "LEFT JOIN StockPbImpl pb ON s.uidpk = pb.stock.uidpk " +
            "LEFT JOIN StockPeImpl pe ON s.uidpk = pe.stock.uidpk " +
            "WHERE s.uidpk = :stockUidpk")
    Optional<StockMetricsResult> fetchStockMetrics(@Param("stockUidpk") Long stockUidpk);
}
