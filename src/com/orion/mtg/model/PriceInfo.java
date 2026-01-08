package com.orion.mtg.model;

public class PriceInfo {
	private double usd;
	private double usdFoil;
	
	public PriceInfo(double usd, double usdFoil) {
		this.usd = usd;
		this.usdFoil = usdFoil;

	}
	
	public double getUsd() {
		return usd;
	}
	
	public double getUsdFoil() {
		return usdFoil;
	}
	
	 public void setUsd(double usd) {
		 this.usd = usd;
	 }
	 
	 public void setUsdFoil(double usdFoil) {
		 this.usdFoil = usdFoil;
	 }
	 
	 public boolean hasFoilPrice() {
		 return usdFoil > 0.0;
	 }
	 
	 public String formatUsd() {
		 return usd > 0 ? String.format("$%.2f", usd) : "N/A";
	 }
	 
	 public String formatUsdFoil() {
		 return usdFoil > 0 ? String.format("$%.2f", usdFoil) : "N/A";
	 }
	 
	 @Override
	 public String toString() {
		 return "USD: " + formatUsd() + " | Foil: " + formatUsdFoil();
	 }
	
	
}
