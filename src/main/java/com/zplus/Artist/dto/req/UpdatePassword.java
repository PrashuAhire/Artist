package com.zplus.Artist.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class  UpdatePassword {

    private Integer adminId;
    private String password;
}
