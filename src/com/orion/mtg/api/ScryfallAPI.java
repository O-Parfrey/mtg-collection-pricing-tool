package com.orion.mtg.api;

import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.orion.mtg.model.CardVersion;
import com.orion.mtg.model.PriceInfo;

public class ScryfallAPI {
	
	private static final boolean DEBUG = false;
	
	private static void log(String msg) {
		if(DEBUG) {
			System.out.println("[ScryFallAPI]" + msg);
		}
	}
	private static Map<String, PriceInfo> cache = new HashMap<>();
	
    public static PriceInfo fetchPrice(String name, String collectorNumber) {
    	try {
    		String key = name.toLowerCase() + "|" + collectorNumber;
    		
    		if(cache.containsKey(key)) {
    			log("CACHE HIT -> " + key);
    			return cache.get(key);
    		}
    		
    		log("fetching: " + name + " (#" + collectorNumber + ")");
            String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
            String urlString = "https://api.scryfall.com/cards/search?q=" + encodedName;
            
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            reader.close();
            conn.disconnect();

            String json = jsonBuilder.toString();

            // Split on "object":"card" to loop through each printing
            String[] cards = json.split("\"object\":\"card\"");

            for (String obj : cards) {

                // Check for matching collector number
                String tag = "\"collector_number\":\"";
                int index = obj.indexOf(tag);
                if (index == -1) continue;

                int start = index + tag.length();
                int end = obj.indexOf("\"", start);
                String cn = obj.substring(start, end);

                if (!cn.equals(collectorNumber))
                    continue;

                log("Matched collector number: " + cn);
                
                // Extract usd price
                String usdTag = "\"usd\":\"";
                int usdIndex = obj.indexOf(usdTag);
                double usd = 0.0;

                if (usdIndex != -1) {
                    int usdStart = usdIndex + usdTag.length();
                    int usdEnd = obj.indexOf("\"", usdStart);
                    String usdStr = obj.substring(usdStart, usdEnd);

                    if (!usdStr.equals("null") && !usdStr.isEmpty()) {
                        usd = Double.parseDouble(usdStr);
                        
                        log("USD price = " + usd);
                    }
                }

                // Extract usd_foil price
                String foilTag = "\"usd_foil\":\"";
                int foilIndex = obj.indexOf(foilTag);
                double usdFoil = 0.0;

                if (foilIndex != -1) {
                    int foilStart = foilIndex + foilTag.length();
                    int foilEnd = obj.indexOf("\"", foilStart);
                    String foilStr = obj.substring(foilStart, foilEnd);

                    if (!foilStr.equals("null") && !foilStr.isEmpty()) {
                        usdFoil = Double.parseDouble(foilStr);
                        
                        log("Foil price = " + usdFoil);
                    }
                }

                PriceInfo info = new PriceInfo(usd, usdFoil);
                cache.put(key,info);
                log("Caching price for key -> " + key);
                return info;
            }

            // No matching printing → return blank prices
            System.err.println("[ScryfallAPI] No matching collector number found for "
                    + name + " (#" + collectorNumber + ")");
            return new PriceInfo(0.0, 0.0);

        } catch (Exception e) {
        	  System.err.println("[ScryfallAPI] Error fetching price for " 
                      + name + " (#" + collectorNumber + "): " + e.getMessage());
            e.printStackTrace();
            return new PriceInfo(0.0, 0.0);
        }
    }
}