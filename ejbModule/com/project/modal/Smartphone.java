package com.project.modal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Smartphone implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1326448912917383902L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String imei;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	  @OneToMany(fetch = FetchType.EAGER,mappedBy = "smartphone")
	  @JsonManagedReference
	  List<Position> positions = new ArrayList<Position>();
	 
	

	public Smartphone(String imei) {
		super();
		this.imei = imei;
	}
	
	public Smartphone() {
		super();
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	
	  public List<Position> getPositions() { return positions; }
	  
	  public void setPositions(List<Position> positions) { this.positions =
	  positions; }
	 

	@Override
	public String toString() {
		return imei ;
	}
	
	
}
