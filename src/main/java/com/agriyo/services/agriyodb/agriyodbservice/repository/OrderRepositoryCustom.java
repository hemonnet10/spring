package com.agriyo.services.agriyodb.agriyodbservice.repository;

import java.util.List;
import java.util.Set;

import com.agriyo.services.agriyodb.agriyodbservice.model.Order;
import com.agriyo.services.agriyodb.agriyodbservice.model.OrderAssign;
import com.agriyo.services.agriyodb.agriyodbservice.model.User;

public interface OrderRepositoryCustom  {
	Set<Order> findAllAssginedOrders(Integer userId);
	List<Order> findByOrderByAssignedId(Integer farmerId);
	void saveAssignedOrder(List<OrderAssign> OrderAssign);
	
	
	
}

