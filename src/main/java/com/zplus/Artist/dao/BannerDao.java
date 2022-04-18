package com.zplus.Artist.dao;

import com.zplus.Artist.model.BannerMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerDao extends JpaRepository<BannerMaster,Integer> {

    List findAllByStatus(String active);
}
