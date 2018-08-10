package com.booking.app.modal;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table (name = "PropertyTimeSheet")
public class PropertyTimeSheet {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY	)
	@Column(name= "propTimeSheetId")
	private Long PropTimeSheetId;
	
	private Date AvailableDates;
	
	/*@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "prop_Id",referencedColumnName= "prop_Id")*/
	
	@OneToOne
	@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	
	private Property property;
	
	
	



	public Date getAvailableDates() {
		return AvailableDates;
	}

	public void setAvailableDates(Date availableDates) {
		AvailableDates = availableDates;
	}

	public Long getPropTimeSheetId() {
		return PropTimeSheetId;
	}

	public PropertyTimeSheet( Date availableDates, Property property) {
		
		AvailableDates = availableDates;
		this.property = property;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public PropertyTimeSheet() {
		
	}
	
	
	
}
