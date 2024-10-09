package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PurchaseOrder;
import com.example.demo.model.Role;

@Repository
public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Integer>{
	

}
