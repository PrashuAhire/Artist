package com.zplus.Artist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "category_master")
public class CategoryMaster extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(length = 500)
    private String categoryName;

    @Column(length = 50)
    private String categoryStatus;

    @Column(length = 500)
    private String categoryImage;

    @Column(length = 3000)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="adminId")
    private AdminMaster adminMaster;
}
