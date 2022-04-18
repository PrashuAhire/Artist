package com.zplus.Artist.service;

import com.zplus.Artist.dto.req.CategoryMasterReqDto;
import com.zplus.Artist.dto.res.CategoryResDto;

import java.util.List;

public interface CategoryService {
    Boolean createCategoryMaster(CategoryMasterReqDto categoryMasterReqDto);

    Boolean updateEmployeeMaster(CategoryMasterReqDto categoryMasterReqDto);

    List getAllCategoryMaster();

    List getActiveCategoryMaster();

    CategoryResDto editCategoryMaster(Integer categoryId);

    List<CategoryResDto> getAllAdminIdWiseCategoryMasterActiveList(Integer adminId);

    List<CategoryResDto> getAllAllCategoryMasterList(Integer adminId);
}
