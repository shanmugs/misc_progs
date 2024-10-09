package com.example.demo.service;

import java.util.List;

import com.example.demo.model.PurchaseOrder;

public interface PurchaseOrderService {

	public PurchaseOrder getPurchaseOrderById(Integer id);

	public void deletePurchaseOrder(Integer id);

	public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder);

	public List<PurchaseOrder> getPurchaseOrders();
	


}
