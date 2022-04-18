package com.zplus.Artist.dao;

import com.zplus.Artist.model.AboutUsMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AboutUsDao extends JpaRepository<AboutUsMaster,Integer> {

    List findAllByStatus(String active);
}
