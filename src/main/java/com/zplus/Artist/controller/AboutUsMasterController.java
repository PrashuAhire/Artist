package com.zplus.Artist.controller;

import com.zplus.Artist.dto.req.AboutUsReqDto;
import com.zplus.Artist.model.AboutUsMaster;
import com.zplus.Artist.service.AboutUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "about_us_master")
public class AboutUsMasterController {

    @Autowired
    private AboutUsService aboutUsService;

    @PostMapping
    public ResponseEntity create(@RequestBody AboutUsReqDto aboutUsReqDto) {
        Boolean flag = aboutUsService.create(aboutUsReqDto);
        return new ResponseEntity(flag, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody AboutUsReqDto aboutUsReqDto) {
        Boolean flag = aboutUsService.update(aboutUsReqDto);
        return new ResponseEntity(flag, HttpStatus.OK);
    }

    @GetMapping(value = "getByAboutUsId/{aboutUsId}")
    public ResponseEntity getByAboutUsId(@PathVariable Integer aboutUsId) {
        AboutUsMaster aboutUsMaster = aboutUsService.getByAboutUsId(aboutUsId);
        return new ResponseEntity(aboutUsMaster, HttpStatus.OK);
    }

    @GetMapping(value = "getAllAboutUs")
    public ResponseEntity getAllAboutUs() {
        List list = aboutUsService.getAllAboutUs();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/activeAboutUs")
    public ResponseEntity activeAboutUs() {
        List list = aboutUsService.activeAboutUs();

        return new ResponseEntity(list, HttpStatus.OK);
    }
}
