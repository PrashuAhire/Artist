package com.zplus.Artist.dto.req;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter@Setter
public class BannerMasterReqDto {

    private Integer bannerId;

    private String bannerName;

    private String bannerDescription;

    private String bannerType;

    private String bannerPath;

    private String status;

    private Date startDate;

    private Date endDate;

    private Date createdAt=new Date();

    private Date updatedAt=new Date();
}
