package com.zplus.Artist.dao;

import com.zplus.Artist.model.AdminMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdminDao extends JpaRepository<AdminMaster,Integer> {

    List findAllByStatus(String active);

    AdminMaster findByMobileNoOrEmailId(String userName, String userName1);

    AdminMaster findByMobileNo(String mobileNo);

    AdminMaster findAllByMobileNo(String mobileNo);

    @Transactional
    @Modifying
    @Query("update AdminMaster as am set am.otp=:otp where am.adminId=:adminId")
    Integer updateOtp(@Param("adminId") Integer adminId, @Param("otp") Integer otp);

    Optional<AdminMaster> findByAdminId(Integer adminId);

    @Transactional
    @Modifying
    @Query("update AdminMaster as am set am.password=:password where am.adminId=:adminId")
    Integer UpdatePassword(@Param("adminId") Integer adminId, @Param("password") String password);
}
