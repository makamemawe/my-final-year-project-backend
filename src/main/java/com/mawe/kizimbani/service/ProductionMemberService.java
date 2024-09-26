package com.mawe.kizimbani.service;

import com.mawe.kizimbani.dao.ProductionMemberRepository;
import com.mawe.kizimbani.entity.ProductionMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionMemberService {

    @Autowired
    private ProductionMemberRepository productionMemberRepository;

    public List<ProductionMember> getAllProductionMembers() {
        return productionMemberRepository.findAll();
    }

    public Optional<ProductionMember> getProductionMemberById(Long id) {
        return productionMemberRepository.findById(id);
    }

    public ProductionMember saveProductionMember(ProductionMember productionMember) {
        return productionMemberRepository.save(productionMember);
    }

    public ProductionMember updateProductionMember(Long id, ProductionMember updatedMember) {
        return productionMemberRepository.findById(id)
                .map(member -> {
                    member.setFullName(updatedMember.getFullName());
                    member.setEmail(updatedMember.getEmail());
                    member.setPhone(updatedMember.getPhone());
                    member.setFieldLevel(updatedMember.getFieldLevel());
                    member.setRegistrationNumber(updatedMember.getRegistrationNumber());
                    member.setProfileImage(updatedMember.getProfileImage());
                    member.setProductionSite(updatedMember.getProductionSite());
                    return productionMemberRepository.save(member);
                })
                .orElseGet(() -> {
                    updatedMember.setId(id);
                    return productionMemberRepository.save(updatedMember);
                });
    }

    public void deleteProductionMember(Long id) {
        productionMemberRepository.deleteById(id);
    }
}
