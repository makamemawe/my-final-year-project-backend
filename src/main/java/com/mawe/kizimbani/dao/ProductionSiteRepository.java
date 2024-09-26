package com.mawe.kizimbani.dao;

import com.mawe.kizimbani.entity.ProductionSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionSiteRepository extends JpaRepository<ProductionSite, Long> {
}
