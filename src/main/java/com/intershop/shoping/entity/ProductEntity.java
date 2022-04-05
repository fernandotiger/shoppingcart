package com.intershop.shoping.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.intershop.shoping.entity.dto.ProductDto;
import com.intershop.shoping.entity.enums.ProductCategoryEnum;

@Entity
@Table(name="product")
public class ProductEntity  implements Serializable{

	private static final long serialVersionUID = 2069833549076923830L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	private String description;
	
	@NotNull
	private BigDecimal price;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private ProductCategoryEnum category;
	
	@Column(name = "creation_date")
	private LocalDateTime creationDate = LocalDateTime.now();

	public ProductEntity() {}

	public ProductEntity(ProductDto dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.description = dto.getDescription();
		this.price = dto.getPrice();
		this.category = dto.getCategory();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public ProductCategoryEnum getCategory() {
		return category;
	}

	public void setCategory(ProductCategoryEnum category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductEntity other = (ProductEntity) obj;
		if (category != other.category)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
}
