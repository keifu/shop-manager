package com.shopmanager;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.shopmanager.Shop;
import com.shopmanager.ShopUtils;

public class ShopUtilsTest {
	
	private Shop shop;
	private Shop shop2; 
	private Shop shop3;
	
	@Before
	public void setup(){
		
		shop = new Shop();
		shop.setShopName("shop 1");
		shop.setShopNumber("1");
		shop.setPostCode("E1");
		shop.setLatitude(5.0);
		shop.setLongitude(5.0);
		
		shop2 = new Shop();
		shop2.setShopName("shop 2");
		shop2.setShopNumber("2");
		shop2.setPostCode("E2");
		shop2.setLatitude(50.0);
		shop2.setLongitude(50.0);
		
		shop3 = new Shop();
		shop3.setShopName("Shop 3");
		shop3.setShopNumber("12");
		shop3.setPostCode("E3");
		shop3.setLatitude(100.0);
		shop3.setLongitude(100.0);
	}
	
	@Test
	public void testGetNearestShop_EmptyShopList(){
		List<Shop> shopList = Collections.emptyList();
		Shop nearestShop = ShopUtils.getNearestShop(1.23, 1.23, shopList);
		assertEquals(null, nearestShop);
	}
	
	@Test
	public void testGetNearestShop_OneShop(){
		List<Shop> shopList = new ArrayList<>();
		shopList.add(shop);
		Shop nearestShop = ShopUtils.getNearestShop(1.23, 1.23, shopList);
		assertEquals(shop, nearestShop);
	}
	
	@Test
	public void testGetNearestShop_MultipleShops(){
		List<Shop> shopList = new ArrayList<>();
		shopList.add(shop);
		shopList.add(shop2);
		shopList.add(shop3);
		Shop nearestShop = ShopUtils.getNearestShop(40.0, 40.0, shopList);
		assertEquals(shop2, nearestShop);
	}

}
