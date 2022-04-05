package com.intershop.shoping.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.intershop.shoping.entity.dto.ClientDto;
import com.intershop.shoping.exception.NotFoundException;
import com.intershop.shoping.service.ClientService;

@Controller
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	public List<ClientDto> getClients(String textFilter){
		
		return clientService.findClients(textFilter);
	}
	
	public ClientDto save(@Valid ClientDto clientDto){
		
		return clientService.save(clientDto);
	}
	
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
