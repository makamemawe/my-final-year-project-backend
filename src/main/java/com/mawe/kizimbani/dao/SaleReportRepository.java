package com.mawe.kizimbani.dao;

import com.mawe.kizimbani.entity.SalesReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleReportRepository extends JpaRepository<SalesReport, Integer> {
}
