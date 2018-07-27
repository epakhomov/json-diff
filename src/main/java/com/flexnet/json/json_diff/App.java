package com.flexnet.json.json_diff;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class App {

	public static void main(String[] args) {

		JSONParser jsonParser = new JSONParser();
		Object object, object2;
		

		try {

			object = jsonParser.parse(new FileReader("01.json"));
			object2 = jsonParser.parse(new FileReader("02.json"));
			JSONObject jsonObject = (JSONObject) object;
			JSONObject jsonObject2 = (JSONObject) object2;

			String projectName = (String) jsonObject.get("projectName");
			String projectName2 = (String) jsonObject2.get("projectName");
			

			JSONArray inventoryItems = (JSONArray) jsonObject.get("inventoryItems");
			JSONArray inventoryItems2 = (JSONArray) jsonObject2.get("inventoryItems");
						
						
			Iterator itr = inventoryItems.iterator();
			Iterator itr2 = inventoryItems2.iterator();
			ArrayList<String> nameA = new ArrayList<String>();
			ArrayList<String> nameB = new ArrayList<String>();

			while (itr.hasNext()) {

				Object slide = itr.next();
				JSONObject jsonObject1 = (JSONObject) slide;
				String name = (String) jsonObject1.get("name");
				
				nameA.add(name);		
											
			}
			
			while (itr2.hasNext()) {

				Object slide2 = itr2.next();
				JSONObject jsonObject3 = (JSONObject) slide2;
				String name2 = (String) jsonObject3.get("name");
				
				nameB.add(name2);
												
			}
			
		
			ArrayList<String> result = (ArrayList<String>) nameA.stream().filter(elem -> !nameB.contains(elem)).collect(Collectors.toList());
			
			System.out.println("Inventory deleted: " + result);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}
}
