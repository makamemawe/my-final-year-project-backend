package com.mawe.kizimbani.dao;


import com.mawe.kizimbani.entity.ProductionMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionMemberRepository extends JpaRepository<ProductionMember, Long> {
}
