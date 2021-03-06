package com.booking.app.modal;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "propertytable")

public class Property {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long prop_Id;
	
	public Long getProp_Id() 
	{
		return prop_Id;
	}
	private String prop_name;
	
	private String prop_type;
	
	private int numberRoom;
	
	private String city;
	
	private String contact_name;
	
	private String addressline2;
	
	private String country;
	
	private String province;
	
	private String streetAddress;
	
	private String website;
	
	private double price;
	
	private String postal_code;
	
	@OneToOne
	@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	private PropertyTimeSheet propertyTimeSheet;
	
	public PropertyTimeSheet getPropertyTimeSheet() {
		return propertyTimeSheet;
	}

	public void setPropertyTimeSheet(PropertyTimeSheet propertyTimeSheet) {
		this.propertyTimeSheet = propertyTimeSheet;
	}
	
	@ManyToOne
	@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	private User user;
	
	public Property(String prop_name, String prop_type, int numberRoom, String city, String contact_name,
			String addressline2, String country, String province, String streetAddress, String website, double price,
			String postal_code, PropertyTimeSheet propertyTimeSheet, User user) {
		super();
		this.prop_name = prop_name;
		this.prop_type = prop_type;
		this.numberRoom = numberRoom;
		this.city = city;
		this.contact_name = contact_name;
		this.addressline2 = addressline2;
		this.country = country;
		this.province = province;
		this.streetAddress = streetAddress;
		this.website = website;
		this.price = price;
		this.postal_code = postal_code;
		this.propertyTimeSheet = propertyTimeSheet;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProp_name() {
		return prop_name;
	}
	public void setProp_name(String prop_name) {
		this.prop_name = prop_name;
	}
	public String getProp_type() {
		return prop_type;
	}
	public void setProp_type(String prop_type) {
		this.prop_type = prop_type;
	}
	public int getNumberRoom() 
	{
		return numberRoom;
	}
	public void setNumberRoom(int numberRoom) {
		this.numberRoom = numberRoom;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getAddressline2() {
		return addressline2;
	}
	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public Property() 
	{
			}
	
	
	
	
	
	
}
