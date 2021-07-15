package com.mbs.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbs.model.Categories;
import com.mbs.repository.CategoriesRepository;
import com.mbs.service.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService{
	
	@Autowired
	private CategoriesRepository repository;

	@Override
	public List<Categories> getAllCategories() throws Exception{
		return this.repository.findAll();
	}

	@Override
	public Categories getCategory(Long id)  {
		return this.repository.findById(id).get();
	}

	@Override
	public Categories saveCategories(Categories category) {
		return this.repository.save(category);
	}

	@Override
	public Categories updateCategories(Categories category) {
		return this.repository.save(category);
	}

	@Override
	public void deleteCategories(Long id) {
		this.repository.deleteById(id);
		
	}

}
