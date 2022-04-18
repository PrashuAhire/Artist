package com.zplus.Artist.service;

import com.zplus.Artist.dto.req.BannerMasterReqDto;
import com.zplus.Artist.dto.res.BannerMasterResDto;

import java.util.List;

public interface BannerMasterService {
    Boolean createBannerMaster(BannerMasterReqDto bannerMasterReqDto);

    Boolean updateBannerMaster(BannerMasterReqDto bannerMasterReqDto);

    List getActiveBannerMaster();

    List getAllBannerMasterList();

    BannerMasterResDto editBannerMaster(Integer bannerId);
}
