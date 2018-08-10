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

import com.booking.app.modal.UserPaymentMethod;
import com.booking.app.repository.UserPaymentMethodRepository;
import com.booking.app.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="http://localhost:4200",allowedHeaders = "*")

public class UserPaymentMethodController {

	private String secret = "youtube";
	
	
	@Autowired
	private UserPaymentMethodRepository userPaymentMethodRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/userPaymentMethod")
	public List<UserPaymentMethod> getUserPaymentMethod()
	{
		return userPaymentMethodRepository.findAll();
	}
	
	@GetMapping("/userPaymentMethod/{UserPaymentMethodId}")
	public Optional<UserPaymentMethod> getUserPaymentMethod(@PathVariable Long UserPaymentMethodId)
	{
		return userPaymentMethodRepository.findById(UserPaymentMethodId);
	}
	
	@DeleteMapping("/userPaymentMethod/{UserPaymentMethodId}")
	public boolean deleteUserPaymentMethod(@PathVariable Long UserPaymentMethodId)
	{
		userPaymentMethodRepository.deleteById(UserPaymentMethodId);
		return true;
	}
	
	@GetMapping("/confirmPaymentMethod/{token}")
	public boolean confirmPaymentMethod(@PathVariable String token)
	{
		int user_id =0;

		 try {
	            Claims body = Jwts.parser()
	                    .setSigningKey(secret)
	                    .parseClaimsJws(token)
	                    .getBody();
	            user_id = (int) body.get("userId");
	           
	        }
	        catch (Exception e) {
	            System.out.println(e);
	        }
		for(int index = 0 ; index< userRepository.findAll().size();index++)
		{
			if(userRepository.findAll().get(index).getUserPaymentMethod() != null &&userRepository.findAll().get(index).getUserPaymentMethod().getUser().getUser_Id() == user_id)
			{
				 return true;	
			}
			
		}
		return false;
	}

	@PostMapping("/createUserPayment/{token}")
	public UserPaymentMethod createUserPaymentMethod(@RequestBody UserPaymentMethod userPaymentMethod,@PathVariable String token)
	{
		
		int user_id = 0;
		
		 try {
	            Claims body = Jwts.parser()
	                    .setSigningKey(secret)
	                    .parseClaimsJws(token)
	                    .getBody();
	            user_id = (int) body.get("userId");
	           
	        }
	        catch (Exception e) {
	            System.out.println(e);
	        }
		 
		for(int index = 0; index <userRepository.findAll().size();index++ )
		{
			if(userRepository.findAll().get(index).getUser_Id() == user_id)
			{
				userPaymentMethod.setUser(userRepository.findAll().get(index));
			}
		}
		
		return userPaymentMethodRepository.save(userPaymentMethod);
	}

	@PutMapping("/userPaymentMethod")
	public UserPaymentMethod updateUserPaymentMethod(@RequestBody UserPaymentMethod userPaymentMethod)
	{
		return userPaymentMethodRepository.save(userPaymentMethod);
	}
	
	 
}
