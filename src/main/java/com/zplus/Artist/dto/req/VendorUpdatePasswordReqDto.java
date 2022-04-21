package com.zplus.Artist.dto.req;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VendorUpdatePasswordReqDto {

    private Integer vendorId;
    private String vendorPassword;
}
