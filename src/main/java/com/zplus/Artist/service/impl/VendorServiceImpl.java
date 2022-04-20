package com.zplus.Artist.service.impl;


import com.zplus.Artist.dao.VendorDao;
import com.zplus.Artist.dto.req.VendorDto;
import com.zplus.Artist.model.Vendor;
import com.zplus.Artist.service.VendorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorDao vendorDao;

    @Override
    public Boolean create(VendorDto vendorDto) {
            Vendor vendor= new Vendor();
        BeanUtils.copyProperties(vendorDto,vendor);
           try {
              vendorDao.save(vendor);
              return true;
           } catch (Exception e){
                  e.printStackTrace();
                         return false;
           }
    }

    @Override
    public Boolean upadte(VendorDto vendorDto) {
        Vendor vendor = new Vendor();
         vendor.setId(vendor.getId());
BeanUtils.copyProperties(vendorDto,vendor);
try {
     vendorDao.save(vendor);
     return  true ;

} catch (Exception e){
    e.printStackTrace();
        return false;
    }
    }

    @Override
    public Vendor getByVendorId(Integer vendorId) {
         Vendor vendor = new Vendor();
         try {
             Optional<Vendor> vendor1=vendorDao.findById(vendorId);
             vendor1.ifPresent(settingMaster-> BeanUtils.copyProperties(settingMaster,vendor));
             return vendor;
         }
         catch ( Exception e){
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
}
