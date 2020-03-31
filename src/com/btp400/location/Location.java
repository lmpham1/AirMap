package com.btp400.location;

import java.math.BigDecimal;

public class Location {
	private BigDecimal latitude;
	private BigDecimal longtitude;
	private String locName;
	
	public Location() {
		latitude = new BigDecimal(0);
		longtitude = new BigDecimal(0);
		locName = "";
	}
	
	public Location(double _lat, double _long) {
		setLatitude(_lat);
		setLongtitude(_long);
		setName(null);
	}
	
	public void setLatitude(double _lat) {
		if (_lat < 90 && _lat > -90) {
			latitude = new BigDecimal(_lat);
		}
		else {
			throw new IllegalArgumentException("Invalid Argument Received! Latitude must be a double value between -90 degree and +90 degree");
		}
	}
	
	public void setLongtitude(double _long) {
		if (_long < 180 && _long > -180) {
			longtitude = new BigDecimal(_long);
		}
		else {
			throw new IllegalArgumentException("Invalid Argument Received! Longtitude must be a double value between -90 degree and +90 degree");
		}
	}
	
	public void setName(String name) {
		locName = (name == null) ? "" : name;
	}
	
	public double getLatitude() {
		return latitude.doubleValue();
	}
	
	public double getLongtitude() {
		return longtitude.doubleValue();
	}
	
	public String getName() {
		return locName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Location) {
			Location other = (Location) obj;
			return (
					latitude.equals(other.latitude)
					&& longtitude.equals(other.longtitude)
					&& locName.equals(other.locName)
			);
					
		}
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + latitude.hashCode();
		result = prime * result + longtitude.hashCode();
		result = prime * result + locName.hashCode();
		return result;
	}
	
	@Override
	public String toString() {
		String s = new String();
		if (locName.length() > 0)
			s += "Location name: " + locName + "\n";
		s += "Latitude: " + String.format("%+.5f", latitude.doubleValue())
			+ ", Longtitude: " + String.format("%+.5f\n", longtitude.doubleValue());
		return s;
	}
}
