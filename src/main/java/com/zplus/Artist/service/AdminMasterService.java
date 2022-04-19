package com.zplus.Artist.service;

import com.zplus.Artist.dto.req.*;
import com.zplus.Artist.dto.res.AdminForgotPasswordResDto;
import com.zplus.Artist.dto.res.AdminLoginResDto;
import com.zplus.Artist.dto.res.AdminMasterResDto;
import com.zplus.Artist.model.AdminMaster;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface  AdminMasterService {
    boolean create(AdminReqDto adminReqDto);

    boolean update(AdminReqDto adminReqDto);

    AdminMaster getByAdminId(Integer adminId);

    List getAllAdminMaster();

    List activeAdminMaster();

    AdminLoginResDto adminLogin(AdminLoginReqDto adminLoginReqDto);

    AdminMasterResDto checkMobileNo(String mobileNo);

    AdminForgotPasswordResDto forgotPassword(AdminForgotPasswordReqDto adminForgotPasswordReqDto) throws NoSuchAlgorithmException, IOException, KeyManagementException;

    AdminForgotPasswordResDto validateOtp(AdminValidateOtpReqDto adminValidateOtpReqDto);

    Boolean UpdatePassword(UpdatePassword updatePassword);
}
