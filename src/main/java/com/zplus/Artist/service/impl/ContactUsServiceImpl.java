package com.zplus.Artist.service.impl;

import com.zplus.Artist.dao.ContactUsDao;
import com.zplus.Artist.dto.req.ContactUsMasterReqDto;
import com.zplus.Artist.model.ContactUsMaster;
import com.zplus.Artist.service.ContactUsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    @Autowired
    private ContactUsDao contactUsDao;

    @Override
    public Boolean saveContact(ContactUsMasterReqDto contactUsMasterReqDto) {
        ContactUsMaster contactUsMaster=new ContactUsMaster();
        BeanUtils.copyProperties(contactUsMasterReqDto,contactUsMaster);
        try {
            contactUsDao.save(contactUsMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateContact(ContactUsMasterReqDto contactUsMasterReqDto) {
        ContactUsMaster contactUsMaster=new ContactUsMaster();
        contactUsMaster.setDateCreated(contactUsMasterReqDto.getCreatedAt());
        contactUsMaster.setContactUsId(contactUsMasterReqDto.getContactUsId());
        BeanUtils.copyProperties(contactUsMasterReqDto,contactUsMaster);
        try {
            contactUsDao.save(contactUsMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ContactUsMaster getByContactUsId(Integer contactUsId) {
        ContactUsMaster contactUsMaster=new ContactUsMaster();
        try {
            Optional<ContactUsMaster> contactUsMaster1=contactUsDao.findById(contactUsId);
            contactUsMaster1.ifPresent(settingMaster -> BeanUtils.copyProperties(settingMaster, contactUsMaster));
            return contactUsMaster;
        }
        catch (Exception e) {
            e.printStackTrace();
            return contactUsMaster;
        }
    }

    @Override
    public List getAllListOfContactMaster() {
        return (List) contactUsDao.findAll();
    }
}
