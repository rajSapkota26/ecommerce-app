package com.mbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbs.model.MyCart;

public interface MyCartRepository extends JpaRepository<MyCart, Long>{
	
	List<MyCart> findByUserId(String id);
	MyCart findByProductId(Long id);

}
