package com.intershop.shoping.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.intershop.shoping.entity.enums.OrderStatusEnum;

@Entity
@Table(name="cart")
public class CartEntity implements Serializable{

	private static final long serialVersionUID = 794728223834561049L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal subtotal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", nullable = false)
	private ClientEntity client;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private OrderStatusEnum status = OrderStatusEnum.DRAFT;
	
	@OneToMany(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
	private List<OrderItemEntity> orderItemList = new ArrayList<>();

	@Deprecated
	public CartEntity() {
	}

	public CartEntity(ClientEntity client, BigDecimal subtotal) {
		this.client = client;
		this.subtotal = subtotal;
	}

	public CartEntity(ClientEntity client, BigDecimal subtotal, List<OrderItemEntity> orderItemList) {
		this.client = client;
		this.subtotal = subtotal;
		this.orderItemList = orderItemList;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}

	public List<OrderItemEntity> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItemEntity> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
	public OrderStatusEnum getStatus() {
		return status;
	}

	public void setStatus(OrderStatusEnum status) {
		this.status = status;
	}

	public BigDecimal calculateTotal(){
		BigDecimal total = BigDecimal.ZERO;
		for(OrderItemEntity item : this.orderItemList) {
			total = total.add( item.getId().getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
		}
		this.subtotal = total;
		return total;
	}
	
	public void removeProduct(ProductEntity product){
		Iterator<OrderItemEntity> it = this.orderItemList.iterator();
		while (it.hasNext()) {
			OrderItemEntity orderItemEntity = it.next();
			if(orderItemEntity.getId().getProduct().equals(product)) {
				it.remove();
			}
		}
		calculateTotal();
	}
	
	public void addItem(OrderItemEntity item) {
		if(this.orderItemList.contains(item)) {
			this.orderItemList.forEach(i -> {
				if(i.equals(item)) {
					i.setQuantity(item.getQuantity());
					return;
				}
			});
		} else {
			this.orderItemList.add(item);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CartEntity other = (CartEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
