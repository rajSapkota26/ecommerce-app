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

import com.mbs.model.WishList;
import com.mbs.service.WishListService;

@RestController
@CrossOrigin("*")
public class WishListController {
	
	@Autowired
	private WishListService service;
	
	//add wishList
    @PostMapping("/wishList")
    public ResponseEntity<?> addwishList(@RequestBody WishList whishList) {
    	WishList whishList1 = this.service.saveWishList(whishList);

        return ResponseEntity.ok(whishList1);
    }
    //get all wishList
    @GetMapping("/wishList")
    public ResponseEntity<?> getAllwishList() throws Exception {    
    	
    	return ResponseEntity.ok( this.service.getAllWishList());
    }
    //get all wishList
    @GetMapping("/wishList/all/{id}")
    public ResponseEntity<?> getAllwishListByUserId(@PathVariable("id") String id) throws Exception {    
    	
    	return ResponseEntity.ok( this.service.getAllWishListByUserId(id));
    }
    //get single wishList
    @GetMapping("/wishList/{id}")
    public WishList getwishList(@PathVariable("id") Long id) {
        return this.service.getWishList(id);
    }
    //update wishList
    @PutMapping("/wishList")
    public WishList updatewishList(@RequestBody WishList categories) {
        return service.updateWishList(categories);
    }

    //delete wishList
    @DeleteMapping("/wishList/{id}")
    public void deletewishList(@PathVariable("id") Long id){
        service.deleteWishList(id);

    }

}
