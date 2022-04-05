package com.intershop.shoping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.intershop.shoping.entity.dto.ProductDto;
import com.intershop.shoping.exception.NotFoundException;
import com.intershop.shoping.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	public List<ProductDto> getProducts(String textFilter){
		
		return productService.findProducts(textFilter);
	}
	
	public ProductDto save( ProductDto productDto){
		
		return productService.save(productDto);
	}
	
	public ControllerResponse getDetail(Long id){
		ControllerResponse response = new ControllerResponse();
		try {
			response.getData().put("productDetail", productService.productDetail(id));
		} catch (NotFoundException e) {
			response.setSuccess(Boolean.FALSE);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	public ControllerResponse updateProduct(Long id, ProductDto dto){
		ControllerResponse response = new ControllerResponse();
		try {
			response.getData().put("productDetail", productService.updateProduct(id, dto));
		} catch (NotFoundException e) {
			response.setSuccess(Boolean.FALSE);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	public ControllerResponse delete(Long id){
		ControllerResponse response = new ControllerResponse();
		try {
			productService.delete(id);
		} catch (NotFoundException e) {
			response.setSuccess(Boolean.FALSE);
			response.setMessage(e.getMessage());
		}
		return response;
	} 
}
