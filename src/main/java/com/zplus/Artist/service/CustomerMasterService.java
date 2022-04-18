package com.zplus.Artist.service;

import com.zplus.Artist.dto.req.*;
import com.zplus.Artist.dto.res.AdminMasterResDto;
import com.zplus.Artist.dto.res.CustomerForgotPassResDto;
import com.zplus.Artist.dto.res.CustomerMasterResDto;

import java.util.List;

public interface CustomerMasterService {
    Boolean createCustomerMaster(CustomerMasterReqDto customerMasterReqDto);

    Boolean updateCustomerMaster(CustomerMasterReqDto customerMasterReqDto);

    List getActiveCustomerMaster();

    List getAllCustomerMasterList();

    CustomerMasterResDto editCustomerMaster(Integer customerId);

    AdminMasterResDto checkCustomerMobNo(String customerMobNo);

    CustomerMasterResDto customerLogin(CustomerLoginReqDto customerLoginReqDto);

    CustomerForgotPassResDto forgotPassword(CustomerForgotPasswordReqDto customerForgotPasswordReqDto);

    CustomerForgotPassResDto validateOtp(CustomerValidateOtpReqDto customerValidateOtpReqDto);

    Boolean customerUpdatePassword(CustomerUpdatePasswordReqDto customerUpdatePasswordReqDto);
}
