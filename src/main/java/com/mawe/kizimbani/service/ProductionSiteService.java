package com.mawe.kizimbani.service;

import com.mawe.kizimbani.dao.ProductionSiteRepository;
import com.mawe.kizimbani.entity.ProductionSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionSiteService {

    @Autowired
    private ProductionSiteRepository productionSiteRepository;

    public List<ProductionSite> getAllProductionSites() {
        return productionSiteRepository.findAll();
    }

    public Optional<ProductionSite> getSiteById(Long id) {
        return productionSiteRepository.findById(id);
    }

    public ProductionSite saveProductionSite(ProductionSite productionSite) {
        return productionSiteRepository.save(productionSite);
    }

    public ProductionSite updateProductionSite(Long id, ProductionSite updatedSite) {
        return productionSiteRepository.findById(id)
                .map(site -> {
                    site.setSiteName(updatedSite.getSiteName());
                    site.setTitle(updatedSite.getTitle());
                    site.setAreaImage(updatedSite.getAreaImage());
                    return productionSiteRepository.save(site);
                })
                .orElseGet(() -> {
                    updatedSite.setId(id);
                    return productionSiteRepository.save(updatedSite);
                });
    }

    public void deleteProductionSite(Long id) {
        productionSiteRepository.deleteById(id);
    }
}


