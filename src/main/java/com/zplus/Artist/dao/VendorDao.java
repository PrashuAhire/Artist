package com.zplus.Artist.dao;


import com.zplus.Artist.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorDao extends JpaRepository<Vendor,Integer> {

    Object findAllByVendorStatus(String active);

}
