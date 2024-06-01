package com.OrgLance.Item;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService 
{
	private ItemRepository itemRepository;
	
	public ItemService(ItemRepository itemRepository)
	{
		this.itemRepository = itemRepository;
	}
	
	public ItemEntity createItem(ItemEntity item)
	{
		return itemRepository.save(item);
	}
	
	public List<ItemEntity> getAllItem()
	{
		return itemRepository.findAll();
	}
	
	public Optional<ItemEntity> getItemById(Long id)
	{
		return itemRepository.findById(id);
	}
	
	public ItemEntity updateItem(Long id, ItemEntity itemDetails)
	{
		ItemEntity item	= itemRepository.findById(id).orElseThrow(()-> new RuntimeException("Item not Found"));
		
		item.setName(itemDetails.getName());
		item.setDescription(itemDetails.getDescription());
		item.setPrice(itemDetails.getPrice());
	
		return itemRepository.save(item);
	}

	public boolean deleteItem(Long id) 
	{
		if(itemRepository.existsById(id))
		{	
			itemRepository.deleteById(id);
			return true;
		}
		else
			return false;
	}
}
