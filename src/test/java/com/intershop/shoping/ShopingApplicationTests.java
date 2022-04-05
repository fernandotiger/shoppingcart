package com.intershop.shoping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.intershop.shoping.controller.CartController;
import com.intershop.shoping.controller.ClientController;
import com.intershop.shoping.controller.ControllerResponse;
import com.intershop.shoping.controller.ProductController;
import com.intershop.shoping.entity.CartEntity;
import com.intershop.shoping.entity.OrderEntity;
import com.intershop.shoping.entity.dto.ClientDto;
import com.intershop.shoping.entity.dto.ProductDto;
import com.intershop.shoping.entity.enums.ProductCategoryEnum;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShopingApplicationTests {

	@Autowired
	public  ClientController clientController;	
	
	@Autowired
	public  ProductController productController;
	
	@Autowired
	public  CartController cartController;
	
	@Test
	@Order(1)
	void test_create_new_product() { 
		
		ProductDto newProduct = productController.save(new ProductDto("Table", "table for dinner", ProductCategoryEnum.FURNITURE, BigDecimal.valueOf(50)));
		
		assertEquals("Table", newProduct.getName());
		assertEquals(true, newProduct.getId() > 0);
	}
	
	@Test
	@Order(2)
	void test_create_new_client() { 
		
		ClientDto newClient = clientController.save(new ClientDto("Jose","jose@test.com"));
		
		assertEquals("Jose", newClient.getName());
		assertEquals(true, newClient.getId() > 0);
	 }
	
	@Test
	@Order(3)
	void test_add_product() { 
		
		List<ProductDto> products = productController.getProducts("Table");
		assertEquals(1, products.size());
		
		List<ClientDto> clients = clientController.getClients("Jose");
		assertEquals(1, clients.size());
		
		ControllerResponse cartResponse = cartController.addProduct( products.get(0).getId(), clients.get(0).getId(), 2);
		
		CartEntity cart = ((CartEntity)cartResponse.getData().get("cart"));
		assertEquals(NumberFormat.getCurrencyInstance().format(BigDecimal.valueOf(100)), NumberFormat.getCurrencyInstance().format(cart.calculateTotal()));
		assertEquals(1, cart.getOrderItemList().size());
	}
	
	@Test
	@Order(4)
	void test_update_product_quantity() { 
		
		List<ProductDto> products = productController.getProducts("Table");
		assertEquals(1, products.size());
		
		List<ClientDto> clients = clientController.getClients("Jose");
		assertEquals(1, clients.size());
		
		ControllerResponse cartResponse = cartController.addProduct( products.get(0).getId(), clients.get(0).getId(), 3);
		
		CartEntity cart = ((CartEntity)cartResponse.getData().get("cart"));
		assertEquals(NumberFormat.getCurrencyInstance().format(BigDecimal.valueOf(150)), NumberFormat.getCurrencyInstance().format(cart.calculateTotal()));
		assertEquals(1, cart.getOrderItemList().size());
	}
	
	@Test
	@Order(5)
	void test_remove_product() { 
		
		List<ProductDto> products = productController.getProducts("Table");
		assertEquals(1, products.size());
		
		List<ClientDto> clients = clientController.getClients("Jose");
		assertEquals(1, clients.size());
		
		ControllerResponse cartResponse = cartController.removeProduct(null, clients.get(0).getId(), products.get(0).getId());
		
		CartEntity cart = ((CartEntity)cartResponse.getData().get("cart"));
		assertEquals(BigDecimal.ZERO, cart.calculateTotal());
		assertEquals(0, cart.getOrderItemList().size());
	 }
	
	@Test
	@Order(6)
	void test_add_3_products_and_calculate_total() { 
		
		ProductDto iPad = productController.save(new ProductDto("iPad", "to study at any place", ProductCategoryEnum.ELECTRONIC, BigDecimal.valueOf(250)));
		ProductDto coffee = productController.save(new ProductDto("Coffe Machine", "machine to drin coffee", ProductCategoryEnum.ELECTRONIC, BigDecimal.valueOf(100)));
		ProductDto courtine = productController.save(new ProductDto("Courtine", "Courine for the living room", ProductCategoryEnum.HOME, BigDecimal.valueOf(50)));
		
		List<ClientDto> clients = clientController.getClients("Jose");
		
		cartController.addProduct( iPad.getId(), clients.get(0).getId(), 1);
		cartController.addProduct( coffee.getId(), clients.get(0).getId(), 2);
		ControllerResponse cartResponse = cartController.addProduct( courtine.getId(), clients.get(0).getId(), 3);
		
		CartEntity cart = ((CartEntity) cartResponse.getData().get("cart"));
		assertEquals(NumberFormat.getCurrencyInstance().format(BigDecimal.valueOf(600)), NumberFormat.getCurrencyInstance().format(cart.calculateTotal()));
	 }
	
	@Test
	@Order(6)
	void test_add_product_and_start_order() { 
		
		ProductDto mouse = productController.save(new ProductDto("mouse", "accessory for computer", ProductCategoryEnum.ELECTRONIC, BigDecimal.valueOf(20)));
		
		List<ClientDto> clients = clientController.getClients("Jose");
		
		ControllerResponse cartResponse = cartController.addProduct( mouse.getId(), clients.get(0).getId(), 3);
		
		CartEntity cart = ((CartEntity) cartResponse.getData().get("cart"));
		
		cartResponse = cartController.startOrder(cart.getId());
		OrderEntity newOrder = (OrderEntity) cartResponse.getData().get("newOrder");
		
		assertEquals(NumberFormat.getCurrencyInstance().format(BigDecimal.valueOf(660)), NumberFormat.getCurrencyInstance().format(newOrder.getTotal()));
	 }
	
}
