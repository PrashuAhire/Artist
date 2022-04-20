package com.zplus.Artist.service;



import com.zplus.Artist.dto.req.VendorDto;
import com.zplus.Artist.model.Vendor;

import java.util.List;

public interface VendorService {


     Boolean create(VendorDto vendorDto);
     Boolean upadte(VendorDto vendorDto);
     Vendor getByVendorId(Integer vendorId);
     List getAllVendor();
     List activeVendor();

}
