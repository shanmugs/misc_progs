package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.model.User;
import com.example.demo.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    public User findByEmail(String email);

    public void save(UserRegistrationDto registration, User u);
    
    public List<User> getAllUsers();

	public User getUserById(Integer id);

	public void deleteUser(Integer id);

	public void saveStaff(User user);
	
	public User saveCustomer(User user);
	
	public void confirmPassword(User u, String password);
	
	public User confirm(String token);
	
	public User saveDetails(String currentUsername, String username, String password);
}
