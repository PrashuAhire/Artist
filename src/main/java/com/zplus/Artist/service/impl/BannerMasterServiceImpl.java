package com.zplus.Artist.service.impl;

import com.zplus.Artist.dao.BannerDao;
import com.zplus.Artist.dto.req.BannerMasterReqDto;
import com.zplus.Artist.dto.res.BannerMasterResDto;
import com.zplus.Artist.model.BannerMaster;
import com.zplus.Artist.service.BannerMasterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerMasterServiceImpl implements BannerMasterService {

    @Autowired
    private BannerDao bannerDao;

    @Override
    public Boolean createBannerMaster(BannerMasterReqDto bannerMasterReqDto) {
        BannerMaster bannerMaster = new BannerMaster();

        BeanUtils.copyProperties(bannerMasterReqDto, bannerMaster);
        try {
            bannerDao.save(bannerMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateBannerMaster(BannerMasterReqDto bannerMasterReqDto) {
        BannerMaster bannerMaster = new BannerMaster();
        bannerMaster.setDateCreated(bannerMasterReqDto.getCreatedAt());
        bannerMaster.setBannerId(bannerMasterReqDto.getBannerId());
        BeanUtils.copyProperties(bannerMasterReqDto, bannerMaster);
        try {
            bannerDao.save(bannerMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List getActiveBannerMaster() {
        List list = bannerDao.findAllByStatus("Active");
        return list;
    }

    @Override
    public List getAllBannerMasterList() {
        return (List) bannerDao.findAll();
    }

    @Override
    public BannerMasterResDto editBannerMaster(Integer bannerId) {
        BannerMasterResDto bannerMasterResDto = new BannerMasterResDto();

        try {
            BannerMaster bannerMaster = null;
            java.util.Optional<BannerMaster> optionalBannerMasterMaster = bannerDao.findById(bannerId);
            if (optionalBannerMasterMaster.isPresent()) {
                bannerMaster = optionalBannerMasterMaster.get();
            }

            BeanUtils.copyProperties(bannerMaster, bannerMasterResDto);
            return bannerMasterResDto;
        } catch (Exception e) {
            e.printStackTrace();
            return bannerMasterResDto;
        }
    }
}
