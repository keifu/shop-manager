package com.shopmanager.geolocation;

import com.shopmanager.Shop;


/**
 * 
 * 
 * @author Keith
 *
 */
public interface GeoLocation {

	public Location getLocation(Shop shop) throws Exception;
	
}
