package com.intershop.shoping.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.intershop.shoping.entity.dto.ClientDto;

@Entity
@Table(name="client")
public class ClientEntity  implements Serializable{

	private static final long serialVersionUID = -7061399761707894760L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String email;
	
	@Column(name = "creation_date")
	private LocalDateTime creationDate = LocalDateTime.now();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	private List<OrderEntity> orderList = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	private List<CartEntity> cartList = new ArrayList<>();

	public ClientEntity() {}

	public ClientEntity(ClientDto dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.email = dto.getEmail();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public List<OrderEntity> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderEntity> orderList) {
		this.orderList = orderList;
	}
	
}
