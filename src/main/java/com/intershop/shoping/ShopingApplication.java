package com.intershop.shoping;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.intershop.shoping.controller.CartController;
import com.intershop.shoping.controller.ClientController;
import com.intershop.shoping.controller.ControllerResponse;
import com.intershop.shoping.controller.ProductController;
import com.intershop.shoping.entity.CartEntity;
import com.intershop.shoping.entity.dto.ClientDto;
import com.intershop.shoping.entity.dto.ProductDto;
import com.intershop.shoping.entity.enums.ProductCategoryEnum;

@SpringBootApplication
public class ShopingApplication {

	@Autowired
	public  ClientController clientController;	
	
	@Autowired
	public  ProductController productController;
	
	@Autowired
	public  CartController cartController;
	
	public static void main(String[] args) {
		SpringApplication.run(ShopingApplication.class, args);
		
	}
	
	@PostConstruct
    public void initiate() { 
		ClientDto newClient = clientController.save(new ClientDto("Fernando","fernndo@test.com"));
		
		ProductDto iPhone = productController.save(new ProductDto("iPhone", "telephone for call", ProductCategoryEnum.ELECTRONIC, new BigDecimal("1000")));
		ProductDto runners =productController.save(new ProductDto("Runners", "shoes for running", ProductCategoryEnum.SPORT, new BigDecimal("20.50")));
		
		cartController.addProduct( iPhone.getId(), newClient.getId(), 2);
		ControllerResponse cartResponse = cartController.addProduct( runners.getId(), newClient.getId(), 2);
		
		CartEntity cart = ((CartEntity)cartResponse.getData().get("cart"));
		
		System.out.println("\n\n *****************Showing the Results**************************** ");
		showOnConsole( cart) ;
		
		// changing product quantity
		cartResponse = cartController.addProduct( runners.getId(), newClient.getId(), 4);
		System.out.println("\n\n *****************Showing new Results after updating quantity**************************** ");
		cart = ((CartEntity)cartResponse.getData().get("cart"));
		showOnConsole( cart) ;
    }
	
	// Only for visualizations purpose
	private void showOnConsole(CartEntity cart) {
		System.out.println(" *****************Cart Content**************************** ");
		
		cart.getOrderItemList().forEach(item -> {
			System.out.println(item.getId().getProduct().getName()+" : "+item.getQuantity()+" : "+item.getId().getProduct().getPrice());
		});
		
		System.out.println("Total: "+cart.calculateTotal());
		System.out.println("\n\n ");
	}
	
	
}
