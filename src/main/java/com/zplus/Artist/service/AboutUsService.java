package com.zplus.Artist.service;

import com.zplus.Artist.dto.req.AboutUsReqDto;
import com.zplus.Artist.model.AboutUsMaster;

import java.util.List;

public interface AboutUsService {
    Boolean create(AboutUsReqDto aboutUsReqDto);

    Boolean update(AboutUsReqDto aboutUsReqDto);

    AboutUsMaster getByAboutUsId(Integer aboutUsId);

    List getAllAboutUs();

    List activeAboutUs();
}
