package com.shopmanager.geolocation;

import com.shopmanager.Shop;


/**
 * GeoLocaton API that gets the location of a shop
 * 
 * @author Keith
 *
 */
public interface GeoLocation {

	public Location getLocation(Shop shop) throws Exception;
	
}
