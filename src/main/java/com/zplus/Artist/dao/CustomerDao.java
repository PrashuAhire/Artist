package com.zplus.Artist.dao;

import com.zplus.Artist.model.CustomerMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<CustomerMaster,Integer> {

    List findAllByStatus(String active);

    CustomerMaster findByCustomerMobNo(String customerMobNo);

    CustomerMaster findByCustomerMobNoOrCustomerMailId(String userName, String userName1);

    CustomerMaster findAllByCustomerMobNo(String customerMobNo);

    @Transactional
    @Modifying
    @Query("update CustomerMaster as cm set cm.otp=:otp where cm.customerId=:customerId")
    Integer updateOtp(@Param("customerId") Integer customerId, @Param("otp") Integer otp);

    @Transactional
    @Modifying
    @Query("update CustomerMaster as cm set cm.password=:password where cm.customerId=:customerId")
    Integer customerUpdatePassword(@Param("customerId") Integer customerId,@Param("password") String password);

}
