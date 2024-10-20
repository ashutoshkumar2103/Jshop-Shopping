package com.jshop.jshopspringboot.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jshop.jshopspringboot.dto.Product;
import com.jshop.jshopspringboot.repository.ProductRepository;

@Repository
public class ProductDao {

	@Autowired
	private ProductRepository productRepository;
	
	/*
	 * save Product
	 */
	public Product saveProductDao(Product product) {
		return productRepository.save(product);
	}
	
	/*
	 * getProductDataByid
	 */
	public Product getProductByIdDao(int productId) {
		
		Optional<Product> optional=productRepository.findById(productId);
		
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
	
	/*
	 * updateProductDao
	 */
	public Product updateProductDao(Product product) {
		Product existingProduct = productRepository.findByProductId(product.getProductId());
		existingProduct.setProductName(product.getProductName());
		existingProduct.setProductBrand(product.getProductBrand());
		existingProduct.setProductPrice(product.getProductPrice());
		existingProduct.setProudctQuantity(product.getProudctQuantity());
		existingProduct.setProductVerified("no");
		Product updateProduct = productRepository.save(existingProduct);
		return updateProduct;
	}
	
	/*
	 * deleteProductDao
	 */
	public void deleteProductDao(int id) {
		Product exixtingId = productRepository.findByProductId(id);
		productRepository.delete(exixtingId);
	}
	
}
