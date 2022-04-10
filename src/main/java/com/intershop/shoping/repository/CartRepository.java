package com.intershop.shoping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.intershop.shoping.entity.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long>{
	
	
	@Query("SELECT c FROM CartEntity c where c.client.id = ?1 AND status = 'DRAFT'  ")
	Optional<CartEntity> findTopByOrderByClientIdDesc(Long clientId);
}
