package com.shopmanager.geolocation;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shopmanager.Application;
import com.shopmanager.Shop;
import com.shopmanager.ShopUtils;
import com.shopmanager.geolocation.GeoLocation;
import com.shopmanager.geolocation.Location;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class GeoLocationImplTest {
	
	@Autowired
	private GeoLocation geoLocation;
	
	private Shop shop;
	
	private Shop fake_shop;
	
	private DecimalFormat df; 
	
	@Before
	public void setup(){
		
		shop = new Shop();
		shop.setShopName("Beckton");
		shop.setShopNumber("3 Armada Way");
		shop.setPostCode("E6 7ER");
		
		fake_shop = new Shop();
		fake_shop.setShopName("fake");
		fake_shop.setShopNumber("fake");
		fake_shop.setPostCode("123456");
		
		df = new DecimalFormat("#.00"); 
	}
	
	@Test
	public void testGetLocation() throws Exception{
		Location location = geoLocation.getLocation(shop);
		
		Double latitude =  df.parse(df.format(location.getLatitude())).doubleValue();
		Double longitude =  df.parse(df.format(location.getLongitude())).doubleValue();
		
		assertEquals(Double.valueOf(51.52), latitude);
		assertEquals(Double.valueOf(0.08),longitude);
		
	}
	
	@Test(expected = Exception.class)
	public void testGetLocation_Error() throws Exception{
		geoLocation.getLocation(fake_shop);
	}
	

}
