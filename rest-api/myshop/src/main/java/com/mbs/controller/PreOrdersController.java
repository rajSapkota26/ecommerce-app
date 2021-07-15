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

import com.mbs.model.PreOrders;
import com.mbs.service.PreOrdersService;

@RestController
@CrossOrigin("*")
public class PreOrdersController {
	
	@Autowired
	private PreOrdersService service;
	
	//add preOrder
    @PostMapping("/preOrder")
    public ResponseEntity<?> addPreOrder(@RequestBody PreOrders preOrder) {
    	preOrder.setDate(String.valueOf(java.time.LocalDate.now()));
    	
    	PreOrders categories1 = this.service.saveMyOrders(preOrder);

        return ResponseEntity.ok(categories1);
    }
    //get all PreOrder
    @GetMapping("/preOrder")
    public ResponseEntity<?> getAllPreOrder() throws Exception {    
    	
    	return ResponseEntity.ok( this.service.getAllMyOrders());
    }
    //get all PreOrder
    @GetMapping("/preOrder/all/{id}")
    public ResponseEntity<?> getAllPreOrderByUserId(@PathVariable("id") String id) throws Exception {    
    	
    	return ResponseEntity.ok( this.service.getAllMyOrdersByUserId(id));
    }
    //get single PreOrder
    @GetMapping("/preOrder/{id}")
    public PreOrders getPreOrder(@PathVariable("id") Long id) {
        return this.service.getMyOrders(id);
    }
    //update PreOrder
    @PutMapping("/preOrder")
    public PreOrders updatePreOrder(@RequestBody PreOrders categories) {
        return service.updateMyOrders(categories);
    }

    //delete PreOrder
    @DeleteMapping("/preOrder/{id}")
    public void deletePreOrder(@PathVariable("id") Long id){
        service.deleteMyOrders(id);

    }

}
