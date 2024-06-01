package com.OrgLance.Item;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderEntity 
{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderID;
	
	private Long customerId;
	
	private String orderedItem;
	private double price;
	
	
	public OrderEntity() {
			
	}
	
	


	public Long getOrderID() {
		return orderID;
	}


	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public String getOrderedItem() {
		return orderedItem;
	}


	public void setOrderedItem(String orderedItem) {
		this.orderedItem = orderedItem;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}	
}
