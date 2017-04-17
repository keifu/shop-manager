package com.shopmanager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.shopmanager.cache.ShopCache;
import com.shopmanager.geolocation.GeoLocation;
import com.shopmanager.geolocation.Location;

public class ShopControllerTest {
	
	private ShopController shopController;
	private GeoLocation geoLocation;
	private ShopCache shopCache;
	
	@Before
	public void setup() throws Exception{
		
		geoLocation = mock(GeoLocation.class);
		
		when(geoLocation.getLocation(any(Shop.class))).thenReturn(new Location(1.2, 1.3));
		
		shopCache = mock(ShopCache.class);
		List<Shop> shopList = new ArrayList<>();
		Shop shop = new Shop();
		shop.setShopName("shop Name");
		shop.setShopNumber("shop Number");
		shop.setPostCode("E6");
		shop.setLatitude(1.2);
		shop.setLongitude(1.3);
		shopList.add(shop);
		when(shopCache.getShopList()).thenReturn(shopList);
		
		shopController = spy(ShopController.class);
		
		shopController.setGeoLocation(geoLocation);
		shopController.setShopCache(shopCache);
	}
	
	@Test
	public void testAddShop() throws Exception{
		Shop shop = new Shop();
		
		shopController.addShop(shop);
		
		assertEquals(Double.valueOf(1.2), shop.getLatitude());
		assertEquals(Double.valueOf(1.3), shop.getLongitude());
		verify(geoLocation, times(1)).getLocation(shop);
		verify(shopCache, times(1)).add(shop);
		
	}
	
	@Test
	public void testGetNearest() throws Exception{
		
		shopController.getNearestShop(1.2, 1.3);
		
		int a = 3;
			
		verify(shopCache, times(1)).getShopList();
		verify(shopController, times(1)).getNearestShop(anyDouble(), anyDouble(), any());
		
	}
	
	@Test
	public void testGetShopList() throws Exception{
		
		shopController.getShopList();
		verify(shopCache, times(1)).getShopList();
		
	}

}
