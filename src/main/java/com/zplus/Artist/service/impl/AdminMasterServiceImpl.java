package com.zplus.Artist.service.impl;

import com.zplus.Artist.configuration.RandomNumberGenerator;
import com.zplus.Artist.configuration.SmsPanel;
import com.zplus.Artist.dao.AdminDao;
import com.zplus.Artist.dto.req.*;
import com.zplus.Artist.dto.res.AdminForgotPasswordResDto;
import com.zplus.Artist.dto.res.AdminLoginResDto;
import com.zplus.Artist.dto.res.AdminMasterResDto;
import com.zplus.Artist.model.AdminMaster;
import com.zplus.Artist.service.AdminMasterService;
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
public class AdminMasterServiceImpl implements AdminMasterService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public boolean create(AdminReqDto adminReqDto) {
        AdminMaster adminMaster=new AdminMaster();
        BeanUtils.copyProperties(adminReqDto,adminMaster);
        try {
            adminDao.save(adminMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(AdminReqDto adminReqDto) {
        AdminMaster adminMaster=new AdminMaster();
        adminMaster.setDateCreated(adminReqDto.getCreatedAt());
        adminMaster.setAdminId(adminReqDto.getAdminId());
        BeanUtils.copyProperties(adminReqDto,adminMaster);
        try {
            adminDao.save(adminMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public AdminMaster getByAdminId(Integer adminId) {
        AdminMaster adminMaster=new AdminMaster();
        try {
            Optional<AdminMaster> adminMaster1=adminDao.findById(adminId);
            adminMaster1.ifPresent(settingMaster -> BeanUtils.copyProperties(settingMaster, adminMaster));
            return adminMaster;
        }
        catch (Exception e) {
            e.printStackTrace();
            return adminMaster;
        }
    }

    @Override
    public List getAllAdminMaster() {
        return adminDao.findAll();
    }

    @Override
    public List activeAdminMaster() {
        return adminDao.findAllByStatus("Active");
    }

    @Override
    public AdminLoginResDto adminLogin(AdminLoginReqDto adminLoginReqDto) {
        AdminLoginResDto adminLoginResDto = new AdminLoginResDto();

        AdminMaster adminMaster = adminDao.findByMobileNoOrEmailId(adminLoginReqDto.getUserName(), adminLoginReqDto.getUserName());
        if (adminMaster == null) {
            adminLoginResDto.setMessage("Mobile Number or emailId not exists");
            adminLoginResDto.setResponseCode(HttpStatus.BAD_REQUEST.value());
            return adminLoginResDto;
        }
        if (adminMaster.getMobileNo().equalsIgnoreCase(adminLoginReqDto.getUserName()) || adminMaster.getEmailId().equalsIgnoreCase(adminLoginReqDto.getUserName())) {

            if (adminMaster.getPassword().equals(adminLoginReqDto.getPassword())) {

                if (adminMaster.getStatus().equals("Active")) {

                    adminLoginResDto.setMessage("Login Successfully");
                    adminLoginResDto.setResponseCode(HttpStatus.OK.value());
                    BeanUtils.copyProperties(adminMaster, adminLoginResDto);


                    return adminLoginResDto;
                } else {
                    adminLoginResDto.setMessage("Inactive User");
                    adminLoginResDto.setResponseCode(HttpStatus.BAD_REQUEST.value());
                    return adminLoginResDto;
                }
            } else {
                adminLoginResDto.setMessage("Wrong Password");
                adminLoginResDto.setResponseCode(HttpStatus.BAD_REQUEST.value());
                return adminLoginResDto;
            }
        } else {
            adminLoginResDto.setMessage("Mobile Number Or EmailId Not Found");
            adminLoginResDto.setResponseCode(HttpStatus.BAD_REQUEST.value());
            return adminLoginResDto;
        }
    }

    @Override
    public AdminMasterResDto checkMobileNo(String mobileNo) {
        AdminMaster adminMaster = adminDao.findByMobileNo(mobileNo);
        AdminMasterResDto adminMasterResDto = new AdminMasterResDto();

        if (adminMaster == null) {
            adminMasterResDto.setMessage("Mobile no is not exists");
            adminMasterResDto.setFlag(false);
        } else {
            adminMasterResDto.setMessage("Mobile no is already exists");
            adminMasterResDto.setFlag(true);
        }
        return adminMasterResDto;
    }

    @Override
    public AdminForgotPasswordResDto forgotPassword(AdminForgotPasswordReqDto adminForgotPasswordReqDto) throws NoSuchAlgorithmException, IOException, KeyManagementException {
        AdminMaster adminMaster = adminDao.findAllByMobileNo(adminForgotPasswordReqDto.getMobileNo());
        if (adminMaster != null) {
            Integer otp = RandomNumberGenerator.getNumber();
            System.out.println("otp " + otp);

            Integer flag = adminDao.updateOtp(adminMaster.getAdminId(), otp);

            System.out.println("flag" + flag);

            AdminForgotPasswordResDto adminForgotPasswordResDto = new AdminForgotPasswordResDto();

            String sms = "verification OTP code " + otp;
            SmsPanel.sendSms(adminMaster.getMobileNo(), sms);
            adminForgotPasswordResDto.setFlag(true);
            adminForgotPasswordResDto.setCustomerId(adminMaster.getAdminId());
//            customerForgotPassResDto.setOtp(otp);
            return adminForgotPasswordResDto;
        } else {
            AdminForgotPasswordResDto adminForgotPasswordResDto = new AdminForgotPasswordResDto();
            adminForgotPasswordResDto.setFlag(false);
            return adminForgotPasswordResDto;
        }
    }

    @Override
    public AdminForgotPasswordResDto validateOtp(AdminValidateOtpReqDto adminValidateOtpReqDto) {
        AdminMaster adminMaster = new AdminMaster();
        try {
            Optional<AdminMaster> adminMaster1 = adminDao.findByAdminId(adminValidateOtpReqDto.getAdminId());
            adminMaster1.ifPresent(settingMaster -> BeanUtils.copyProperties(settingMaster, adminMaster));
        } catch (Exception e) {
            e.printStackTrace();
        }

        AdminForgotPasswordResDto adminForgotPasswordResDto = new AdminForgotPasswordResDto();

        if (adminValidateOtpReqDto.getOtp().equals(adminMaster.getOtp())) {
            adminForgotPasswordResDto.setFlag(true);
            adminForgotPasswordResDto.setCustomerId(adminMaster.getAdminId());
            return adminForgotPasswordResDto;
        } else {
            adminForgotPasswordResDto.setFlag(false);
            adminForgotPasswordResDto.setCustomerId(adminMaster.getAdminId());
            return adminForgotPasswordResDto;
        }
    }

    @Override
    public Boolean UpdatePassword(UpdatePassword updatePassword) {
        Integer adminId = adminDao.UpdatePassword(updatePassword.getAdminId(), updatePassword.getPassword());
        if (adminId == null) {
            return false;
        } else {
            return true;
        }
    }
}
