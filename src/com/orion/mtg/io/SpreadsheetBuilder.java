package com.orion.mtg.io;

import com.orion.mtg.logic.CollectionManager;

import java.io.PrintWriter;
import java.util.Map;
import com.orion.mtg.model.CardVersion;

public class SpreadsheetBuilder {

    public static void buildSpreadsheet(CollectionManager manager, String outputPath) {
        // TODO: Version 1 spreadsheet output using Apache POI
    }
    
    public static void exportToCSV(Map<String, CardVersion> cards, String filename) {
    	try (PrintWriter writer = new PrintWriter(filename)){
    		writer.println("Name,Set,Collector Number,Foil,Quantity,Price,Total");
    		double grandTotal = 0.0;
    		
    		for(CardVersion card : cards.values()) {
    			double priceEach;
    			if(card.isFoil()) {
    				priceEach = card.getPrices().getUsdFoil();
    			} else {
    				priceEach = card.getPrices().getUsd();
    			}
    			double total = priceEach * card.getQuantity();
    			
    			grandTotal += total;
    			
    			String row = card.getName() + "," +
    		             card.getSetCode() + "," +
    		             card.getCollectorNumber() + "," +
    		             card.isFoil() + "," +
    		             card.getQuantity() + "," +
    		             String.format("%.2f", priceEach) + "," +
    		             String.format("%.2f", total);
    			
    			writer.println(row);
    			
    		}
    		writer.println(",,,,,Grand Total," + String.format("%.2f", grandTotal));
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}