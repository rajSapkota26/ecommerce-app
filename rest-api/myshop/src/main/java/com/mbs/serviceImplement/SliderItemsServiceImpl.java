package com.mbs.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbs.model.SliderItems;
import com.mbs.repository.SliderItemsRepository;
import com.mbs.service.SliderItemsService;

@Service
public class SliderItemsServiceImpl implements SliderItemsService {

	@Autowired
	private SliderItemsRepository repository;
	@Override
	public List<SliderItems> getAllItems() {
		
		return this.repository.findAll();
	}

	@Override
	public SliderItems addItems(SliderItems item) {
		
		return this.repository.save(item);
	}

	@Override
	public SliderItems getItems(Long id) {
		
		return this.repository.findById(id).get();
	}

	@Override
	public SliderItems updateItems(SliderItems item) {
		
		return this.repository.save(item);
	}

	@Override
	public void deleteItems(Long id) {
		this.repository.deleteById(id);

	}

}
