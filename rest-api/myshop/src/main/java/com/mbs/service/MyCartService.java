package com.mbs.service;

import java.util.List;

import com.mbs.model.MyCart;

public interface MyCartService {

	public List<MyCart> getAllMyCarts() throws Exception;
	public List<MyCart> getAllMyCartsByUserId(String id) throws Exception;

	public MyCart getMyCart(Long id);
	public MyCart getMyCartByPId(Long id);

	public MyCart saveMyCart(MyCart cart);

	public MyCart updateMyCart(MyCart cart);

	public void deleteMyCart(Long id);

}
