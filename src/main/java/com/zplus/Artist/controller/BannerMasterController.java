package com.zplus.Artist.controller;

import com.zplus.Artist.dto.req.BannerMasterReqDto;
import com.zplus.Artist.dto.res.BannerMasterResDto;
import com.zplus.Artist.service.BannerMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "banner_master")
public class BannerMasterController {

    @Autowired
    private BannerMasterService bannerMasterService;


    @PostMapping
    public ResponseEntity createBannerMaster(@RequestBody BannerMasterReqDto bannerMasterReqDto) {
        Boolean flag = bannerMasterService.createBannerMaster(bannerMasterReqDto);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity updateBannerMaster(@RequestBody BannerMasterReqDto bannerMasterReqDto) {
        Boolean flag = bannerMasterService.updateBannerMaster(bannerMasterReqDto);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getActiveList")
    public ResponseEntity getActiveBannerMaster()
    {
        List list = bannerMasterService.getActiveBannerMaster();
        return new ResponseEntity(list,HttpStatus.OK);

    }
    @GetMapping(value = "/getAllBannerMasterList")
    public ResponseEntity getAllBannerMasterList()
    {
        List list = bannerMasterService.getAllBannerMasterList();
        return new ResponseEntity(list,HttpStatus.OK);

    }
    @GetMapping(value = "/editBannerMaster/{bannerId}")
    public ResponseEntity editBannerMaster(@PathVariable Integer bannerId)
    {
        BannerMasterResDto bannerMasterResDto = bannerMasterService.editBannerMaster(bannerId);
        if(bannerMasterResDto!=null)
        {
            return new ResponseEntity(bannerMasterResDto, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(bannerMasterResDto,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping(value = "getFollowUpDetailsFromToDate")
//    private ResponseEntity getFollowUpDetailsFromToDate(@RequestBody BannerStartToEndDateReqDto bannerStartToEndDateReqDto){
//        List list=bannerMasterService.getFollowUpDetailsFromToDate(bannerStartToEndDateReqDto);
//        return new ResponseEntity(list, HttpStatus.OK);
//    }

}
