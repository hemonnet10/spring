package com.agriyo.services.agriyodb.agriyodbservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name="t_farmer_crop")
public class FarmerCrop implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Crop crop;
	
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FARMER_ID", nullable = false)
	private User farmer;
	*/
	

	@Column(name = "FARMER_ID")
	private Integer farmerId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Crop getCrop() {
		return crop;
	}
	public void setCrop(Crop crop) {
		this.crop = crop;
	}
	public Integer getFarmerId() {
		return farmerId;
	}
	public void setFarmerId(Integer farmerId) {
		this.farmerId = farmerId;
	}
	
	
	
}