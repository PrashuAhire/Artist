package com.zplus.Artist.service.impl;

import com.zplus.Artist.dao.CategoryDao;
import com.zplus.Artist.dto.req.CategoryMasterReqDto;
import com.zplus.Artist.dto.res.CategoryResDto;
import com.zplus.Artist.model.AdminMaster;
import com.zplus.Artist.model.CategoryMaster;
import com.zplus.Artist.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Boolean createCategoryMaster(CategoryMasterReqDto categoryMasterReqDto) {
        CategoryMaster categoryMaster=new CategoryMaster();
        AdminMaster adminMaster=new AdminMaster();
        adminMaster.setAdminId(categoryMasterReqDto.getAdminId());
        categoryMaster.setAdminMaster(adminMaster);
        BeanUtils.copyProperties(categoryMasterReqDto, categoryMaster);
        try {
            categoryDao.save(categoryMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateEmployeeMaster(CategoryMasterReqDto categoryMasterReqDto) {
        CategoryMaster categoryMaster=new CategoryMaster();
        AdminMaster adminMaster=new AdminMaster();
        adminMaster.setAdminId(categoryMasterReqDto.getAdminId());
        categoryMaster.setAdminMaster(adminMaster);
        categoryMaster.setDateCreated(categoryMasterReqDto.getCreatedAt());
        categoryMaster.setCategoryId(categoryMasterReqDto.getCategoryId());
        BeanUtils.copyProperties(categoryMasterReqDto, categoryMaster);
        try {
            categoryDao.save(categoryMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List getAllCategoryMaster() {
        List<CategoryResDto> list= (List) categoryDao.findAll();
        return list;
    }

    @Override
    public List getActiveCategoryMaster() {
        List list = categoryDao.findAllByCategoryStatus("Active");
        return list;
    }

    @Override
    public CategoryResDto editCategoryMaster(Integer categoryId) {
        CategoryResDto categoryResDto=new CategoryResDto();
        try {
            Optional<CategoryResDto> categoryResDto1=categoryDao.getByCategoryId(categoryId);
            categoryResDto1.ifPresent(settingMaster -> BeanUtils.copyProperties(settingMaster, categoryResDto));
            return categoryResDto;
        }
        catch (Exception e) {
            e.printStackTrace();
            return categoryResDto;
        }
    }

    @Override
    public List<CategoryResDto> getAllAdminIdWiseCategoryMasterActiveList(Integer adminId) {
        List<CategoryResDto> categoryResDtos=categoryDao.getAllAdminIdWiseCategoryMasterActiveList(adminId);
        return categoryResDtos;
    }

    @Override
    public List<CategoryResDto> getAllAllCategoryMasterList(Integer adminId) {
        List<CategoryResDto> categoryResDtos=categoryDao.getAllAllCategoryMasterList(adminId);
        return categoryResDtos;
    }
}
