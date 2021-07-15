package com.mbs.service;

import java.util.List;

import com.mbs.model.WishList;

public interface WishListService {
	public List<WishList> getAllWishList() throws Exception;

	public List<WishList> getAllWishListByUserId(String id) throws Exception;

	public WishList getWishList(Long id);

	public WishList saveWishList(WishList wishlist);

	public WishList updateWishList(WishList wishlist);

	public void deleteWishList(Long id);
}
