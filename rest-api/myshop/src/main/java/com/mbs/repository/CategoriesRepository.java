package com.mbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbs.model.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Long>{

}
