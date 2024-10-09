package com.example.demo.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.annotation.security.RolesAllowed;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.PurchaseOrder;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.PurchaseOrderService;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.UserRegistrationDto;





@Controller
public class UserRegistrationController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @ModelAttribute("adminRegistration")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
    
    @ModelAttribute("customerRegistration")
    public UserRegistrationDto customerRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("adminRegistration", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerAdminAccount(@ModelAttribute("adminRegistration") @Valid UserRegistrationDto userDto,
                                      BindingResult result){

        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "registration";
        }
        	
        User u = new User();
        u.setRoles(Arrays.asList(new Role("ADMIN")));

        userService.save(userDto, u);
        

        
        return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {

        return "login";
    }
    
    
    
    
    @RequestMapping("/staff/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("userUpdate", userService.getUserById(id)); 
        return "adminform";
    }
    
    @RequestMapping(value="staff/new/",  method = RequestMethod.GET)
    public String newStaff(Model model) {
        model.addAttribute("userUpdate", new User());
        return "adminform";
    }
    
    @RequestMapping("staff/delete/{id}")
    public String delete(@PathVariable Integer id) {
    	userService.deleteUser(id);
        return "redirect:/admin";
    }
    

    @RequestMapping(value = "staffInsert", method = RequestMethod.POST)
    public String saveStaff(User userUpdate) {
        userService.saveStaff(userUpdate);
        return "redirect:/admin/";
    }
    
    
    @RequestMapping(value="admin",  method = RequestMethod.GET)
    public String showStaff(Model model){
    	
    	List<User> userList = userService.getAllUsers();
    	List<User> staffList = new ArrayList<>();
    	for (User u : userList)
    	{
    			u.getRoles().stream().forEach(role -> {
    				if (role.getName().equalsIgnoreCase("STAFF"))
    	    			staffList.add(u);
    			});
    	}
    	
    	model.addAttribute("staffList",staffList);
    	return "admin";
    }     
    
    @RequestMapping(value = "/confirmInsert", method = RequestMethod.POST)
    public String confirmPassword(@RequestParam("email") String email,@RequestParam("password") String password, HttpSession session) {
        User u = userService.findByEmail(email);
    	userService.confirmPassword(u, password);
        return "login";
    }
    
    @RequestMapping(value="/confirm",  method = RequestMethod.GET)
    public String confirmAccount(Model model, @RequestParam("token") String token) {    	
    	User user = userService.confirm(token);
    	model.addAttribute("confirmUser", user);
        return "confirm";
    }
    
    
    
    @RequestMapping("customer/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
    	userService.deleteUser(id);
        return "redirect:/staff";
    }

    @RequestMapping("/customer/edit/{id}")
    public String editCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customerUpdate", userService.getUserById(id)); 
        return "staffform";
    }
    
    @RequestMapping(value="customer/new/",  method = RequestMethod.GET)
    public String newCustomer(Model model) {
        model.addAttribute("customerUpdate", new User());
        return "staffform";
    }
    
    @RequestMapping(value = "customerInsert", method = RequestMethod.POST)
    public String saveCustomer(User customerUpdate) {
        User u = userService.saveCustomer(customerUpdate);
        return "redirect:/staff/";
    }
    
    @RequestMapping(value = "/customerRegistration", method = RequestMethod.POST)
    public String registerCustomerAccount(@ModelAttribute("customerRegistration") @Valid UserRegistrationDto userDto,
                                      BindingResult result){

        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "customerRegistration";
        }
        User u = new User();
        u.setRoles(Arrays.asList(new Role("CUSTOMER")));
        userService.save(userDto, u);
        

        
        return "staff";
    }
    
    @RequestMapping(value="staff",  method = RequestMethod.GET)
    public String showCustomers(Model model){
    	
    	List<User> userList = userService.getAllUsers();
    	List<User> customerList = new ArrayList<>();
    	for (User u : userList)
    	{
    		u.getRoles().stream().forEach(role -> {
    			if (role.getName().equalsIgnoreCase("CUSTOMER"))
    				customerList.add(u);
   			});
    	}
    	model.addAttribute("customerList",customerList);
    	
		model.addAttribute("purchaseOrderList",purchaseOrderService.getPurchaseOrders());


    	return "staff";
    }    
    
    @RequestMapping(value="customer",  method = RequestMethod.GET)
    public String showCustDetails(Model model){
    	
		model.addAttribute("purchaseOrderList",purchaseOrderService.getPurchaseOrders());

		
    	return "customer";
    } 
    
    @RequestMapping(value="customer/changeUsernamePassword/",  method = RequestMethod.GET)
    public String showDetails(Model model){
    	
    	return "changeUsernamePassword";
    } 
    
    @RequestMapping(value = "/changeDetail", method = RequestMethod.POST)
    public String changePassword(@RequestParam("currentUsername") String currentEmail, 
    		@RequestParam("username") String email,
    		@RequestParam("password") String password) {
    	
    	User u = userService.saveDetails(currentEmail, email, password);
        if (u != null)
        return "customer";
        else return "changeUsernamePassword";
    }
    
    @GetMapping("/")
    public String root() {
        return "login";
    }

    
}
