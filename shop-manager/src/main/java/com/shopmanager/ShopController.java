package com.shopmanager;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopmanager.cache.ShopCache;
import com.shopmanager.geolocation.GeoLocation;
import com.shopmanager.geolocation.Location;

@RestController
@RequestMapping()
public class ShopController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
		
	@Autowired
	private ShopCache shopCache;
	
	@Autowired
	private GeoLocation geoLocation;

	@RequestMapping(value = "/shop", method = RequestMethod.POST)
	public ResponseEntity<Void>  addShop(@RequestBody Shop shop) {
	
		try {
			Location location = geoLocation.getLocation(shop);

			shop.setLatitude(location.getLatitude());
			shop.setLongitude(location.getLongitude());
			
			shopCache.add(shop);
			
			logger.info("Added shop: " + shop);
			
		} catch (Exception e) {
			logger.error("Unable to add shop", e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
		
	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public ResponseEntity<Shop> getNearestShop(@RequestParam(value = "latitude", defaultValue = "0")Double latitude, 
										 @RequestParam(value = "longitude", defaultValue = "0")Double longitude) {
		
		List<Shop> shopList = shopCache.getShopList();
		if(shopList.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			Shop shop = ShopUtils.getNearestShop(latitude, longitude, shopList);
			return new ResponseEntity<>(shop, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/shopList", method = RequestMethod.GET)
	public ResponseEntity<List<Shop>>  getShopList() {
		List<Shop> shopList = shopCache.getShopList();
		return new ResponseEntity<>(shopList, HttpStatus.OK);
	}
	
	void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}
	
	void setShopCache(ShopCache shopCache) {
		this.shopCache = shopCache;
	}

}
