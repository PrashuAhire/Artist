package com.zplus.Artist.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CategoryResDto {

    private Integer categoryId;

    private String categoryName;

    private String categoryStatus;

    private String categoryImage;

    private String description;

    private Integer adminId;

    private Date createdAt=new Date();

    private Date updatedAt=new Date();

    public CategoryResDto() {

    }
}
