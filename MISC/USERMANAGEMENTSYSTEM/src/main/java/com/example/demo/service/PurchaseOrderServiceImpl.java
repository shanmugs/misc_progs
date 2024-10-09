package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.PurchaseOrder;
import com.example.demo.model.User;
import com.example.demo.repository.PurchaseOrderRepository;
import com.example.demo.repository.UserRepository;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

	
	@Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
	
	
	
	  public void deletePurchaseOrder(Integer purchaseOrderId) {
		  purchaseOrderRepository.deleteById(purchaseOrderId);
	    }
	  
	  public PurchaseOrder savePurchaseOrder(PurchaseOrder purhaseOrder) {
	        return purchaseOrderRepository.save(purhaseOrder);
	    }
	  
	  public PurchaseOrder getPurchaseOrderById(Integer id)
		{   
			return purchaseOrderRepository.findById(id).get();
		}
	  
	  public List<PurchaseOrder> getPurchaseOrders()
		{   
			return (List<PurchaseOrder>) purchaseOrderRepository.findAll();
		}

	
	  
	  
	  
	  
}
