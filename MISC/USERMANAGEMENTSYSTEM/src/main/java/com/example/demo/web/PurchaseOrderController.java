package com.example.demo.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.PurchaseOrder;
import com.example.demo.model.User;
import com.example.demo.service.PurchaseOrderService;
import com.example.demo.service.PurchaseOrderServiceImpl;
import com.example.demo.service.UserService;

@Controller
public class PurchaseOrderController
{
	
	@Autowired
    private PurchaseOrderService purchaseOrderService;
	
	@Autowired
    private UserService userService;

	 @RequestMapping("purchaseOrder/edit/{id}")
	    public String edit(@PathVariable Integer id, Model model) {
	        model.addAttribute("purchaseOrder", purchaseOrderService.getPurchaseOrderById(id)); 
	        return "purchaseOrderform";
	    }
	    
	    @RequestMapping("purchaseOrder/delete/{id}")
	    public String delete(@PathVariable Integer id) {
	    	purchaseOrderService.deletePurchaseOrder(id);
	        return "redirect:/staff";
	    }
	    
	    @RequestMapping(value = "purchaseOrder", method = RequestMethod.POST)
	    public String savePurchaseOrder(PurchaseOrder purchaseOrder) {
	    	purchaseOrderService.savePurchaseOrder(purchaseOrder);
	        return "redirect:/staff";
	    }
	    
	    @RequestMapping(value="purchaseOrder",  method = RequestMethod.GET)
	    public String showPurchaseOrders(Model model){
		  model.addAttribute("purchaseOrderList",purchaseOrderService.getPurchaseOrders());
		  return "staff";
	    }

	    @RequestMapping(value="purchaseOrder/new",method = RequestMethod.GET)
	    public String newPurchaseOrder(Model model) {
	    	List<User> userList = userService.getAllUsers();
	    	List<User> customerList = new ArrayList<>();
	    	for (User u : userList)
	    	{
	    		u.getRoles().stream().forEach(role -> {
	    			if (role.getName().equalsIgnoreCase("CUSTOMER"))
	    				customerList.add(u);
	   			});
	    	}
	    	if (customerList != null && customerList.size() > 0)
	    	{
	    		model.addAttribute("purchaseOrder", new PurchaseOrder());
		        return "purchaseOrderform";
	    	}
	    	else return "staff";
	    }
	    
	    @RequestMapping(value="/purchaseOrderNew/new",method = RequestMethod.GET)
	    public String newPurchaseOrderNew(Model model) {
	    	
	    		model.addAttribute("purchaseOrder", new PurchaseOrder());
		       return "purchaseOrderFormCust";
	    	
	    }
	    
	    
	    @RequestMapping(value = "purchaseOrderCust", method = RequestMethod.POST)
	    public String savePurchaseOrderCust(PurchaseOrder purchaseOrder) {
	    	purchaseOrderService.savePurchaseOrder(purchaseOrder);
	        return "redirect:/customer";
	    }
	    
	    @RequestMapping("purchaseOrderCust/delete/{id}")
	    public String deleteCust(@PathVariable Integer id) {
	    	purchaseOrderService.deletePurchaseOrder(id);
	        return "redirect:/customer";
	    }
	    

	    @RequestMapping("purchaseOrderCust/edit/{id}")
	    public String editCust(@PathVariable Integer id, Model model) {
	        model.addAttribute("purchaseOrder", purchaseOrderService.getPurchaseOrderById(id)); 
	        return "purchaseOrderFormCust";
	    }
}
