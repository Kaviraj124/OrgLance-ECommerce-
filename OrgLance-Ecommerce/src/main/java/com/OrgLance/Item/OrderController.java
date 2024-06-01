package com.OrgLance.Item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController 
{
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<String> createOrder(@RequestBody OrderEntity entity)
	{
		OrderEntity order = orderService.createOrder(entity);
		if(!order.equals(null))
			return new ResponseEntity<String>("Order Added Succesfully", HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<String>("Order Can not be added", HttpStatus.NOT_ACCEPTABLE);
	}
	
	@GetMapping
	public ResponseEntity <List<OrderEntity>> retriveOrders()
	{
		List<OrderEntity> orders = orderService.getOrders();
		
		if(!orders.equals(null))
			return new ResponseEntity<List<OrderEntity>>(orders, HttpStatus.FOUND);
		else
			return new ResponseEntity<List<OrderEntity>>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderEntity> retriveOrderById(@PathVariable Long id)
	{
		OrderEntity order = orderService.getOrderById(id);
		if(!order.equals(null))		
			return new ResponseEntity<OrderEntity>(order, HttpStatus.FOUND);
		else
			return new ResponseEntity<OrderEntity>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrderEntity> updateOrder(@PathVariable Long id, @RequestBody OrderEntity updated)
	{
		OrderEntity order =	orderService.updateOrder(id, updated);
		if(!order.equals(null))	
			return new ResponseEntity<OrderEntity>(order, HttpStatus.OK);
		else
			return new ResponseEntity<OrderEntity>(HttpStatus.NOT_MODIFIED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> cancelOrder(@PathVariable Long id)
	{
	    boolean deleted = orderService.deleteOrder(id);
	    if(deleted)
	    	return new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);
	    else 
			 return new ResponseEntity<String>("Not Deleted", HttpStatus.NOT_FOUND);
	}
}
