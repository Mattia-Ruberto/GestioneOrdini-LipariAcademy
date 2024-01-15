package com.lipari.gestioneordini.Model.Address;

public class Address{
	private String Street;
	private Integer Number;
	private String City;
	private String CAP;
	private Integer userID;

	public Address(){
		super();
	}
	public Address(String Street, Integer Number, String City, String CAP) {
		super();
		this.Street = Street;
		this.Number = Number;
		this.City = City;
		this.CAP = CAP;
	}
	
	public String getStreet() {
		return this.Street;
	}
	
	public Integer getNumber() {
		return this.Number;
	}
	
	public String getCity() {
		return this.City;
	}
	
	public String getCAP() {
		return this.CAP;
	}
	public void setStreet(String street){
		this.Street = street;
	}

	public void setNumber(Integer number) {
		this.Number = number;
	}

	public void setCity(String city) {
		this.City = city;
	}

	public void setCAP(String CAP) {
		this.CAP = CAP;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String PrintFullAddress() {
		return this.Street + " " + this.Number.toString() + ", " + this.City + ", " + this.CAP;
	}
}
