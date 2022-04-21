package com.zplus.Artist.dao;


import com.zplus.Artist.model.AdminMaster;
import com.zplus.Artist.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface VendorDao extends JpaRepository<Vendor,Integer> {

    Object findAllByVendorStatus(String active);

    AdminMaster findByMobileNoOrEmailId(String userName, String userName1);

    Vendor findByMoblieNoOrEmailId(String Username, String userName1);

    Vendor findByMoblieNo(String mobileNo);

   // Vendor findByVendorId(Integer vendorId);

     @Transactional
     @Modifying
     @Query("update Vendor as am set am.otp=:otp where am.vendorId=:vendorId ")
    Integer updateOtp(@Param("vendorId") Integer vendorId, @Param("otp") Integer otp);

    Integer VendorUpdatePassword(@Param("vendorId")Integer vendorId,@Param("password")String Password);


   Optional<Vendor> findByVendorId(Integer vendorId);
}
