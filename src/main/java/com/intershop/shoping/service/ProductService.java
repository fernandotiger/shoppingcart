package com.intershop.shoping.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intershop.shoping.entity.ProductEntity;
import com.intershop.shoping.entity.dto.ProductDto;
import com.intershop.shoping.exception.NotFoundException;
import com.intershop.shoping.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<ProductDto> findProducts(String textFilter){
		if(Objects.nonNull(textFilter)) {
			return ProductDto.convert(productRepository.findByProductName("%"+textFilter+"%"));
		}
		return ProductDto.convert(productRepository.findAll());
	}
	
	@Transactional
	public ProductDto save(@Valid ProductDto topicDto){
		ProductEntity product = new ProductEntity(topicDto);
		productRepository.save(product);
		
		return new ProductDto(product);
	}
	
	public ProductDto productDetail(Long id) throws NotFoundException{
		Optional<ProductEntity> optional = productRepository.findById(id);
		if(optional.isPresent()) {
			return new ProductDto(optional.get());
		}		
		throw new NotFoundException("Product details not found");
	}
	
	@Transactional
	public ProductDto updateProduct(Long id, ProductDto dto) throws NotFoundException{
		Optional<ProductEntity> optional = productRepository.findById(id);
		if(optional.isPresent()) {
			ProductEntity entity = dto.updateEntity(id);
			productRepository.save(entity);
			return new ProductDto(entity);
		}
		
		throw new NotFoundException("Product not found. Update not possible");
	}
	
	public void delete(Long id) throws NotFoundException{
		Optional<ProductEntity> optional = productRepository.findById(id);
		if(optional.isPresent()) {
			productRepository.deleteById(id);
		}
		
		throw new NotFoundException("Product details not found. Delete was not possible");
	}
	
	public ProductEntity findById(Long id) throws NotFoundException{
		Optional<ProductEntity> optional = productRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		
		throw new NotFoundException("Product details not found. ");
	}
}
