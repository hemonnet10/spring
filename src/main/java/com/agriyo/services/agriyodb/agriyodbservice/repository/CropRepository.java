package com.agriyo.services.agriyodb.agriyodbservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agriyo.services.agriyodb.agriyodbservice.model.Crop;
import com.agriyo.services.agriyodb.agriyodbservice.model.ProductCategory;
public interface CropRepository extends JpaRepository<Crop, Integer> {

	List<Crop> findByCategoryId(Integer categoryId);
	List<Crop> findByCropName(String cropName);
	


}

