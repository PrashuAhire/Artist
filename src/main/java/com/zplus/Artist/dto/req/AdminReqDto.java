package com.zplus.Artist.dto.req;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AdminReqDto {

    private Integer adminId;
    private String adminName;
    private String mobileNo;
    private String emailId;
    private String address;
    private String status;
    private String password;
    private Date createdAt=new Date();
    private Date updatedAt=new Date();
}
