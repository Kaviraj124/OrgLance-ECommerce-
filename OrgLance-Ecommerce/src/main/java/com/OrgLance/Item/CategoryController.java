package com.OrgLance.Item;

import java.util.List;
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
@RequestMapping("/api/categories") 
public class CategoryController
{
	 @Autowired
	 private CategoryService categoryService;
	 
	 @PostMapping
	 public ResponseEntity<String> createCategory(@RequestBody CategoryEntity category) 
	 {
		 CategoryEntity newCategory = categoryService.createCategory(category);
		 if(!newCategory.equals(null))
		 {
	     return new ResponseEntity<>("Category Created Successfully", HttpStatus.CREATED);
		 }
		 else
			 return new ResponseEntity<String>("Category not added", HttpStatus.NOT_ACCEPTABLE );
	 }
	 
	 @GetMapping
	 public ResponseEntity<List<CategoryEntity>> getAllCategories()
	 {
		 List<CategoryEntity> category = categoryService.getAllCategories();
		 if(!category.equals(null))
		 {
			 return new ResponseEntity<List<CategoryEntity>>(category,HttpStatus.FOUND);
		 } 
		 else
		 {
			 return new ResponseEntity<List<CategoryEntity>>(HttpStatus.NOT_FOUND);
		 }
		 
	 }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<List<ItemEntity>> getItems(@PathVariable Long id)
	 {
		 List<ItemEntity> items = categoryService.getItemByCategoriesId(id);
		 if(!items.equals(null))
		 {
			 return new ResponseEntity<List<ItemEntity>>(items, HttpStatus.FOUND);
		 }
		 return new ResponseEntity<List<ItemEntity>>(HttpStatus.NOT_FOUND);
	 }
	 
	 
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody CategoryEntity updatedCategory)
	 {
		 CategoryEntity updated = categoryService.updateCateegory(id, updatedCategory);
		 if(!updated.equals(null))
		 {
			 return new ResponseEntity<String>("Updated Successfully",HttpStatus.ACCEPTED);
		 }
		 return new ResponseEntity<String>("Not updated",HttpStatus.NOT_ACCEPTABLE);
	 }
	 
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<String> deleteCategory(@PathVariable Long id)
	 {
		 boolean deleted = categoryService.deleteCategory(id);
		 if(deleted)
			 return new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);
		 else 
			 return new ResponseEntity<String>("Not Deleted", HttpStatus.NOT_FOUND);
		 
	 }
	 
	 
	 
}
