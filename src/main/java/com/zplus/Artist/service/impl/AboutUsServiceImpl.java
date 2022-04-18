package com.zplus.Artist.service.impl;

import com.zplus.Artist.dao.AboutUsDao;
import com.zplus.Artist.dto.req.AboutUsReqDto;
import com.zplus.Artist.model.AboutUsMaster;
import com.zplus.Artist.service.AboutUsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AboutUsServiceImpl implements AboutUsService {

    @Autowired
    private AboutUsDao aboutUsDao;

    @Override
    public Boolean create(AboutUsReqDto aboutUsReqDto) {
        AboutUsMaster aboutUsMaster=new AboutUsMaster();
        BeanUtils.copyProperties(aboutUsReqDto,aboutUsMaster);
        try {
            aboutUsDao.save(aboutUsMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(AboutUsReqDto aboutUsReqDto) {
        AboutUsMaster aboutUsMaster=new AboutUsMaster();
        aboutUsMaster.setDateCreated(aboutUsReqDto.getCreatedAt());
        aboutUsMaster.setAboutUsId(aboutUsReqDto.getAboutUsId());
        BeanUtils.copyProperties(aboutUsReqDto,aboutUsMaster);
        try {
            aboutUsDao.save(aboutUsMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public AboutUsMaster getByAboutUsId(Integer aboutUsId) {
        AboutUsMaster aboutUsMaster=new AboutUsMaster();
        try {
            Optional<AboutUsMaster> aboutUsMaster1=aboutUsDao.findById(aboutUsId);
            aboutUsMaster1.ifPresent(settingMaster -> BeanUtils.copyProperties(settingMaster, aboutUsMaster));
            return aboutUsMaster;
        }
        catch (Exception e) {
            e.printStackTrace();
            return aboutUsMaster;
        }
    }

    @Override
    public List getAllAboutUs() {
        return (List) aboutUsDao.findAll();
    }

    @Override
    public List activeAboutUs() {
        return (List) aboutUsDao.findAllByStatus("Active");

    }
}
