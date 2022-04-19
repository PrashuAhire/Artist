package com.zplus.Artist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "customer_master")
public class CustomerMaster extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @Column(length = 500)
    private String customerName;

    @Column(length = 20,unique = true, nullable = false)
    private String customerMobNo;

    @Column(length = 50,unique = true, nullable = false)
    private String customerMailId;

    @Column(length = 1000)
    private String customerAddress;

    @Column(length = 20)
    private String status;

    @Column(length = 50)
    @NotNull
    private String password;

    @Column(length = 4)
    private Integer otp;
}
