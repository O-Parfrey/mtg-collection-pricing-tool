package com.orion.mtg;

import java.util.Map;

import com.orion.mtg.api.ScryfallAPI;
import com.orion.mtg.io.TCGPlayerCSVImporter;
import com.orion.mtg.model.CardVersion;
import com.orion.mtg.ui.MenuSystem;

public class MTGApp {

	public static void main(String[] args) {
		Map<String, CardVersion>map = TCGPlayerCSVImporter.importCSV("D:\\Java Projects\\MTG Collection App\\CSV Tester\\test.csv");
		//delete all this later, testing purposes
		for(String key : map.keySet()){
			CardVersion v = map.get(key);
			System.out.println(key + " -> qty: " + v.quantity);
		}
		
		double price = ScryfallAPI.fetchPrice("Path to Exile", "29");
		System.out.println("Price: $" + price);

	} 

}
