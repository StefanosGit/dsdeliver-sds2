package com.devsuperior.dsdeliver.services;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.OrderStatus;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.OrderRepository;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

@Service
public class OrderService implements Serializable{
	
	private static final long serialVersionUID = -3078757633416552141L;
	
	private OrderRepository orderRepository;	
	private ProductRepository productRepository;
	
	@Autowired
	public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
			
		this.orderRepository = orderRepository;	
		this.productRepository = productRepository;
	}
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		
		List<Order> orders = this.orderRepository.findOrdersWithProducts();
		
		return orders.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
		
	}
	
	@Transactional
	public OrderDTO insert (OrderDTO orderDTO) {
		
		Order order = new Order(null, orderDTO.getAddress(), orderDTO.getLatitude()
				, orderDTO.getLongitude(), Instant.now(), OrderStatus.PENDING);
		
		for(ProductDTO productDTO:orderDTO.getProducts()) {
			
			Product product = productRepository.getOne(productDTO.getId());
			
			order.getProducts().add(product);
			
			
		}
		
		order = orderRepository.save(order);
		
		
		return new OrderDTO(order);
		
		
	}
	
	@Transactional
	public OrderDTO setDelivered(Long id) {
		
		Order order = orderRepository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		orderRepository.save(order);
		return new OrderDTO(order);
		
	}
		

}
