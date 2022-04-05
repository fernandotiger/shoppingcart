package com.intershop.shoping.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intershop.shoping.entity.ClientEntity;
import com.intershop.shoping.entity.dto.ClientDto;
import com.intershop.shoping.exception.NotFoundException;
import com.intershop.shoping.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public List<ClientDto> findClients(String textFilter){
		if(Objects.nonNull(textFilter)) {
			return ClientDto.convert(clientRepository.findByClientName("%"+textFilter+"%"));
		}
		return ClientDto.convert(clientRepository.findAll());
	}
	
	@Transactional
	public ClientDto save( ClientDto dto){
		ClientEntity client = new ClientEntity(dto);
		clientRepository.save(client);
		
		return new ClientDto(client);
	}
	
	public ClientDto clientDetail(Long id) throws NotFoundException{
		Optional<ClientEntity> optional = clientRepository.findById(id);
		if(optional.isPresent()) {
			return new ClientDto(optional.get());
		}		
		throw new NotFoundException("Client details not found");
	}
	
	@Transactional
	public ClientDto updateClient(Long id, ClientDto dto) throws NotFoundException{
		Optional<ClientEntity> optional = clientRepository.findById(id);
		if(optional.isPresent()) {
			ClientEntity entity = dto.updateEntity(id);
			clientRepository.save(entity);
			return new ClientDto(entity);
		}
		
		throw new NotFoundException("Client not found. Update not possible");
	}
	
	public void delete(Long id) throws NotFoundException{
		Optional<ClientEntity> optional = clientRepository.findById(id);
		if(optional.isPresent()) {
			clientRepository.deleteById(id);
		}
		
		throw new NotFoundException("Client details not found. Delete was not possible");
	}
	

	public ClientEntity findById(Long id) throws NotFoundException{
		Optional<ClientEntity> optional = clientRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		
		throw new NotFoundException("Client details not found. ");
	}
}
