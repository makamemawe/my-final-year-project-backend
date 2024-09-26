package com.mawe.kizimbani.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "production_sites")
public class ProductionSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String siteName;
    private String title;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB") // Use MEDIUMBLOB for larger images
    private byte[] areaImage;

    @OneToMany(mappedBy = "productionSite", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductionMember> productionMembers;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getAreaImage() {
        return areaImage;
    }

    public void setAreaImage(byte[] areaImage) {
        this.areaImage = areaImage;
    }

    public List<ProductionMember> getProductionMembers() {
        return productionMembers;
    }

    public void setProductionMembers(List<ProductionMember> productionMembers) {
        this.productionMembers = productionMembers;
    }
}
