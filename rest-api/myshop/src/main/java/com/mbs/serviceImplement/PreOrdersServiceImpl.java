package com.mbs.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbs.model.PreOrders;
import com.mbs.repository.PreOrderRepository;
import com.mbs.service.PreOrdersService;

@Service
public class PreOrdersServiceImpl implements PreOrdersService{
	
	@Autowired
	private PreOrderRepository repository;

	@Override
	public List<PreOrders> getAllMyOrders() throws Exception {
		
		return this.repository.findAll();
	}

	@Override
	public List<PreOrders> getAllMyOrdersByUserId(String id) throws Exception {
	
		return this.repository.findByUserId(id);
	}

	@Override
	public PreOrders getMyOrders(Long id) {
	
		return this.repository.findById(id).get();
	}

	@Override
	public PreOrders getMyOrdersByStatus(String sts) {
		
		return this.repository.findByStatus(sts);
	}

	@Override
	public PreOrders saveMyOrders(PreOrders order) {
		
		return this.repository.save(order);
	}

	@Override
	public PreOrders updateMyOrders(PreOrders order) {
	
		return this.repository.save(order);
	}

	@Override
	public void deleteMyOrders(Long id) {
		this.repository.deleteById(id);
		
	}

}
