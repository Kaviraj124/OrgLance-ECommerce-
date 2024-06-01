package com.OrgLance.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryService
{
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public CategoryEntity createCategory(CategoryEntity category)
	{
		return categoryRepository.save(category);
	}

	public List<CategoryEntity> getAllCategories() 
	{
	   return categoryRepository.findAll();
	}

	public Optional<CategoryEntity> getById(Long id)
	{
		return categoryRepository.findById(id);
	}
 
	public CategoryEntity updateCateegory(Long id, CategoryEntity updatedCategory) 
	{
		CategoryEntity category = categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Category Not Found"));
		category.setName(updatedCategory.getName());
		return category;
	}
 
	
	public boolean deleteCategory(Long id) 
	{
		if(categoryRepository.existsById(id))
		{
			categoryRepository.deleteById(id);
			return true;
		}
		return false;
	}

	
	List<ItemEntity> itemsByCategories = new ArrayList<>();

	public List<ItemEntity> getItemByCategoriesId(Long id) 
	{
		 List<ItemEntity> allItems = itemRepository.findAll();
	        return allItems.stream()
	                .filter(item -> item.getCategory().getId().equals(id))
	                .collect(Collectors.toList());
	  		
	}
	
}
