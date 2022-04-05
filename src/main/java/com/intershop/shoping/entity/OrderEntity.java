package com.intershop.shoping.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
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
@Table(name="client_order")
public class OrderEntity  implements Serializable{

	private static final long serialVersionUID = 3686782276203895106L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private BigDecimal total;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private OrderStatusEnum status = OrderStatusEnum.DRAFT;
	
	@Column(name = "creation_date")
	private LocalDateTime creationDate = LocalDateTime.now();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", nullable = false)
	private ClientEntity client;
	
	@OneToMany
	private List<OrderItemEntity> orderItemList = new ArrayList<>();
	
	@Deprecated
	public OrderEntity() {
	}
	
	public OrderEntity(ClientEntity client, List<OrderItemEntity> orderItemList, BigDecimal total ) {
		this.total = total;
		this.orderItemList = orderItemList;
		this.client = client;
		this.orderItemList.forEach(item -> item.setOrder(this));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public OrderStatusEnum getStatus() {
		return status;
	}

	public void setStatus(OrderStatusEnum status) {
		this.status = status;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public List<OrderItemEntity> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItemEntity> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
	public void addItem(OrderItemEntity item) {
		this.orderItemList.add(item);
		calculateTotal();
	}

	private void calculateTotal() {
		total = BigDecimal.ZERO;
		orderItemList.forEach(item ->{
			total.add(BigDecimal.valueOf(item.getQuantity()).multiply(item.getId().getProduct().getPrice()));
		});
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
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
		OrderEntity other = (OrderEntity) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
