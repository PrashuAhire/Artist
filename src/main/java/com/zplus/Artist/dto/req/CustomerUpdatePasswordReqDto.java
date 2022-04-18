package com.zplus.Artist.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CustomerUpdatePasswordReqDto {

    private Integer customerId;
    private String password;
}
