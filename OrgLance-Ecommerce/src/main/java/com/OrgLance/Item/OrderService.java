package com.OrgLance.Item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.persistence.criteria.Order;

@Service
public class OrderService
{
	@Autowired
	private OrderRepository orderRepository;
	
	public OrderEntity createOrder(OrderEntity entity)
	{
		return orderRepository.save(entity);
	}
	
	public List<OrderEntity> getOrders()
	{
		return orderRepository.findAll();
	}
	
	public OrderEntity getOrderById(Long id)
	{
		return orderRepository.findById(id).orElse(null);
	}
	
	public OrderEntity updateOrder(Long id, OrderEntity updatedOrder)
	{
		OrderEntity order =  orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order Not Found"));
		
		order.setOrderedItem(updatedOrder.getOrderedItem());
		order.setPrice(updatedOrder.getPrice());
		return order;
	}
	
	public boolean deleteOrder(Long id)
	{
		if(orderRepository.existsById(id))
		{	
			orderRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
