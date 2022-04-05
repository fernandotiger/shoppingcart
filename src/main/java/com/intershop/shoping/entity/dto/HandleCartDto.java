package com.intershop.shoping.entity.dto;

import java.util.HashSet;
import java.util.Set;

import com.intershop.shoping.entity.OrderItemEntity;

public class HandleCartDto {

	private Long cartId;
	
	private Long productId;
	
	private Integer quantity;
	
	private Long clientId;
	
	private Set<OrderItemEntity> orderItemList = new HashSet<>();

	public HandleCartDto(Long cartId, Long productId,  Long clientId, Integer quantity) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.quantity = quantity;
		this.clientId = clientId;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Set<OrderItemEntity> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(Set<OrderItemEntity> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
	
	
}
