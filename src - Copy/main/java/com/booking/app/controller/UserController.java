package com.booking.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.modal.User;
import com.booking.app.repository.UserRepository;
import com.booking.app.security.JwtGenerator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="http://localhost:4200",allowedHeaders = "*")

public class UserController {

	private String secret = "youtube";
	
	
	@Autowired
	 
	private UserRepository userRepository;
	@Autowired
    private JwtGenerator jwtGenerator;

	
	@GetMapping("/users")
	public List<User> getUsers()
	{
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{user_Id}")
	public Optional<User> getUser(@PathVariable Long user_Id)
	{
		return userRepository.findById(user_Id);
	}
	
	@DeleteMapping("/users/{user_Id}")
	public boolean deleteUser(@PathVariable Long user_Id)
	{
		userRepository.deleteById(user_Id);
		return true;
	}

	@PostMapping("/users")
	public User createUser(@RequestBody User user)
	{
		for(int index = 0; index<userRepository.findAll().size();index++)
		{
			if(user.getRole().equalsIgnoreCase("Customer") || user.getRole() == null)
			{
				user.setRole("Customer");
			}
			if(user.getRole().equalsIgnoreCase("Admin"))
			{
				user.setRole("Admin");
			}
			if(user.getEmail().equalsIgnoreCase(userRepository.findAll().get(index).getEmail()))
			{
				return null;
			}
		}
		return userRepository.save(user);
	}

	@PutMapping("/updateProfile")
	public User updateUser(@RequestBody User user)
	{
		return userRepository.save(user);
	}
	
	
	@GetMapping("/userlogin/{email}/{password}")
	
	public String login( @PathVariable String email , @PathVariable String password)
	{

		User user = new User();
		
		for (int x = 0; x < userRepository.findAll().size(); x++) {
			if (userRepository.findAll().get(x).getEmail().equals(email)
					&& userRepository.findAll().get(x).getPassword().equals(password)) {
				
				user = userRepository.findAll().get(x);
				return jwtGenerator.generate(user);
			}
			
		}

		return null;
	
	}
	
	@GetMapping("/username/{token}")
	public String getUsername(@PathVariable String token)
	{
		String username = null;
		
		 try {
	            Claims body = Jwts.parser()
	                    .setSigningKey(secret)
	                    .parseClaimsJws(token)
	                    .getBody();
	            username = (String) body.get("username");
	            return username;
	        }
	        catch (Exception e) {
	            System.out.println(e);
	        }
		 
		 return username;
	}
	
	@GetMapping("/role/{token}")
	public String getRole(@PathVariable String token)
	{
		String role = null;
		
		 try {
	            Claims body = Jwts.parser()
	                    .setSigningKey(secret)
	                    .parseClaimsJws(token)
	                    .getBody();
	            role = (String) body.get("role");
	            return role;
	        }
	        catch (Exception e) {
	        	
	            System.out.println(e);
	            
	        }
		 
		 return role;
	}
	
	@GetMapping("/getUserID/{token}")
	public String getUserID(@PathVariable String token)
	{
		String userId = null;
		
		try {
			Claims body = Jwts.parser()
		
	                    .setSigningKey(secret)
	                    .parseClaimsJws(token)
	                    .getBody();
	     userId =  (String) body.get("userId");
	          
	     return userId;
		 }
        catch (Exception e) {
        	
            System.out.println(e);
            
        }
	       
		return userId;
	}
	
	@GetMapping("/searchUser/{user_id}")
	public User searchUser(@PathVariable long user_id)
	{
		for(int index = 0; index < userRepository.findAll().size();index++)
		{
			if(userRepository.findAll().get(index).getUser_Id().equals(user_id))
			{
				return userRepository.findAll().get(index);
			}
		}
		return null;
	}
	
	 

	
	
}
