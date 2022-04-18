package com.zplus.Artist.dto.req;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AboutUsReqDto {
    private Integer aboutUsId;

    private String aboutUsDescription;

    private String aboutUSHeading;

    private String aboutImage;

    private String status;

    private String aboutUsMissionDescription;

    private String aboutUsMissionHeading;

    private String aboutUsMissionImage;

    private String aboutUSVisionHeading;

    private String aboutUSVisionDescription;

    private String aboutUSVisionImage;

    private Date createdAt=new Date();

    private Date updatedAt=new Date();

}
