package com.zplus.Artist.dto.req;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VenodrUpdatePassword {

    private Integer vendorId;
    private String password;
}
