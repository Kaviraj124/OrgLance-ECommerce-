package com.OrgLance.Item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/items")
public class ItemController 
{
	@Autowired
	private ItemService itemService;
	
	@PostMapping
	public ResponseEntity<String> createItem(@RequestBody ItemEntity item)
	{
		ItemEntity newItem = itemService.createItem(item);
		if(!newItem.equals(null))
			return new ResponseEntity<String>("Item Added",HttpStatus.CREATED);
		else
			return new ResponseEntity<String>("Item not Added",HttpStatus.NOT_ACCEPTABLE);
	}
	
	@GetMapping
	public ResponseEntity <List<ItemEntity>> getAllItem()
	{
		List<ItemEntity> items = itemService.getAllItem();
		if(!items.equals(null))
		{
			return new ResponseEntity<List<ItemEntity>>(items, HttpStatus.FOUND);
		}
		else
			return new ResponseEntity<List<ItemEntity>>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemEntity> getItemById(@PathVariable Long id)
	{
		ItemEntity item = itemService.getItemById(id).orElseThrow(() -> new RuntimeException("Item Not Found"));
		if(!item.equals(null))
			return new ResponseEntity<ItemEntity>(item, HttpStatus.OK);
		else
			return new ResponseEntity<ItemEntity>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ItemEntity> updateItem(@PathVariable Long id, @RequestBody ItemEntity itemDetails)
	{
		ItemEntity updatedItem = itemService.updateItem(id, itemDetails);
		if(!updatedItem.equals(null))
			return new ResponseEntity<ItemEntity>(updatedItem, HttpStatus.OK);
		else
			return new ResponseEntity<ItemEntity>(HttpStatus.NOT_MODIFIED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable Long id)
	{
		boolean deleted = itemService.deleteItem(id);
		if(deleted)
			return new ResponseEntity<String>("Item Deleted",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Item not deleted", HttpStatus.NOT_FOUND);
	}
}
