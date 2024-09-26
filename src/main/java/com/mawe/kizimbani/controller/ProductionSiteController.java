package com.mawe.kizimbani.controller;

import com.mawe.kizimbani.entity.ProductionSite;
import com.mawe.kizimbani.service.ProductionSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/production-sites")
public class ProductionSiteController {

    @Autowired
    private ProductionSiteService productionSiteService;

    @GetMapping
    public ResponseEntity<List<ProductionSite>> getAllProductionSites() {
        List<ProductionSite> sites = productionSiteService.getAllProductionSites();
        return new ResponseEntity<>(sites, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductionSite> getProductionSiteById(@PathVariable Long id) {
        Optional<ProductionSite> site = productionSiteService.getSiteById(id);
        return site.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ProductionSite> createProductionSite(
            @RequestParam("siteName") String siteName,
            @RequestParam("title") String title,
            @RequestParam("areaImage") MultipartFile areaImage
    ) throws IOException {
        ProductionSite site = new ProductionSite();
        site.setSiteName(siteName);
        site.setTitle(title);
        site.setAreaImage(areaImage.getBytes());

        ProductionSite savedSite = productionSiteService.saveProductionSite(site);
        return new ResponseEntity<>(savedSite, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductionSite> updateProductionSite(
            @PathVariable Long id,
            @RequestParam("siteName") String siteName,
            @RequestParam("title") String title,
            @RequestParam(value = "areaImage", required = false) MultipartFile areaImage
    ) throws IOException {
        ProductionSite site = new ProductionSite();
        site.setSiteName(siteName);
        site.setTitle(title);
        if (areaImage != null && !areaImage.isEmpty()) {
            site.setAreaImage(areaImage.getBytes());
        }

        ProductionSite updatedSite = productionSiteService.updateProductionSite(id, site);
        return new ResponseEntity<>(updatedSite, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductionSite(@PathVariable Long id) {
        productionSiteService.deleteProductionSite(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

