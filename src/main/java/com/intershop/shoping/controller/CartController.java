package com.intershop.shoping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intershop.shoping.entity.dto.HandleCartDto;
import com.intershop.shoping.exception.NotFoundException;
import com.intershop.shoping.service.CartService;


@RestController
@RequestMapping(value = "/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	public ControllerResponse addProduct(Long cartId, Long productId, Long clientId, Integer quantity) {
		ControllerResponse response = new ControllerResponse();
		try {
			response.getData().put("cart", cartService.add(new HandleCartDto(cartId, productId, clientId, quantity)));
		} catch (NotFoundException e) {
			response.setSuccess(Boolean.FALSE);
			response.setMessage(e.getMessage());
		}
		
		return response;
	}
	
	public ControllerResponse addProduct(Long productId, Long clientId, Integer quantity) {
		return addProduct(null, productId,  clientId,  quantity);
	}
	
	public ControllerResponse startOrder(Long cartId)   {
		ControllerResponse response = new ControllerResponse();
		try {
			response.getData().put("newOrder", cartService.startOrder(cartId));
		} catch (NotFoundException e) {
			response.setSuccess(Boolean.FALSE);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	public ControllerResponse removeProduct(Long cartId, Long clientId, Long productId)   {
		ControllerResponse response = new ControllerResponse();
		try {
			response.getData().put("cart", cartService.removeProduct(cartId, clientId, productId));
		} catch (NotFoundException e) {
			response.setSuccess(Boolean.FALSE);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	
	
}
