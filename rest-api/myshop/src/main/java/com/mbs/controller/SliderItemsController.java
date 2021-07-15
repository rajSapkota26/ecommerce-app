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

import com.mbs.model.SliderItems;
import com.mbs.service.SliderItemsService;

@RestController
@CrossOrigin("*")
public class SliderItemsController {

	@Autowired
	private SliderItemsService service;

	// add slider
	@PostMapping("/slider")
	public ResponseEntity<?> addSlider(@RequestBody SliderItems item) {
		SliderItems addItems = this.service.addItems(item);
		return ResponseEntity.ok(addItems);
	}

	// get all slider
	@GetMapping("/slider")
	public ResponseEntity<?> getAllSlider() {
		List<SliderItems> allItems = this.service.getAllItems();
		Collections.shuffle(allItems);
		return ResponseEntity.ok(allItems);
	}

	// getSingle slider
	@GetMapping("/slider/{id}")
	public ResponseEntity<?> getSlider(@PathVariable("id") Long id) {

		return ResponseEntity.ok(this.service.getItems(id));
	}

	// update
	@PutMapping("/slider")
	public ResponseEntity<?> updateSlider(@RequestBody SliderItems item) {
		SliderItems addItems = this.service.updateItems(item);
		return ResponseEntity.ok(addItems);
	}

	@DeleteMapping("/slider/{id}")
	public void deleteSlider(@PathVariable("id") Long id) {
		this.service.deleteItems(id);

	}

}
