package com.zplus.Artist.dao;

import com.zplus.Artist.dto.res.CategoryResDto;
import com.zplus.Artist.model.CategoryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryDao extends JpaRepository<CategoryMaster,Integer> {

    Optional<CategoryResDto> getByCategoryId(@Param("categoryId") Integer categoryId);

    @Query("select new com.zplus.Artist.dto.res.CategoryResDto(cm.categoryId,cm.categoryName,cm.categoryStatus,cm.categoryImage,cm.description,cm.adminMaster.adminId,cm.createdAt,cm.updatedAt) from CategoryMaster as cm where cm.adminMaster.adminId=:adminId")
    List<CategoryResDto> getAllAllCategoryMasterList(@Param("adminId") Integer adminId);

    @Query("select new com.zplus.Artist.dto.res.CategoryResDto(cm.categoryId,cm.categoryName,cm.categoryStatus,cm.categoryImage,cm.description,cm.adminMaster.adminId,cm.createdAt,cm.updatedAt) from CategoryMaster as cm where cm.adminMaster.adminId=:adminId and cm.categoryStatus='Active'")
    List<CategoryResDto> getAllAdminIdWiseCategoryMasterActiveList(@Param("adminId") Integer adminId);

    List findAllByCategoryStatus(String active);
}
