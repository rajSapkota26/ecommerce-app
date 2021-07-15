package com.mbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mbs.model.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long>{
	List<WishList> findByUserId(String id);

}
