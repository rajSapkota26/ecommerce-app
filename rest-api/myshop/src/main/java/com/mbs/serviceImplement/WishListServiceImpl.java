package com.mbs.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbs.model.WishList;
import com.mbs.repository.WishListRepository;
import com.mbs.service.WishListService;

@Service
public class WishListServiceImpl implements WishListService{
	
	@Autowired
	private WishListRepository repository;

	@Override
	public List<WishList> getAllWishList() throws Exception {
		
		return this.repository.findAll();
	}

	@Override
	public WishList getWishList(Long id) {
		
		return this.repository.findById(id).get();
	}

	@Override
	public WishList saveWishList(WishList wishlist) {
		
		return this.repository.save(wishlist);
	}

	@Override
	public WishList updateWishList(WishList wishlist) {
	
		return this.repository.save(wishlist);
	}

	@Override
	public void deleteWishList(Long id) {
		this.repository.deleteById(id);
		
	}

	@Override
	public List<WishList> getAllWishListByUserId(String id) throws Exception {
	
		return this.repository.findByUserId(id);
	}

}
