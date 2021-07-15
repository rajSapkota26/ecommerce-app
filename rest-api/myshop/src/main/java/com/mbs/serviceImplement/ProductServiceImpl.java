package com.mbs.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbs.model.Categories;
import com.mbs.model.Product;
import com.mbs.repository.ProductRepository;
import com.mbs.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository repository;

	@Override
	public List<Product> getAllProduct() {
		return this.repository.findAll();
	}

	@Override
	public List<Product> getAllProductByCategories(Categories category) {
		return this.repository.findByCategory(category);
	}

	@Override
	public Product getProduct(Long id) {
		return this.repository.findById(id).get();
	}

	@Override
	public Product saveProduct(Product product) {
		return this.repository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return this.repository.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
	this.repository.deleteById(id);
		System.out.println("done");
		
	}

}
