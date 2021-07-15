package com.mbs.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbs.model.MyCart;
import com.mbs.repository.MyCartRepository;
import com.mbs.service.MyCartService;

@Service
public class MyCartServiceImpl implements MyCartService{
	
	@Autowired
	private MyCartRepository repository;

	@Override
	public List<MyCart> getAllMyCarts() throws Exception {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public MyCart getMyCart(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public MyCart saveMyCart(MyCart cart) {
		// TODO Auto-generated method stub
		return repository.save(cart);
	}

	@Override
	public MyCart updateMyCart(MyCart cart) {
		// TODO Auto-generated method stub
		return repository.save(cart);
	}

	@Override
	public void deleteMyCart(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public List<MyCart> getAllMyCartsByUserId(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.repository.findByUserId(id);
	}

	@Override
	public MyCart getMyCartByPId(Long id) {
		// TODO Auto-generated method stub
		return this.repository.findByProductId(id);
	}

}
