package com.mawe.kizimbani.entity;

import javax.persistence.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ProductionMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phone;
    private String fieldLevel;
    private String registrationNumber;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB") // Use MEDIUMBLOB for larger images
    private byte[] profileImage;

    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false)
    private ProductionSite productionSite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFieldLevel() {
        return fieldLevel;
    }

    public void setFieldLevel(String fieldLevel) {
        this.fieldLevel = fieldLevel;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public ProductionSite getProductionSite() {
        return productionSite;
    }

    public void setProductionSite(ProductionSite productionSite) {
        this.productionSite = productionSite;
    }
}

