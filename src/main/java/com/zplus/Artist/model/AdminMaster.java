package com.zplus.Artist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "admin_Master")
public class AdminMaster extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer adminId;

    @Column(length = 100)
    private String adminName;

    @Column(length = 20,nullable = false,unique = true)
    private String mobileNo;

    @Column(length = 50,nullable = false,unique = true)
    private String emailId;

    @Column(length = 500)
    private String address;

    @Column(length = 20)
    private String status;

    @Column(length = 25)
    private String password;

    @Column(length = 4)
    private Integer otp;
}
