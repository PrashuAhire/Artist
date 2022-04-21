package com.zplus.Artist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "vendor")
public class Vendor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 20)
    private String vendorFirstName;
    @Column(length = 20)
    private String vendorMiddleName;
    @Column(length = 20)
    private String vendorLastName;
    @Column(length = 50,nullable = false,unique = true)
    private String vendorEmail;
    @Column(length = 20 ,nullable = false,unique = true)
    private String vendorMoblieNo;
    @Column(length = 15)
    private String vendorPassword;
    @Column(length = 100)
    private String vendorAddress;
    @Column(length = 15)
    private String vendorStatus;
    @Column(length = 30)
    private String vendorAcNo;
    @Column(length = 12)
    private String vendorPan;
    @Column(length = 16)
    private String vendorAdharCard;
    @Column(length = 12)
    private String vendorShopPan;

    private String vendorRegdate;
    private String vendorToDate;
    @Column(length = 100)
    private String amountBalance;
    @Column(length = 8)
    private Integer Otp;

    private String photo;

}