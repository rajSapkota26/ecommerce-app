package com.mbs.service;

import java.util.List;

import com.mbs.model.*;

public interface ProductService {

	public List<Product> getAllProduct();

	public List<Product> getAllProductByCategories(Categories category);

	public Product getProduct(Long id);

	public Product saveProduct(Product product);

	public Product updateProduct(Product product);

	public void deleteProduct(Long id);
}
