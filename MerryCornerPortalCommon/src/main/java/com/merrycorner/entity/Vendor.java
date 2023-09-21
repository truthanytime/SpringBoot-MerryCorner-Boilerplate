package com.merrycorner.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String businessEmail;

    @ManyToOne
    @JoinColumn(name = "vendor_category_id")
    private VendorCategory vendorCategory;

    private String legalFirstName;
    private String legalMiddleName;
    private String legalLastName;
    private String mobileNumber;
    private String companyName;

    @ManyToOne // Corrected relationship annotation
    @JoinColumn(name = "address_id")
    private Address address;

    private Boolean acceptedAgreementFlag;
    private Boolean applicationApprovedFlag;

    // Other fields, getters and setters
}
