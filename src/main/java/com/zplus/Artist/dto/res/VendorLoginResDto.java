package com.zplus.Artist.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorLoginResDto {


    private  Integer vendorId;
    private String vendorFirstName;
    private String vendorMiddleName;
    private String vendorLastName;
    private String vendorEmail;
    private String vendorMoblieNo;

    private String vendorAddress;
    private String vendorStatus;
    private String vendorAcNo;
    private String vendorPan;
    private String vendorAdharCard;
    private String vendorShopPan;
    private String vendorRegdate;
    private String vendorToDate;
    private String amountBalance;
    private Integer Oto;
    private String photo;
    @JsonIgnore
    private  String password;
    private Integer responseCode;
    private String message;
    private  String userName;


}
