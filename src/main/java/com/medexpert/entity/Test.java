package com.medexpert.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Test {
	
	private int id;
	private String name; 
	private double price;
	private String unit;
	private double minValue;
	private double maxValue;

	/**
	 * Called by test factory.
	 * 
	 * @param name
	 * @param price
	 * @param unit
	 * @param minValue
	 * @param maxValue
	 */
	public Test(String name, double price, String unit, double minValue, double maxValue) {
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	@Override
	public String toString() {
		return "Test [name=" + name + ", price=" + price + ", unit=" + unit + ", minValue=" + minValue + ", maxValue="
				+ maxValue + "]";
	}
}
