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

import com.mbs.model.MyCart;
import com.mbs.service.MyCartService;

@RestController
@CrossOrigin("*")
public class MyCartController {

	@Autowired
	private MyCartService service;

	// add cart
	@PostMapping("/myCart")
	public ResponseEntity<?> addCarts(@RequestBody MyCart cart) {
		if(cart.getQuantities()==0) {
			cart.setQuantities(1);
		}

		MyCart m = this.service.getMyCartByPId((cart.getProductId()));
		

		if (m != null) {
			int quantity = cart.getQuantities() + m.getQuantities();
			m.setQuantities(quantity);
			m=	this.service.updateMyCart(m);
			return ResponseEntity.ok(m);
		}else {
			MyCart cart1 = this.service.saveMyCart(cart);
			return ResponseEntity.ok(cart1);
		}

		
	}

	// get all cart
	@GetMapping("/myCart")
	public ResponseEntity<?> getAllCarts() throws Exception {

		return ResponseEntity.ok(this.service.getAllMyCarts());
	}

	// get all cart
	@GetMapping("/myCart/all/{id}")
	public ResponseEntity<?> getAllCartsByUserId(@PathVariable("id") String id) throws Exception {

		return ResponseEntity.ok(this.service.getAllMyCartsByUserId(id));
	}

	// get single cart
	@GetMapping("/myCart/{id}")
	public MyCart getCart(@PathVariable("id") Long id) {
		return this.service.getMyCart(id);
	}

	// update cart
	@PutMapping("/myCart")
	public MyCart updateCart(@RequestBody MyCart cart) {
		return service.updateMyCart(cart);
	}

	// delete cart
	@DeleteMapping("/myCart/{id}")
	public void deleteCart(@PathVariable("id") Long id) {
		service.deleteMyCart(id);

	}

}
