package com.ecommerce.sb_ecom.controller;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import jdk.jfr.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.atomic.AtomicLong;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/public/categories")
public class CategoryController {




    @Autowired
    private CategoryService categoryService;



    @GetMapping
   public ResponseEntity<List<Category>> getAllCategories(){

        List<Category> categories = categoryService.getAllCategories();
        return  new ResponseEntity<>(categories,HttpStatus.OK);
   }

   @PostMapping
    public  ResponseEntity<String> createCategory(@RequestBody Category category){
      // category.setCategoryId(idCounter.incrementAndGet());
       categoryService.createCategory(category);
       return new ResponseEntity<>("Category added successfully",HttpStatus.CREATED);
   }

   @DeleteMapping("/{categoryId}")
   public ResponseEntity<String> DeleteCategory(@PathVariable Long categoryId){

        try {
            String status = categoryService.deleteCategory(categoryId);
           // return new ResponseEntity<>(status, HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
   }

    @PutMapping("/{categoryId}")
    public  ResponseEntity<String> createCategory(@RequestBody Category category,
                                                  @PathVariable Long categoryId){
        try{
            Category savedCategory = categoryService.updateCategory(category,categoryId);
             return new ResponseEntity<>("Category with category id: " + categoryId, HttpStatus.OK);


        }catch(ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }

}
