package com.devsuperior.dsdeliver.services;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.repositories.OrderRepository;

@Service
public class OrderService implements Serializable{
	
	private static final long serialVersionUID = -3078757633416552141L;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		
		List<Order> orders = orderRepository.findOrdersWithProducts();
		
		return orders.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
		
	}
	
	

}