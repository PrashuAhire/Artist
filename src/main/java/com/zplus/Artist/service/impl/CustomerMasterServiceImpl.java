package com.zplus.Artist.service.impl;

import com.zplus.Artist.configuration.RandomNumberGenerator;
import com.zplus.Artist.configuration.SmsPanel;
import com.zplus.Artist.dao.CustomerDao;
import com.zplus.Artist.dto.req.*;
import com.zplus.Artist.dto.res.AdminMasterResDto;
import com.zplus.Artist.dto.res.CustomerForgotPassResDto;
import com.zplus.Artist.dto.res.CustomerMasterResDto;
import com.zplus.Artist.model.CustomerMaster;
import com.zplus.Artist.service.CustomerMasterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerMasterServiceImpl implements CustomerMasterService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Boolean createCustomerMaster(CustomerMasterReqDto customerMasterReqDto) {
        CustomerMaster customerMaster = new CustomerMaster();
        BeanUtils.copyProperties(customerMasterReqDto, customerMaster);
        customerMaster.setStatus("Active");

        try {
            customerDao.save(customerMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateCustomerMaster(CustomerMasterReqDto customerMasterReqDto) {
        CustomerMaster customerMaster = new CustomerMaster();
        customerMaster.setCustomerId(customerMasterReqDto.getCustomerId());
        customerMaster.setDateCreated(customerMasterReqDto.getCreatedAt());
        BeanUtils.copyProperties(customerMasterReqDto, customerMaster);
        customerMaster.setStatus("Active");

        try {
            customerDao.save(customerMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List getActiveCustomerMaster() {
        List list = customerDao.findAllByStatus("Active");
        return list;
    }

    @Override
    public List getAllCustomerMasterList() {
        return (List) customerDao.findAll();
    }

    @Override
    public CustomerMasterResDto editCustomerMaster(Integer customerId) {
        CustomerMasterResDto customerMasterResDto = new CustomerMasterResDto();

        try {
            CustomerMaster customerMaster = null;
            java.util.Optional<CustomerMaster> optionalCustomerMaster = customerDao.findById(customerId);
            if (optionalCustomerMaster.isPresent()) {
                customerMaster = optionalCustomerMaster.get();
            }

            BeanUtils.copyProperties(customerMaster, customerMasterResDto);
            return customerMasterResDto;
        } catch (Exception e) {
            e.printStackTrace();
            return customerMasterResDto;
        }
    }

    @Override
    public AdminMasterResDto checkCustomerMobNo(String customerMobNo) {
        CustomerMaster customerMaster = customerDao.findByCustomerMobNo(customerMobNo);
        AdminMasterResDto adminMasterResDto = new AdminMasterResDto();

        if (customerMaster == null) {
            adminMasterResDto.setMessage("Mobile no is not exists");
            adminMasterResDto.setFlag(false);
        } else {
            adminMasterResDto.setMessage("Mobile no is already exists");
            adminMasterResDto.setFlag(true);
        }
        return adminMasterResDto;
    }

    @Override
    public CustomerMasterResDto customerLogin(CustomerLoginReqDto customerLoginReqDto) {
        CustomerMasterResDto customerMasterResDto = new CustomerMasterResDto();

        CustomerMaster customerMaster = customerDao.findByCustomerMobNoOrCustomerMailId(customerLoginReqDto.getUserName(), customerLoginReqDto.getUserName());
        if (customerMaster == null) {
            customerMasterResDto.setMessage("Mobile Number or emailId not exists");
            customerMasterResDto.setResponseCode(HttpStatus.BAD_REQUEST.value());
            return customerMasterResDto;
        }
        if (customerMaster.getCustomerMobNo().equalsIgnoreCase(customerLoginReqDto.getUserName()) || customerMaster.getCustomerMailId().equalsIgnoreCase(customerLoginReqDto.getUserName())) {

            if (customerMaster.getPassword().equals(customerLoginReqDto.getPassword())) {

                if (customerMaster.getStatus().equals("Active")) {

                    customerMasterResDto.setMessage("Login Successfully");
                    customerMasterResDto.setResponseCode(HttpStatus.OK.value());
                    BeanUtils.copyProperties(customerMaster, customerMasterResDto);
                    return customerMasterResDto;
                } else {
                    customerMasterResDto.setMessage("Inactive User");
                    customerMasterResDto.setResponseCode(HttpStatus.BAD_REQUEST.value());
                    return customerMasterResDto;
                }
            } else {
                customerMasterResDto.setMessage("Wrong Password");
                customerMasterResDto.setResponseCode(HttpStatus.BAD_REQUEST.value());
                return customerMasterResDto;
            }
        } else {
            customerMasterResDto.setMessage("Mobile Number Or EmailId Not Found");
            customerMasterResDto.setResponseCode(HttpStatus.BAD_REQUEST.value());
            return customerMasterResDto;
        }
    }

    @Override
    public CustomerForgotPassResDto forgotPassword(CustomerForgotPasswordReqDto customerForgotPasswordReqDto) {
        CustomerMaster customerMaster = customerDao.findAllByCustomerMobNo(customerForgotPasswordReqDto.getCustomerMobNo());
        if (customerMaster != null) {
            Integer otp = RandomNumberGenerator.getNumber();
            System.out.println("otp " + otp);

            Integer flag = customerDao.updateOtp(customerMaster.getCustomerId(), otp);

            System.out.println("flag" + flag);

            CustomerForgotPassResDto customerForgotPassResDto = new CustomerForgotPassResDto();
            try {

                String sms = "Use OTP " + otp + " to verify. Do not share the OTP for security reasons. Kiran Tyres..";
                System.out.println(sms);
                SmsPanel.sendSms(customerMaster.getCustomerMobNo(), sms);

                customerForgotPassResDto.setFlag(true);
                customerForgotPassResDto.setCustomerId(customerMaster.getCustomerId());
                customerForgotPassResDto.setOtp(otp);

            } catch (Exception e) {
                e.printStackTrace();

            }
            return customerForgotPassResDto;
        } else {
            CustomerForgotPassResDto customerForgotPassResDto = new CustomerForgotPassResDto();
            customerForgotPassResDto.setFlag(false);
            return customerForgotPassResDto;
        }
    }

    @Override
    public CustomerForgotPassResDto validateOtp(CustomerValidateOtpReqDto customerValidateOtpReqDto) {
        CustomerMaster customerMaster = new CustomerMaster();
        try {
            Optional<CustomerMaster> adminMaster1 = customerDao.findById(customerValidateOtpReqDto.getCustomerId());
            adminMaster1.ifPresent(settingMaster -> BeanUtils.copyProperties(settingMaster, customerMaster));
        } catch (Exception e) {
            e.printStackTrace();
        }

        CustomerForgotPassResDto customerForgotPassResDto = new CustomerForgotPassResDto();


        if (customerValidateOtpReqDto.getOtp().equals(customerMaster.getOtp())) {
            customerForgotPassResDto.setFlag(true);
            customerForgotPassResDto.setCustomerId(customerMaster.getCustomerId());
            customerForgotPassResDto.setOtp(customerMaster.getOtp());
            return customerForgotPassResDto;
        } else {
            customerForgotPassResDto.setFlag(false);
            customerForgotPassResDto.setCustomerId(customerMaster.getCustomerId());
            return customerForgotPassResDto;
        }
    }

    @Override
    public Boolean customerUpdatePassword(CustomerUpdatePasswordReqDto customerUpdatePasswordReqDto) {
        Integer customerId = customerDao.customerUpdatePassword(customerUpdatePasswordReqDto.getCustomerId(), customerUpdatePasswordReqDto.getPassword());
        if (customerId == null) {
            return false;
        } else {
            return true;
        }
    }
}
