package com.zplus.Artist.dto.req;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class ContactUsMasterReqDto {

    private Integer contactUsId;

    private String contactAddress;

    private String contactEmail;

    private String mobileNo;

    private String message;

    private String contactUsPersonName;

    private Date createdAt=new Date();

    private Date updatedAt=new Date();
}
