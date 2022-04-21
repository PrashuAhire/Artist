package com.zplus.Artist.service.impl;


import com.zplus.Artist.configuration.RandomNumberGenerator;
import com.zplus.Artist.configuration.SmsPanel;
import com.zplus.Artist.dao.VendorDao;
import com.zplus.Artist.dto.req.*;
import com.zplus.Artist.dto.res.AdminForgotPasswordResDto;
import com.zplus.Artist.dto.res.VendorForgotPasswordResDto;
import com.zplus.Artist.dto.res.VendorLoginResDto;
import com.zplus.Artist.dto.res.VendorResDto;
import com.zplus.Artist.model.AdminMaster;
import com.zplus.Artist.model.Vendor;
import com.zplus.Artist.service.VendorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorDao vendorDao;

    @Override
    public Boolean create(VendorDto vendorDto) {
        Vendor vendor = new Vendor();
        BeanUtils.copyProperties(vendorDto, vendor);
        try {
            vendorDao.save(vendor);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean upadte(VendorDto vendorDto) {
        Vendor vendor = new Vendor();
        vendor.setId(vendor.getId());
        BeanUtils.copyProperties(vendorDto, vendor);
        try {
            vendorDao.save(vendor);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Vendor getByVendorId(Integer vendorId) {
        Vendor vendor = new Vendor();
        try {
            Optional<Vendor> vendor1 = vendorDao.findById(vendorId);
            vendor1.ifPresent(settingMaster -> BeanUtils.copyProperties(settingMaster, vendor));
            return vendor;
        } catch (Exception e) {
            e.printStackTrace();
            return vendor;
        }
    }

    @Override
    public List getAllVendor() {
        return (List) vendorDao.findAll();
    }

    @Override
    public List activeVendor() {
        return (List) vendorDao.findAllByVendorStatus("Active");
    }

    @Override
    public VendorLoginResDto vendorLogin(VendorLoginReqDto vendorLoginReqDto) {
        VendorLoginResDto vendorLoginResDto = new VendorLoginResDto();
        Vendor vendor = vendorDao.findByMoblieNoOrEmailId(vendorLoginReqDto.getUserName(), vendorLoginReqDto.getUserName());
        if (vendor == null) {
            vendorLoginResDto.setMessage("Message Number or Email not exists");
            vendorLoginResDto.setResponseCode(HttpStatus.BAD_REQUEST.value());
            return vendorLoginResDto;
        }
        if (vendor.getVendorMoblieNo().equalsIgnoreCase(vendorLoginReqDto.getUserName()) || vendor.getVendorEmail().equalsIgnoreCase(vendorLoginReqDto.getUserName())) {

            if (vendor.getVendorPassword().equals(vendorLoginReqDto.getPassword())) {

                if (vendor.getVendorStatus().equals("Active")) {

                    vendorLoginResDto.setMessage("Login Successfully");
                    vendorLoginResDto.setResponseCode(HttpStatus.OK.value());
                    BeanUtils.copyProperties(vendor, vendorLoginResDto);

                    return vendorLoginResDto;
                } else {
                    vendorLoginResDto.setMessage("Inactive Vendor");
                    vendorLoginResDto.setResponseCode(HttpStatus.BAD_REQUEST.value());
                    return vendorLoginResDto;
                }
            } else {
                vendorLoginResDto.setMessage("Wrong Password");
                vendorLoginResDto.setResponseCode(HttpStatus.BAD_REQUEST.value());
                return vendorLoginResDto;
            }
        } else {
            vendorLoginResDto.setMessage("Mobile Number or EmailId Not Found");
            vendorLoginResDto.setResponseCode(HttpStatus.BAD_REQUEST.value());
            return vendorLoginResDto;
        }


    }

    @Override
    public VendorResDto checkMoblieNo(String mobileNo) {
        Vendor vendor = vendorDao.findByMoblieNo(mobileNo);
        VendorResDto vendorResDto = new VendorResDto();

        if (vendor == null) {
            vendorResDto.setMessage("Moblie No is not exists");
            vendorResDto.setFlag(false);
        } else {
            vendorResDto.setMessage("Mobile No is already exists");
            vendorResDto.setFlag(true);
        }

        return null;
    }

    @Override
    public VendorForgotPasswordResDto forgotPassword(VendorForgotPasswordReqDto vendorForgotPasswordReqDto) throws NoSuchAlgorithmException, IOException, KeyManagementException {
        Vendor vendor = vendorDao.findByMoblieNo(vendorForgotPasswordReqDto.getMobileNo());
        if (vendor != null) {
            Integer otp = RandomNumberGenerator.getNumber();
            System.out.println("otp" + otp);

            Integer flag = vendorDao.updateOtp(vendor.getId(), otp);

            System.out.println("flag" + flag);

            VendorForgotPasswordResDto vendorForgotPasswordResDto = new VendorForgotPasswordResDto();

            String sms = "verification OTP code " + otp;
            SmsPanel.sendSms(vendor.getVendorMoblieNo(), sms);
            vendorForgotPasswordResDto.setFlag(true);
            vendorForgotPasswordResDto.setVendorId(vendor.getId());
            return vendorForgotPasswordResDto;

        } else {
            VendorForgotPasswordResDto vendorForgotPasswordResDto = new VendorForgotPasswordResDto();
            vendorForgotPasswordResDto.setFlag(false);
            return vendorForgotPasswordResDto;

        }
    }

    @Override
    public VendorForgotPasswordResDto validateOtp(VendorValidateOtpReqDto vendorValidateOtpReqDto) {
        Vendor vendor = new Vendor();
        try {
            Optional<Vendor> vendor1 = vendorDao.findByVendorId(vendorValidateOtpReqDto.getVendorId());
            vendor1.ifPresent(settingMaster -> BeanUtils.copyProperties(settingMaster, vendor));
        } catch (Exception e) {
            e.printStackTrace();
        }

        VendorForgotPasswordResDto vendorForgotPasswordResDto = new VendorForgotPasswordResDto();

        if (vendorValidateOtpReqDto.getOtp().equals(vendor.getOtp())) {
            vendorForgotPasswordResDto.setFlag(true);
            vendorForgotPasswordResDto.setVendorId(vendor.getId());
            return vendorForgotPasswordResDto;
        } else {
            vendorForgotPasswordResDto.setFlag(false);
            vendorForgotPasswordResDto.setVendorId(vendor.getId());
            return vendorForgotPasswordResDto;
        }
    }


    @Override
    public Boolean UpdatePassword(UpdatePassword updatePassword) {
        Integer vendorId = vendorDao.VendorUpdatePassword(updatePassword.getVendorId(), updatePassword.getPassword());
        if (vendorId == null) {
            return false;
        } else {
            return true;
        }

    }

}