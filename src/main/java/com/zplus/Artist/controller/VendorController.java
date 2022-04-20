package com.zplus.Artist.controller;


import com.zplus.Artist.dto.req.VendorDto;
import com.zplus.Artist.model.Vendor;
import com.zplus.Artist.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/vendorMaster")
public class VendorController {


    @Autowired
    private VendorService vendorService;

      @PostMapping
    public ResponseEntity create(@RequestBody VendorDto vendorDto){
          Boolean flag= vendorService.create(vendorDto);
          return  new ResponseEntity(flag,HttpStatus.OK);
      }
      @PutMapping()
      public  ResponseEntity update(@RequestBody VendorDto vendorDto){
          Boolean flag = vendorService.upadte(vendorDto);
          return new ResponseEntity(flag,HttpStatus.OK);
      }
      @GetMapping(value ="getbyVendorId/{vendorId}")
      public ResponseEntity getByVendorId(@PathVariable Integer vendorId){
         Vendor vendor = vendorService.getByVendorId(vendorId);
         return  new ResponseEntity(vendor,HttpStatus.OK);
      }

      @GetMapping(value = "getAllVendor")
       public ResponseEntity getAllVendor(){
           List list =vendorService.getAllVendor();
return  new ResponseEntity( list,HttpStatus.OK);

       }
       public ResponseEntity activeVendor(){
         List list = vendorService.activeVendor();
         return  new ResponseEntity(list,HttpStatus.OK);
       }






}
