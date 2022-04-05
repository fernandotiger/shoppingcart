package com.intershop.shoping.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intershop.shoping.entity.CartEntity;
import com.intershop.shoping.entity.ClientEntity;
import com.intershop.shoping.entity.OrderEntity;
import com.intershop.shoping.entity.OrderItemEntity;
import com.intershop.shoping.entity.ProductEntity;
import com.intershop.shoping.entity.dto.HandleCartDto;
import com.intershop.shoping.entity.enums.OrderStatusEnum;
import com.intershop.shoping.exception.NotFoundException;
import com.intershop.shoping.repository.CartRepository;
import com.intershop.shoping.repository.OrderRepository;

@Service
public class CartService {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ClientService clientService;
	
	public CartEntity save(CartEntity entity) {
		return cartRepository.save(entity);
	}
	
	public CartEntity add(HandleCartDto cartHandleDto) throws NotFoundException {
		ProductEntity product = productService.findById(cartHandleDto.getProductId());
		CartEntity cart = null;

		if(Objects.isNull(cartHandleDto.getCartId())) {
			cart = getCartByClient(cartHandleDto.getClientId());
		} else {
			Optional<CartEntity> optionalCart = cartRepository.findById(cartHandleDto.getCartId());
			if(!optionalCart.isPresent() ) {
				throw new NotFoundException("Product details not found");
			}
			cart = optionalCart.get();
		}
		cart.addItem(new OrderItemEntity(cart, product, cartHandleDto.getQuantity()));
		return cartRepository.save(cart);
	
	}

	private CartEntity getCartByClient(Long clientId ) throws NotFoundException {
		ClientEntity client = clientService.findById(clientId);
		try {
			return findLatestCartByClient( client);
		}catch(NotFoundException e) {
			return cartRepository.save(new CartEntity(client, BigDecimal.ZERO, new ArrayList<OrderItemEntity>()));
		}
	}

	public OrderEntity startOrder(Long cartId) throws NotFoundException {
		Optional<CartEntity> optionalCart = cartRepository.findById(cartId);
		if(optionalCart.isPresent()) {
			CartEntity cart = optionalCart.get();
			cart.setStatus(OrderStatusEnum.COMPLETE);
			return orderRepository.save(new OrderEntity(cart.getClient(), cart.getOrderItemList(), cart.calculateTotal()));
		}
		throw new NotFoundException("Order details not found");
	}
	
	public CartEntity removeProduct(Long cartId, Long clientId, Long productId) throws NotFoundException {
		CartEntity cart = null;
		ProductEntity product = productService.findById(productId);
		if(Objects.nonNull(cartId)) {
			Optional<CartEntity> optionalCart = cartRepository.findById(cartId);
			if(optionalCart.isPresent()) {
				cart = optionalCart.get();
			}
		} else if(Objects.nonNull(clientId)){
			ClientEntity client = clientService.findById(clientId);
			cart = findLatestCartByClient( client);
		}
		if(Objects.nonNull(cart)) {
			cart.removeProduct(product);
			return cartRepository.save(cart);
		}
		throw new NotFoundException("Remove product not completed");
	}
	
	private CartEntity findLatestCartByClient(ClientEntity client) throws NotFoundException {
		Optional<CartEntity> optionalCart = cartRepository.findTopByOrderByClientIdDesc(client.getId());
		if(optionalCart.isPresent()) {
			return optionalCart.get();
		}
		throw new NotFoundException("Cart details not found");
	}
	
}
