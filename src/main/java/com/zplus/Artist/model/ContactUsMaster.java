package com.zplus.Artist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "contact_us_master")
public class ContactUsMaster extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer contactUsId;

    @Column(length = 2000)
    private String contactAddress;

    @Column(length = 20,nullable = false,unique = true)
    private String mobileNo;

    @Column(length = 50,unique = true,nullable = false)
    private String contactEmail;

    @Column(length = 500)
    private String contactUsPersonName;

    @Column(length = 1000)
    private String message;
}
