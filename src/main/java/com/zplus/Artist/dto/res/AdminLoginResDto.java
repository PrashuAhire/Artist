package com.zplus.Artist.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class AdminLoginResDto {
    private Integer adminId;
    private String adminName;
    private String mobileNo;
    private String emailId;
    private String address;
    private String status;
    @JsonIgnore
    private String password;
    private Integer responseCode;

    private String message;
}
