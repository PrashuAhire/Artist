package com.zplus.Artist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter@Setter@Entity
@Table(name = "about_us_master")
public class AboutUsMaster extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer aboutUsId;

    @Column(length = 1000)
    private String aboutUsDescription;

    @Column(length = 500)
    private String aboutUSHeading;

    @Column(length = 500)
    private String aboutImage;

    @Column(length = 15)
    private String status;

    @Column(length = 1000)
    private String aboutUsMissionDescription;

    @Column(length = 1000)
    private String aboutUsMissionHeading;

    @Column(length = 1000)
    private String aboutUsMissionImage;


    @Column(length = 1000)
    private String aboutUSVisionHeading;

    @Column(length = 1000)
    private String aboutUSVisionDescription;


    @Column(length = 1000)
    private String aboutUSVisionImage;

}
