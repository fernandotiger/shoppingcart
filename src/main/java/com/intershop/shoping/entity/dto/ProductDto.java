package com.intershop.shoping.entity.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.intershop.shoping.entity.ProductEntity;
import com.intershop.shoping.entity.enums.ProductCategoryEnum;

public class ProductDto {

	private Long id;
	
	private String name;
	
	private String description;
	
	private ProductCategoryEnum category;
	
	private BigDecimal price;
	
	private LocalDateTime creationDate = LocalDateTime.now();
	
	public ProductDto(String name, String description, ProductCategoryEnum category, BigDecimal price) {	
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}
	
	public ProductDto(ProductEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.creationDate = entity.getCreationDate();
		this.category = entity.getCategory();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	
	public static List<ProductDto> convert(List<ProductEntity> entityList){
		return entityList.stream().map(ProductDto::new).collect(Collectors.toList());
	}
	
	public ProductEntity updateEntity(Long id) {
		ProductEntity entity = new ProductEntity(this);
		entity.setId(id);
		return entity;
	}

	public ProductCategoryEnum getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + ", description=" + description + ", category=" + category
				+ ", price=" + price + ", creationDate=" + creationDate + "]";
	}
	
	
	
}
