package com.zplus.Artist.controller;

import com.zplus.Artist.dto.req.ContactUsMasterReqDto;
import com.zplus.Artist.model.ContactUsMaster;
import com.zplus.Artist.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "contact_us_master")
public class ContactUsController {

    @Autowired
    private ContactUsService contactUsMasterService;

    @PostMapping
    public ResponseEntity saveContact(@RequestBody ContactUsMasterReqDto contactUsMasterReqDto) {
        Boolean flag = contactUsMasterService.saveContact(contactUsMasterReqDto);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity updateContact(@RequestBody ContactUsMasterReqDto contactUsMasterReqDto) {
        Boolean flag = contactUsMasterService.updateContact(contactUsMasterReqDto);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getByContactUsId/{contactUsId}")
    public ResponseEntity getByContactUsId(@PathVariable Integer contactUsId) {
        ContactUsMaster contactUsMaster = contactUsMasterService.getByContactUsId(contactUsId);
        return new ResponseEntity(contactUsMaster, HttpStatus.OK);
    }
    @GetMapping(value = "/getAllListOfContactMaster")
    public ResponseEntity getAllListOfContactMaster() {
        List list = contactUsMasterService.getAllListOfContactMaster();
        if (list.size() != 0) {
            return new ResponseEntity(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(list, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
