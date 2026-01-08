package com.orion.mtg.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.orion.mtg.api.ScryfallAPI;
import com.orion.mtg.model.CardVersion;
import com.orion.mtg.model.PriceInfo;

public class TCGPlayerCSVImporter {

    public static Map<String, CardVersion> importCSV(String fileName) {
        Map<String, CardVersion> cardMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            // Skip header line
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {

                // Simple CSV split (your old code used this)
                String[] parts = line.split(",");

                if (parts.length < 5) {
                    continue; // malformed row
                }

                String name = parts[0].trim();
                String setCode = parts[1].trim();
                String collectorNumber = parts[2].trim();

                String foilStr = parts[3].trim();
                boolean foil = foilStr.equalsIgnoreCase("foil") ||
                               foilStr.equalsIgnoreCase("true") ||
                               foilStr.equalsIgnoreCase("yes");

                int quantity = 0;
                try {
                    quantity = Integer.parseInt(parts[4].trim());
                } catch (NumberFormatException e) {
                    quantity = 1;
                }

                // ⭐ NEW STEP: Fetch pricing from Scryfall
                PriceInfo prices = ScryfallAPI.fetchPrice(name, collectorNumber);

                // ⭐ Construct the new CardVersion object
                CardVersion card = new CardVersion(
                        name,
                        setCode,
                        collectorNumber,
                        foil,
                        quantity,
                        prices
                );

                // Key: name + set + collector number + foil
                String key = name + "|" + setCode + "|" + collectorNumber + "|" + foil;

                cardMap.put(key, card);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cardMap;
    }
}