package com.zplus.Artist.controller;

import com.zplus.Artist.dto.req.*;
import com.zplus.Artist.dto.res.AdminMasterResDto;
import com.zplus.Artist.dto.res.CustomerForgotPassResDto;
import com.zplus.Artist.dto.res.CustomerMasterResDto;
import com.zplus.Artist.service.CustomerMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "customer_master")
public class CustomerMasterController {

    @Autowired
    private CustomerMasterService customerMasterService;

    @PostMapping
    public ResponseEntity createCustomerMaster(@RequestBody CustomerMasterReqDto customerMasterReqDto) {
        Boolean flag = customerMasterService.createCustomerMaster(customerMasterReqDto);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping
    public ResponseEntity updateCustomerMaster(@RequestBody CustomerMasterReqDto customerMasterReqDto) {
        Boolean flag = customerMasterService.updateCustomerMaster(customerMasterReqDto);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getActiveList")
    public ResponseEntity getActiveCustomerMaster()
    {
        List list = customerMasterService.getActiveCustomerMaster();
        return new ResponseEntity(list,HttpStatus.OK);

    }
    @GetMapping(value = "/getAllCustomerMasterList")
    public ResponseEntity getAllCustomerMasterList()
    {
        List list = customerMasterService.getAllCustomerMasterList();
        return new ResponseEntity(list,HttpStatus.OK);

    }
    @GetMapping(value = "/editCustomerMaster/{customerId}")
    public ResponseEntity editCustomerMaster(@PathVariable Integer customerId)
    {
        CustomerMasterResDto customerMasterResDto = customerMasterService.editCustomerMaster(customerId);
        if(customerMasterResDto!=null)
        {
            return new ResponseEntity(customerMasterResDto, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(customerMasterResDto,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/checkCustomerMobNo/{customerMobNo}")
    private ResponseEntity checkCustomerMobNo(@PathVariable String customerMobNo) {

        AdminMasterResDto adminMasterResDto = customerMasterService.checkCustomerMobNo(customerMobNo);

        if (adminMasterResDto != null)
            return new ResponseEntity(adminMasterResDto, HttpStatus.OK);
        else
            return new ResponseEntity(adminMasterResDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value = "/customerLogin")
    public ResponseEntity customerLogin(@RequestBody CustomerLoginReqDto customerLoginReqDto)
    {
        CustomerMasterResDto customerMasterResDto=customerMasterService.customerLogin(customerLoginReqDto);
        return new ResponseEntity(customerMasterResDto,HttpStatus.OK);
    }
    @PostMapping(value = "/forgotPassword")
    private ResponseEntity forgotPassword(@RequestBody CustomerForgotPasswordReqDto customerForgotPasswordReqDto) throws NoSuchAlgorithmException, IOException, KeyManagementException {
        CustomerForgotPassResDto customerForgotPassResDto=customerMasterService.forgotPassword(customerForgotPasswordReqDto);
        return new ResponseEntity(customerForgotPassResDto,HttpStatus.OK);
    }
    @PostMapping(value = "/validateotp")
    public ResponseEntity validateOtp(@RequestBody CustomerValidateOtpReqDto customerValidateOtpReqDto)
    {
        CustomerForgotPassResDto customerForgotPassResDto = customerMasterService.validateOtp(customerValidateOtpReqDto);
        return new ResponseEntity(customerForgotPassResDto,HttpStatus.OK);
    } @PutMapping(value = "/customer/customerUpdatePassword")
    public ResponseEntity customerUpdatePassword(@RequestBody CustomerUpdatePasswordReqDto customerUpdatePasswordReqDto)
    {
        Boolean flag = customerMasterService.customerUpdatePassword(customerUpdatePasswordReqDto);

        return new ResponseEntity(flag,HttpStatus.OK);

    }

}
