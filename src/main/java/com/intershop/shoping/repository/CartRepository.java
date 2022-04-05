package com.intershop.shoping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.intershop.shoping.entity.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long>{
	
	
	@Query(nativeQuery = true,
            value = "SELECT TOP 1 * FROM cart e WHERE client_id = ?1 AND status = 'DRAFT'  ORDER BY e.id DESC")
	Optional<CartEntity> findTopByOrderByClientIdDesc(Long clientId);
}
