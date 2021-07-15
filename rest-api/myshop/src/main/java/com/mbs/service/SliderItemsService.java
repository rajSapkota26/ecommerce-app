package com.mbs.service;

import java.util.List;

import com.mbs.model.SliderItems;

public interface SliderItemsService {
	
	public List<SliderItems> getAllItems();
	
	public SliderItems addItems(SliderItems item);
	public SliderItems getItems(Long id);
	public SliderItems updateItems(SliderItems item);
	public void deleteItems(Long id);

}
