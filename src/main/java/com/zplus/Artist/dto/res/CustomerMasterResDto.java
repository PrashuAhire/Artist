package com.zplus.Artist.dto.res;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class CustomerMasterResDto {
    private Integer customerId;

    private String customerName;

    private String customerMobNo;

    private String customerMailId;

    private String customerAddress;

    private String status;

    private String password;

    private Integer otp;

    private Integer responseCode;

    private String message;

    private Date createdAt=new Date();

    private Date updatedAt=new Date();
}
