package com.zplus.Artist.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CustomerValidateOtpReqDto {

    private Integer customerId;

    private Integer otp;
}
