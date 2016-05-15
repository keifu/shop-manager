package com.shopmanager.geolocation;

public class Location {
	
	Double latitude;
	Double longitude;
	
	public Location(Double latitude, Double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Double getLatitude() {
		return latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}

}
