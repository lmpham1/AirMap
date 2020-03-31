package com.btp400.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import com.btp400.AirStatistics.*;
import com.btp400.HTTPRequest.*;
import com.btp400.location.*;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 

public class Controller {
	private ArrayList<LocationStatistic> locations = new ArrayList<LocationStatistic>();
	private ArrayList<Pollutant> pollutants = new ArrayList<Pollutant>();
	private AirDataRequest airRequest = new AirDataRequest();
	private LocationRequest locRequest = new LocationRequest();
	
	private void addLocation(LocationStatistic ls) {
		boolean flag = false;
		for (int i = 0; i < locations.size(); ++i) {
			if (locations.get(i).getLatitude() == ls.getLatitude() && locations.get(i).getLongtitude() == ls.getLongtitude()) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			locations.add(ls);
		}
	}
	
	
	private void addPollutantFromJson(JSONObject jo) {
		
		String fullName = (String) jo.get("full_name");
		String codeName = (String) jo.get("display_name");
		String unit = (String) ((JSONObject)jo.get("concentration")).get("units");
		Pollutant p = new Pollutant(fullName, codeName, unit);
		JSONObject sources_effects = (JSONObject) jo.get("sources_and_effects");
		String source = (String) sources_effects.get("sources");
		String effect = (String) sources_effects.get("effects");
		p.setDescription(source, effect);
		this.addPollutant(p);
	}
	
	private void getStats(PollutantStatistic ps, Map map) {
		Iterator<Map.Entry> iter = map.entrySet().iterator(); 
		
		while(iter.hasNext()) {
			Map.Entry pair = iter.next();
			JSONObject tempObj = (JSONObject) pair.getValue();
			String name = (String) tempObj.get("display_name");
			if (ps.getCodeName().equals(name)) {
				double concentration = (double) ((Number)((JSONObject)tempObj.get("concentration")).get("value"));
				JSONObject aqiInfo = (JSONObject)tempObj.get("aqi_information");
				JSONObject aqiJson = (JSONObject) aqiInfo.get("baqi");
				int aqi = (int) ((Number)aqiJson.get("aqi")).intValue();
				String status = (String) aqiJson.get("category");
				ps.setConcentration(concentration);
				ps.setAQI(aqi);
				ps.setStatus(status);
			}
		}
	}
	
	private void addPollutant(Pollutant p) {
		boolean flag =false;
		for (Pollutant i : pollutants) {
			if (p.getID() == i.getID()) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			pollutants.add(p);
		}
	}
	
	public Controller() {
		try {
			airRequest.setFeatures(FeaturesList.pollutants_concentrations, FeaturesList.sources_and_effects);
			airRequest.sendGet(48.857456, 2.354611);
			JSONObject jsonResult = airRequest.getJsonResult();
			
			Map pollutantsArr = (Map)((JSONObject)jsonResult.get("data")).get("pollutants");
			
			Iterator<Map.Entry> iter = pollutantsArr.entrySet().iterator();
			
			while(iter.hasNext()) {
				Map.Entry pair = iter.next();
				JSONObject jo = (JSONObject) pair.getValue();
				this.addPollutantFromJson(jo);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public LocationStatistic getALocationStats(double _lat, double _long) throws Exception {
		try {
			airRequest.setFeatures(FeaturesList.pollutants_concentrations, FeaturesList.pollutants_aqi_information);
			airRequest.sendGet(_lat, _long);
			
			LocationStatistic ls = new LocationStatistic(_lat, _long);
			ls.updatePollutants(pollutants);
			
			JSONObject jsonResult = airRequest.getJsonResult();
			JSONObject data = (JSONObject)jsonResult.get("data");
			
			if (data != null) {
				ArrayList<PollutantStatistic> ps = ls.getAllPollutants();
				Map pollutantsArr = (Map) data.get("pollutants");
			
				for (PollutantStatistic p : ps) {
					this.getStats(p, pollutantsArr);
				}
				
				//get location name
				locRequest.sendGet(_lat, _long);
				String addressData = (String)((JSONObject)locRequest.getJsonResult().get("plus_code")).get("compound_code");
				ls.setName(addressData);
			
				this.addLocation(ls);
				return ls;
			} else
				throw new IllegalArgumentException("Sorry! The specified location is not yet supported! Try another one!");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			StackTraceElement[] ste= e.getStackTrace();
			for(StackTraceElement i : ste) {
				System.out.println(i);
			}
			//System.out.println(e.getCause());
			throw e;
		}
	}
	
	public String displayStats(double _lat, double _long) {
		String s = "";
		try {
			LocationStatistic ls = this.getALocationStats(_lat, _long);
		
			s += ls;
		} catch(Exception e) {
			s += e.getMessage();
			//s += e.getCause();
		}
		
		return s;
	}
	
	public LocationStatistic getALocationStats(String address) throws Exception{
		try {
			locRequest.sendGet(address);
			
			JSONArray results =(JSONArray) locRequest.getJsonResult().get("results");
			if (results.size() > 0) {
				JSONObject jo = (JSONObject)((JSONObject)((JSONObject) results.get(0)).get("geometry")).get("location");
				double _lat = (double)((Number)jo.get("lat"));
				double _long = (double)((Number)jo.get("lng"));
				return this.getALocationStats(_lat, _long);
			}
			else
				throw new Exception("No result found!");
		}
		
		catch(Exception e) {
			throw e;
		}
	}
	
	public String displayStats(String address) {
		String s = "";
		try {
			LocationStatistic ls = this.getALocationStats(address);
			//s += "Location Name: " + address + "\n";
			s += ls;
		} catch(Exception e) {
			s += e.getMessage();
			//s += e.getCause();
		}
		
		return s;
	}
}
