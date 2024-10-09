package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.ConfirmationToken;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.ConfirmationTokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    
    @Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void save(UserRegistrationDto registration, User user){
        user.setEmail(registration.getEmail());
        user.setName(registration.getName());
        user.setPhone(registration.getPhone());
        user.setAddress(registration.getAddress());

        userRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);
        sendMail(user, confirmationToken);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
    
    
    
    public void deleteUser(Integer user) {
        userRepository.deleteById(user);
    }
    
    public List<User> getAllUsers()
	{   
		return (List<User>) userRepository.findAll();
	}
    
    @Override
    public User getUserById(Integer id)
	{   
		return userRepository.findById(id).get();
	}
    
   

    
    public void saveStaff(User user) {
    	User u = new User();
    	if (userRepository.findByEmail(user.getEmail()) == null)
    	{
        user.setRoles(Arrays.asList(new Role("STAFF")));
        userRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);
        sendMail(user, confirmationToken);
    	}
    	else 
    	{
    		user.setRoles(Arrays.asList(new Role("STAFF")));
    		u = userRepository.save(user);
    	}

    }
    
    public User saveCustomer(User user) {
    	User u = new User();
    	if (userRepository.findByEmail(user.getEmail()) == null)
    	{
    		user.setRoles(Arrays.asList(new Role("CUSTOMER")));
            userRepository.save(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);
            sendMail(user, confirmationToken);
    	}
    	else 
    		{
    		user.setRoles(Arrays.asList(new Role("CUSTOMER")));
    		userRepository.save(user);
    		}
    	return u;
    }
    
    
    
  
    
    
    
    @Override
    public void confirmPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
    
    @Override
    public User confirm(String confirmationToken) {
    	 ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
    	 User user = new User();
         if(token != null)
         {
             user = userRepository.findByEmail(token.getUser().getEmail());
         }
         return user;
    }
    
    
    public void sendMail(User u, ConfirmationToken token)
    {
    	String to = u.getEmail();

        String from = "aditya.apparaju.2806@gmail.com";

        // Sender's email ID needs to be mentioned

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session s = Session.getInstance(properties, new javax.mail.Authenticator() {

            public PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(u.getEmail(), "*********");

            }

        });

        // Used to debug SMTP issues
        s.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(s);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            
            message.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/confirm?token="+token.getConfirmationToken());

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }
        catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    
    @Override
    public User saveDetails(String currentUsername, String username, String password) {
    	User u = new User();
    	    	
    	if (userRepository.findByEmail(username) == null || (username.equalsIgnoreCase(currentUsername)))
    	{
    		u = userRepository.findByEmail(currentUsername);
    		u.setEmail(username);
    		userRepository.save(u);
            ConfirmationToken confirmationToken = new ConfirmationToken(u);
            confirmationTokenRepository.save(confirmationToken);
            sendMail(u, confirmationToken);
            return u;
    	}
    	else return null;
    	
    	
    }
   
}
