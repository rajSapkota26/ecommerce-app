package com.mbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbs.model.*;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByCategory(Categories c);

}
