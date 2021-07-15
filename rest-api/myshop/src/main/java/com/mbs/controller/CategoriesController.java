package com.mbs.controller;

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
import com.mbs.service.CategoriesService;

@RestController
@CrossOrigin("*")
public class CategoriesController {
	
	@Autowired
	private CategoriesService service;
	
	//add categories
    @PostMapping("/category")
    public ResponseEntity<?> addCategories(@RequestBody Categories categories) {
        Categories categories1 = this.service.saveCategories(categories);

        return ResponseEntity.ok(categories1);
    }
    //get all categories
    @GetMapping("/category")
    public ResponseEntity<?> getAllCategories() throws Exception {    
    	
    	return ResponseEntity.ok( this.service.getAllCategories());
    }
    //get single categories
    @GetMapping("/category/{id}")
    public Categories getCategory(@PathVariable("id") Long id) {
        return this.service.getCategory(id);
    }
    //update category
    @PutMapping("/category")
    public Categories updateCategory(@RequestBody Categories categories) {
        return service.updateCategories(categories);
    }

    //delete category
    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        service.deleteCategories(id);

    }
    

}
