package com.booking.app.controller;


import java.sql.Date;
import java.util.ArrayList;
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

import com.booking.app.modal.AccBooking;
import com.booking.app.repository.AccBookingRepository;
import com.booking.app.repository.PropertyRepository;
import com.booking.app.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="http://localhost:4200",allowedHeaders = "*")

public class AccBookingController {

	private String secret = "youtube";
	
	@Autowired 
	private AccBookingRepository accBookingRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private PropertyRepository propertyRepository;
	
	@Autowired 
	private UserPaymentMethodController userPaymentMethodController;
	
	
	
	@GetMapping("/accBooking")
	public List<AccBooking> getAccBooking()
	{
		return accBookingRepository.findAll();
	}
	
	@GetMapping("/accBooking/{accBooking_Id}")
	public Optional<AccBooking> getAccBooking(@PathVariable Long accBooking_Id)
	{
		return accBookingRepository.findById(accBooking_Id);
	}
	
	@DeleteMapping("/accBooking/{accBooking_Id}")
	public boolean deleteAccBooking(@PathVariable Long accBooking_Id)
	{
		accBookingRepository.deleteById(accBooking_Id);
		return true;
	}

	@PostMapping("/accBooking")
	public AccBooking createAccBooking(@RequestBody AccBooking accBooking)
	{
		return accBookingRepository.save(accBooking);
	}

	@PutMapping("/accBooking")
	public AccBooking updateAccBooking(@RequestBody AccBooking accBooking)
	{
		return accBookingRepository.save(accBooking);
	}
	
	@PostMapping("/bookAccomodation/{token}/{property_id}/{visitors}/{checkInDate}/{checkOutDate}")
	public AccBooking bookAccommodation(@PathVariable String token, @PathVariable int property_id,@PathVariable int visitors,@PathVariable Date checkInDate, @PathVariable Date checkOutDate)
	{
		AccBooking accBooking = new AccBooking();
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
		 
		boolean paymethod = userPaymentMethodController.confirmPaymentMethod(token);
		
		for(int index = 0; index < userRepository.findAll().size();index++ )
		{
			if(userRepository.findAll().get(index).getUser_Id() == user_id)
			{
				accBooking.setUser(userRepository.findAll().get(index));
			}
		}
		
		for(int index = 0; index < propertyRepository.findAll().size();index++)
		{
			if(propertyRepository.findAll().get(index).getProp_Id() == property_id)
			{
				accBooking.setProperty(propertyRepository.findAll().get(index));
			}
		}
		
		accBooking.setNoOfVisitors(visitors);
		accBooking.setCheckInDate(checkInDate);
		accBooking.setCheckOutDate(checkOutDate);
		int nights = checkOutDate.compareTo(checkInDate);
		
		accBooking.setNights(nights);
		
		if(paymethod == true)
		{
			return accBookingRepository.save(accBooking);
		}
		
		return null;
	}
	
	@GetMapping("/viewBookings/{token}")
	public List<AccBooking> viewBooking(@PathVariable String token)
	{
		int user_id = 0;
		
		List<AccBooking> accBooking = new ArrayList<>();
		
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
		
		for(int index = 0;index < accBookingRepository.findAll().size();index++)
		{
			if(accBookingRepository.findAll().get(index).getUser().getUser_Id() == user_id)
			{
				accBooking.add(accBookingRepository.findAll().get(index));
			}
		}
			
		return accBooking;
	}
	

	
	
}
