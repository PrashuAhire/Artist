package com.zplus.Artist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter@Setter@Entity
@Table(name = "banner_master")
public class BannerMaster extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer bannerId;

    @Column(length = 200)
    private String bannerName;

    @Column(length = 500)
    private String bannerDescription;

    @Column(length = 200)
    private String bannerType;

    @Column(length = 200)
    private String bannerPath;

    @Column(length = 10)
    private String status;

    @Column
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Date  enquiryDate;
}
