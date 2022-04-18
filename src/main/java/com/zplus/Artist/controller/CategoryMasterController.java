package com.zplus.Artist.controller;

import com.zplus.Artist.dto.req.CategoryMasterReqDto;
import com.zplus.Artist.dto.res.CategoryResDto;
import com.zplus.Artist.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "category_master")
public class CategoryMasterController {

    @Autowired
    private CategoryService categoryMasterService;


    @PostMapping
    public ResponseEntity createCategoryMaster(@RequestBody CategoryMasterReqDto categoryMasterReqDto) {
        Boolean flag = categoryMasterService.createCategoryMaster(categoryMasterReqDto);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping
    public ResponseEntity updateCategoryMaster(@RequestBody CategoryMasterReqDto categoryMasterReqDto) {
        Boolean flag = categoryMasterService.updateEmployeeMaster(categoryMasterReqDto);
        if (flag) {
            return new ResponseEntity(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity getAllCategoryMaster() {
        List list = categoryMasterService.getAllCategoryMaster();
        return new ResponseEntity(list, HttpStatus.CREATED);
    }
    @GetMapping(value = "/editCategoryMaster/{categoryId}")
    public ResponseEntity editCategoryMaster(@PathVariable Integer categoryId)
    {
        CategoryResDto categoryResDto = categoryMasterService.editCategoryMaster(categoryId);
        if(categoryResDto!=null)
        {
            return new ResponseEntity(categoryResDto, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(categoryResDto,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getActiveList")
    public ResponseEntity getActiveCategoryMaster()
    {
        List list = categoryMasterService.getActiveCategoryMaster();
        return new ResponseEntity(list,HttpStatus.OK);

    }
    @GetMapping("/getAllAdminIdWiseCategoryMasterActiveList/{adminId}")
    public ResponseEntity getAllAdminIdWiseCategoryMasterActiveList(@PathVariable Integer adminId) {
        List<CategoryResDto> list= categoryMasterService.getAllAdminIdWiseCategoryMasterActiveList(adminId);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllAllCategoryMasterList/{adminId}")
    public ResponseEntity getAllAllCategoryMasterList(@PathVariable Integer adminId)
    {
        List<CategoryResDto> list = categoryMasterService.getAllAllCategoryMasterList(adminId);
        if(list.size()!=0)
        {
            return new ResponseEntity(list,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(list,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
