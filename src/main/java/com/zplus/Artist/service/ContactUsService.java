package com.zplus.Artist.service;

import com.zplus.Artist.dto.req.ContactUsMasterReqDto;
import com.zplus.Artist.model.ContactUsMaster;

import java.util.List;

public interface ContactUsService {
    Boolean saveContact(ContactUsMasterReqDto contactUsMasterReqDto);

    Boolean updateContact(ContactUsMasterReqDto contactUsMasterReqDto);

    ContactUsMaster getByContactUsId(Integer contactUsId);

    List getAllListOfContactMaster();
}
