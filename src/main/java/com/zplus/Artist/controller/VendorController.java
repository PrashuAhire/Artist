package com.zplus.Artist.controller;


import com.zplus.Artist.dto.req.*;
import com.zplus.Artist.dto.res.AdminForgotPasswordResDto;
import com.zplus.Artist.dto.res.VendorForgotPasswordResDto;
import com.zplus.Artist.dto.res.VendorLoginResDto;
import com.zplus.Artist.dto.res.VendorResDto;
import com.zplus.Artist.model.Vendor;
import com.zplus.Artist.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/vendorMaster")
public class VendorController {


    @Autowired
    private VendorService vendorService;

    @PostMapping
    public ResponseEntity create(@RequestBody VendorDto vendorDto) {
        Boolean flag = vendorService.create(vendorDto);
        return new ResponseEntity(flag, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody VendorDto vendorDto) {
        Boolean flag = vendorService.upadte(vendorDto);
        return new ResponseEntity(flag, HttpStatus.OK);
    }

    @GetMapping(value = "getbyVendorId/{vendorId}")
    public ResponseEntity getByVendorId(@PathVariable Integer vendorId) {
        Vendor vendor = vendorService.getByVendorId(vendorId);
        return new ResponseEntity(vendor, HttpStatus.OK);
    }

    @GetMapping(value = "getAllVendor")
    public ResponseEntity getAllVendor() {
        List list = vendorService.getAllVendor();
        return new ResponseEntity(list, HttpStatus.OK);

    }

    @GetMapping(value = "activeVendor")
    public ResponseEntity activeVendor() {
        List list = vendorService.activeVendor();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping(value = "vendorLogin")
    public ResponseEntity vendorLogin(@RequestBody VendorLoginReqDto vendorLoginReqDto) {
        VendorLoginResDto vendorLoginResDto = vendorService.vendorLogin(vendorLoginReqDto);
        return new ResponseEntity(vendorLoginResDto, HttpStatus.OK);

    }

    @GetMapping("/checkMoblieNo/{moblieNo}")
    public ResponseEntity checkMobileNo(@PathVariable String mobileNo) {
        VendorResDto vendorResDto = vendorService.checkMoblieNo(mobileNo);

        if (vendorResDto != null)
            return new ResponseEntity(vendorResDto, HttpStatus.OK);
        else
            return new ResponseEntity(vendorResDto, HttpStatus.INTERNAL_SERVER_ERROR);


    }
     @PostMapping(value = "/forgotPassword")
    private ResponseEntity ForgotPassword(@RequestBody VendorForgotPasswordReqDto vendorForgotPasswordReqDto) throws NoSuchAlgorithmException, IOException, KeyManagementException {
        VendorForgotPasswordResDto vendorForgotPasswordResDto = vendorService.forgotPassword(vendorForgotPasswordReqDto);
        return new ResponseEntity(vendorForgotPasswordResDto, HttpStatus.OK);
    }

    @PostMapping(value = "/validateotp")
public  ResponseEntity validateOtp(@RequestBody VendorValidateOtpReqDto vendorValidateOtpReqDto){
    VendorForgotPasswordResDto vendorForgotPasswordResDto = vendorService.validateOtp(vendorValidateOtpReqDto);
    return  new ResponseEntity(vendorForgotPasswordResDto,HttpStatus.OK);
    }

    @PutMapping(value = "/vendor/vendorUpatePassword")
    public ResponseEntity vendorUpdatePassword(@RequestBody VendorUpdatePasswordReqDto vendorUpdatePasswordReqDto){
        Boolean flag = vendorService.vendorUpdatePassword(vendorUpdatePasswordReqDto);
        return new ResponseEntity(flag,HttpStatus.OK);
    }

}











