package com.lipari.gestioneordini.Model.Address;

public class Address{
	private final String Street;
	private final Integer Number;
	private final String City;
	private final String CAP;
	
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
	
	public String PrintFullAddress() {
		return this.City + " " + this.Number.toString() + ", " + this.City + ", " + this.CAP;
	}
}
