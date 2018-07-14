package com.agriyo.services.agriyodb.agriyodbservice.repository.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.agriyo.services.agriyodb.agriyodbservice.model.FarmerCrop;
import com.agriyo.services.agriyodb.agriyodbservice.model.Order;
import com.agriyo.services.agriyodb.agriyodbservice.model.OrderAssign;
import com.agriyo.services.agriyodb.agriyodbservice.model.User;
import com.agriyo.services.agriyodb.agriyodbservice.repository.OrderRepositoryCustom;

public class OrderRepositoryImpl implements OrderRepositoryCustom{

	@Autowired
	private EntityManager em;
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	
	@Override
	public List<Order> findByOrderByAssignedId(Integer farmerId) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> query = cb.createQuery(Order.class);
		Root<Order> order = query.from(Order.class);
		Root<OrderAssign> orderAssign = query.from(OrderAssign.class);
		Join<Order, Order> orderAssigns = order.join("orderAssigns");
		 query.select(order).where(cb.equal(orderAssign.get("userId"), farmerId));
		return em.createQuery(query).getResultList();
		
		
		
		
		
	}

	//@Transactional(propagation = Propagation.REQUIRED)
	@Transactional
	@Override
	public void saveAssignedOrder(List<OrderAssign> orderAssignList) {
		for(OrderAssign orderAssign:orderAssignList)
			em.persist(orderAssign);
	}

	@Override
	public Set<Order> findAllAssginedOrders(Integer userId) {
		Set<Order> orderSet=new HashSet<Order>();
		Query orderAssignQuery = em.createQuery(
	            "SELECT o FROM OrderAssign o WHERE user_id = :userId");
		orderAssignQuery.setParameter("userId",userId);
		
		
		List<OrderAssign> orderAssigns =orderAssignQuery.getResultList();
		
		if(orderAssigns!=null && !orderAssigns.isEmpty()) {
		List<Integer> orderIdList=new ArrayList();
		for(OrderAssign orderAssign:orderAssigns)
			orderIdList.add(orderAssign.getOrderId());
		Query orderQuery = em.createQuery(
	            "SELECT o FROM Order o WHERE id IN :orderIdList");
		orderQuery.setParameter("orderIdList",orderIdList);
		orderSet.addAll(orderQuery.getResultList());
		}
		
		return orderSet;
	}

	
}

