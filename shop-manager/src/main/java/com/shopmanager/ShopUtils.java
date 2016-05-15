package com.shopmanager;
import java.util.List;

public class ShopUtils {

	public static Shop getNearestShop(Double latitude, Double longitude,
											List<Shop> shopList) {

		if(shopList == null || shopList.isEmpty()){
			return null;
		}
		
		double diff = Integer.MAX_VALUE;
		Shop nearestShop = null;
		
		for(Shop shop : shopList){
			
			double tempdiff = calculateDistance(latitude, longitude, shop.getLatitude(), shop.getLongitude());
			if(tempdiff < diff){
				diff = tempdiff;
				nearestShop = shop;
			}
		}
		
		return nearestShop;
		
	}
	
	/**
	 * 
	 * Calculates the distance between two points using Haversine formula
	 * 
	 * Code is taken from: http://stackoverflow.com/questions/120283/how-can-i-measure-distance-and-create-a-bounding-box-based-on-two-latitudelongi
	 * 
	 * Could have used Pythagoras theorem if just want an approximation
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
	    double earthRadius = 3958.75; // miles (or 6371.0 kilometers)
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double sindLat = Math.sin(dLat / 2);
	    double sindLng = Math.sin(dLng / 2);
	    double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
	            * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = earthRadius * c;

	    return dist;
	    }

}
