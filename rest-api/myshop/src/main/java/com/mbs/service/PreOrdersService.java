package com.mbs.service;

import java.util.List;

import com.mbs.model.PreOrders;

public interface PreOrdersService {
	public List<PreOrders> getAllMyOrders() throws Exception;
	public List<PreOrders> getAllMyOrdersByUserId(String id) throws Exception;

	public PreOrders getMyOrders(Long id);
	public PreOrders getMyOrdersByStatus(String sts);

	public PreOrders saveMyOrders(PreOrders order);

	public PreOrders updateMyOrders(PreOrders order);

	public void deleteMyOrders(Long id);
}
