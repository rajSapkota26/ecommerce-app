package com.mbs.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mbs.model.Categories;
import com.mbs.model.Product;
import com.mbs.service.ProductService;

@RestController
@CrossOrigin("*")
public class ProductController {

	@Autowired
	private ProductService service;	
	
	
	//add product
    @PostMapping("/product")
    public ResponseEntity<?> addProducts(@RequestBody Product product) {
    	Product pro = this.service.saveProduct(product);
        return ResponseEntity.ok(pro);
    }
    
    
    //get all product
    @GetMapping("/product")
    public ResponseEntity<?> getAllProducts() throws Exception {      	    	
    	List<Product> products = this.service.getAllProduct();
        Collections.shuffle(products);
        return ResponseEntity.ok(products);
    }
    
  //get all product by categories
    @GetMapping("/product/all/{id}")
    public ResponseEntity<?> getAllProductByCategory(@PathVariable("id") Long id) {
       Categories cat=new Categories();
        cat.setId(id);

        List<Product> products = this.service.getAllProductByCategories(cat);
        Collections.shuffle(products);
        return ResponseEntity.ok(products);
    }
    
    //get single product
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return this.service.getProduct(id);
    }
    
    //update product
    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product) {
        return service.updateProduct(product);
    }

    
    //delete product
    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
    	
        this.service.deleteProduct(id);

    }
}
