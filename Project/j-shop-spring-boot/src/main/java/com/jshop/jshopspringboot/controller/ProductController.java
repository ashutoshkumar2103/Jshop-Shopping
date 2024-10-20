package com.jshop.jshopspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jshop.jshopspringboot.dto.Product;
import com.jshop.jshopspringboot.responsestructure.ResponseStructure;
import com.jshop.jshopspringboot.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	/*
	 * save Product
	 */
	@PostMapping("/saveProduct")
	public ResponseStructure<Product> saveProductController(@RequestBody Product product) {
		
		return productService.saveProductService(product);
	}
	
	/*
	 * updateProductController
	 */
	@PutMapping("/updateProduct")
	public ResponseStructure<Product> updateProductController(@RequestBody Product product){
		return productService.updateProductService(product);
	}

	/*
	 * deleteProductController
	 */
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseStructure<Product> deleteProductController(@PathVariable int id){
		return productService.deleteProductService(id);
	}
}
