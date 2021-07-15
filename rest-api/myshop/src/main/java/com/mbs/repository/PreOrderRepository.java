package com.mbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbs.model.PreOrders;

public interface PreOrderRepository extends JpaRepository<PreOrders, Long>{
	
	List<PreOrders> findByUserId(String id);
	PreOrders findByStatus(String status);

}
