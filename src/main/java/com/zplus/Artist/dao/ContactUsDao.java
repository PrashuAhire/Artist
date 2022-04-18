package com.zplus.Artist.dao;

import com.zplus.Artist.model.ContactUsMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactUsDao extends JpaRepository<ContactUsMaster,Integer> {
}
