package com.zplus.Artist.service;



import com.zplus.Artist.dto.req.*;
import com.zplus.Artist.dto.res.*;
import com.zplus.Artist.model.Vendor;
import org.apache.commons.math3.stat.descriptive.moment.VectorialMean;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface VendorService {


     Boolean create(VendorDto vendorDto);

     Boolean upadte(VendorDto vendorDto);

     Vendor getByVendorId(Integer vendorId);

     List getAllVendor();

     List activeVendor();


     VendorLoginResDto vendorLogin(VendorLoginReqDto vendorLoginReqDto);

     VendorResDto checkMoblieNo(String mobileNo);

     VendorForgotPasswordResDto forgotPassword(VendorForgotPasswordReqDto vendorForgotPasswordReqDto) throws NoSuchAlgorithmException, IOException,KeyManagementException;

     VendorForgotPasswordResDto validateOtp(VendorValidateOtpReqDto vendorValidateOtpReqDto );


   Boolean vendorUpdatePassword(VendorUpdatePasswordReqDto vendorUpdatePasswordReqDto);


}
