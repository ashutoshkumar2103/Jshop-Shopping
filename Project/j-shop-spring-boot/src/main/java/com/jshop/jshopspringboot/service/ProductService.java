package com.jshop.jshopspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jshop.jshopspringboot.dao.ProductDao;
import com.jshop.jshopspringboot.dao.ProductOwnerDao;
import com.jshop.jshopspringboot.dto.Product;
import com.jshop.jshopspringboot.dto.ProductOwner;
import com.jshop.jshopspringboot.responsestructure.ResponseStructure;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductService{
	
	@Autowired
	private ResponseStructure<Product> responseStructure;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private ProductOwnerDao ownerDao;
	
	
	
	/*
	 * save Product
	 */
	public ResponseStructure<Product> saveProductService(Product product) {
		
		if(httpSession.getAttribute("email")!=null) {
			
			
			ProductOwner productOwner=ownerDao.getProductOwnerById(ownerDao.getProductOwnerId());
			
			if(productOwner!=null) {
				product.setProductOwner(productOwner);
			}
			product.setProductVerified("no");
			
			Product product1=productDao.saveProductDao(product);
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setStatusMsg("Product Addedd");
			responseStructure.setDescription("Now your product is added....your login session is valid for 200 seconds");
			responseStructure.setData(product1);
		}
		else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setStatusMsg("Your session is out ....please login");
			responseStructure.setDescription("only product owner can add product details if he/she is logged in");
			responseStructure.setData(null);
		}
		return responseStructure;
	}
	
	/*
	 * updateProductService
	 */
	public ResponseStructure<Product> updateProductService(Product product) {
		if(httpSession.getAttribute("email")!=null) {
			ProductOwner productOwner = ownerDao.getProductOwnerById(ownerDao.getProductOwnerId());
			Product product2 = productDao.updateProductDao(product);
			if(productOwner!=null) {
				product.setProductOwner(productOwner);
			}
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setStatusMsg("Product updated");
			responseStructure.setDescription("Now your product is updated....wait for admin to verify");
			responseStructure.setData(product2);
		}
		else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setStatusMsg("check details again and update again");
			responseStructure.setDescription("only product owner can add product details if he/she is logged in");
			responseStructure.setData(null);
		}
		return responseStructure;
	}
	
	/*
	 * deleteProductService
	 */
	public ResponseStructure<Product> deleteProductService(int id){
		if(httpSession.getAttribute("email")!=null) {
			ProductOwner productOwner = ownerDao.getProductOwnerById(ownerDao.getProductOwnerId());
			if(productOwner != null) {
				productDao.deleteProductDao(id);
			}
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setStatusMsg("Product deleted" + id);
			responseStructure.setDescription("Your product is deleted....");
			responseStructure.setData(null);
		}
		else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setStatusMsg("check Id again and delete again");
			responseStructure.setDescription("only product owner can delete product details if he/she is logged in");
			responseStructure.setData(null);
		}
		return responseStructure;
	}
	
	
}
