package com.mbs.service;

import java.util.List;

import com.mbs.model.Categories;

public interface CategoriesService {
	
	public List<Categories> getAllCategories() throws Exception;
	
	public Categories getCategory(Long id) ;
	
	public Categories saveCategories(Categories category);
	
	public Categories updateCategories(Categories category);
	
	public void deleteCategories(Long id);

}
