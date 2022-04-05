package com.intershop.shoping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.intershop.shoping.entity.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long>{
	
	@Query("SELECT e FROM ClientEntity e where (?1 is null) OR e.name LIKE ?1")
	List<ClientEntity> findByClientName(String textFilter);
}
