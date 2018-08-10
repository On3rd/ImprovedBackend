package com.booking.app.modal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;



@Entity
@Table(name = "role_names_tabel")

public class RoleNames {

	@Id
	private Long roleName_Id;
	
	@OneToOne
	@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	private User_Roles user_role;
	
		
	private String role_name;
	
	
	public String getRole() {
		return role_name;
	}
	
	public void setRole(String role_name) {
		this.role_name = role_name;
	}
	
	public Long getRoleName_Id() {
		return roleName_Id;
	}
	
	public RoleNames(String role_name) {
		
		this.role_name = role_name;
	}
	
	public RoleNames() {
		
	}
	
	
}
