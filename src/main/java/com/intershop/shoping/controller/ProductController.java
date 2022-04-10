package com.intershop.shoping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intershop.shoping.entity.dto.ProductDto;
import com.intershop.shoping.exception.NotFoundException;
import com.intershop.shoping.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<ProductDto> getProducts(String textFilter){
		
		return productService.findProducts(textFilter);
	}
	
	@PostMapping
	public ProductDto save( ProductDto productDto){
		
		return productService.save(productDto);
	}
	
	@GetMapping("/{id}")
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
	
	@PutMapping
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
	
	@DeleteMapping
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
