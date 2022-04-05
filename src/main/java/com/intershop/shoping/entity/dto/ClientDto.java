package com.intershop.shoping.entity.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.intershop.shoping.entity.ClientEntity;

public class ClientDto {

	private Long id;
	
	private String name;
	
	private String email;
	
	private LocalDateTime creationDate = LocalDateTime.now();
	
	public ClientDto(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	public ClientDto(ClientEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.creationDate = entity.getCreationDate();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public static List<ClientDto> convert(List<ClientEntity> entityList){
		return entityList.stream().map(ClientDto::new).collect(Collectors.toList());
	}

	public ClientEntity updateEntity(Long id) {
		ClientEntity entity = new ClientEntity(this);
		entity.setId(id);
		return entity;
	}

	@Override
	public String toString() {
		return "ClientDto [id=" + id + ", name=" + name + ", email=" + email + ", creationDate=" + creationDate + "]";
	}
	
	
}
