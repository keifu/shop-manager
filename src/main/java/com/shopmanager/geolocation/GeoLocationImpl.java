package com.shopmanager.geolocation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.shopmanager.Shop;

/**
 * 
 * Implemenation of {@link GeoLocation} interface that uses google map service
 * 
 * @author Keith
 *
 */
@Component
public class GeoLocationImpl implements GeoLocation {

	@Value("${google.mapservice.key}")
	private String serviceKey;
	
	@Override
	public Location getLocation(Shop shop) throws Exception {
		
		GeoApiContext context = new GeoApiContext().setApiKey(this.serviceKey);
		
		GeocodingResult[] results = GeocodingApi.geocode(context,
					   shop.getShopNumber() + ", " + shop.getPostCode()).await();
		 
		LatLng latlng = results[0].geometry.location;
		
		Location location = new Location(latlng.lat, latlng.lng);
		
		return location;
	}

}
