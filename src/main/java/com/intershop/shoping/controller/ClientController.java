package com.intershop.shoping.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intershop.shoping.entity.dto.ClientDto;
import com.intershop.shoping.exception.NotFoundException;
import com.intershop.shoping.service.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public List<ClientDto> getClients(String textFilter){
		
		return clientService.findClients(textFilter);
	}
	
	@PostMapping
	public ClientDto save(@Valid ClientDto clientDto){
		
		return clientService.save(clientDto);
	}
	
	@GetMapping("/{id}")
	public ControllerResponse getDetail(Long id){
		ControllerResponse response = new ControllerResponse();
		try {
			response.getData().put("clientDetail", clientService.clientDetail(id));
		} catch (NotFoundException e) {
			response.setSuccess(Boolean.FALSE);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@PutMapping
	public ControllerResponse updateClient(Long id, ClientDto dto){
		ControllerResponse response = new ControllerResponse();
		try {
			response.getData().put("clientDetail", clientService.updateClient(id, dto));
		} catch (NotFoundException e) {
			response.setSuccess(Boolean.FALSE);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@DeleteMapping
	public ControllerResponse delete(Long id){
		ControllerResponse response = new ControllerResponse();
		try {
			clientService.delete(id);
		} catch (NotFoundException e) {
			response.setSuccess(Boolean.FALSE);
			response.setMessage(e.getMessage());
		}
		return response;
	} 
}
