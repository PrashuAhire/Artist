package com.zplus.Artist.dto.req;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter@Setter
public class CustomerMasterReqDto {

    private Integer customerId;

    private String customerName;

    private String customerMobNo;

    private String customerMailId;

    private String customerAddress;

    private String status;

    private String password;

    private Integer otp;

    private Date createdAt=new Date();

    private Date updatedAt=new Date();
}
