package com.intershop.shoping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.intershop.shoping.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	
	@Query("SELECT e FROM ProductEntity e where (?1 is null) OR e.name LIKE ?1")
	List<ProductEntity> findByProductName(String textFilter);
}
