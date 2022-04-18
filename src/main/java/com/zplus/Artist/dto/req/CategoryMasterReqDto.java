package com.zplus.Artist.dto.req;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class CategoryMasterReqDto {

    private Integer categoryId;

    private String categoryName;

    private String categoryStatus;

    private String categoryImage;

    private String description;

    private Integer adminId;

    private Date createdAt=new Date();

    private Date updatedAt=new Date();

}
