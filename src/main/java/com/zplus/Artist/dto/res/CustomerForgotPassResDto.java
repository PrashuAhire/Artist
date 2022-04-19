package com.zplus.Artist.dto.res;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CustomerForgotPassResDto {

    private Integer customerId;

    private Boolean flag;

    private Integer otp;
}
