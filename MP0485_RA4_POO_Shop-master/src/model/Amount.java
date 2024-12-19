/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author emche
 */
public class Amount {
 	private double value;
 	private final String currency = "?";
 
	public Amount(double value) {
     	this.value = value;
 	}
 
	public double getValue() {
     	return value;
 	}
 
	public void setValue(double value) {
     	this.value = value;
 	}
 
	public String getCurrency() {
     	return currency;
 	}

 	public void add(Amount other) {
         	this.value += other.value;
 	}
 
	@Override
 	public String toString() {
     	return String.format("%.2f %s", value, currency);
 	}
 
}


