package com.orion.mtg.model;

public class CardVersion {
	
	private String name;
    private String setCode;
    private String collectorNumber;
    private boolean foil;
    private int quantity;
    private PriceInfo prices;


    public CardVersion(String name, String setCode, String collectorNumber, boolean foil, int quantity, PriceInfo prices) {
        this.name = name;
    	this.setCode = setCode;
        this.collectorNumber = collectorNumber;
        this.foil = foil;
        this.quantity = quantity;
        this.prices = prices;
    }

    public String getName() {
    	return name;
    }
    
    public String getSetCode() {
    	return setCode;
    }
    
    public String getCollectorNumber() {
    	return collectorNumber;
    }
    
    public PriceInfo getPrices() {
    	return prices;
    }
    
    public boolean isFoil() {
    	return foil;
    }
    
    public int getQuantity() {
    	return quantity;
    }
    
    
}