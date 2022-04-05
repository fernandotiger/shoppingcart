package com.intershop.shoping.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.intershop.shoping.entity.compositeid.OrderItemId;

@Entity
@Table(name="order_item")
public class OrderItemEntity  implements Serializable {

	private static final long serialVersionUID = 4523642902948576382L;

	@Id
	private OrderItemId id;
		
	@NotNull
	private Integer quantity;
	
	@NotNull
	private BigDecimal price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private OrderEntity order;
	
	@Deprecated
	public OrderItemEntity() {
	}
	
	public OrderItemEntity(ProductEntity product, OrderEntity order, Integer quantity) {
		this.id.setProduct(product);
		this.setOrder(order);
		this.quantity = quantity;
	}

	public OrderItemEntity(CartEntity cart, ProductEntity product, Integer quantity) {
		if(Objects.isNull(this.id)) {
			this.id = new OrderItemId();
		}
		this.id.setCart(cart);
		this.id.setProduct(product) ;
		this.quantity = quantity;
		this.price = product.getPrice();
	}

	public OrderItemId getId() {
		return id;
	}

	public void setId(OrderItemId id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal total) {
		this.price = total;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
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
		OrderItemEntity other = (OrderItemEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		return true;
	}
	
	
	
}
