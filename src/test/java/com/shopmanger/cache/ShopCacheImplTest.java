package com.shopmanger.cache;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.shopmanager.Shop;
import com.shopmanager.cache.ShopCache;
import com.shopmanager.cache.ShopCacheImpl;

public class ShopCacheImplTest {

	private ShopCache shopCache;
	private Shop shop;
	private Shop shop2; 
	private Shop shop3;
	
	@Before
	public void setup(){
		shopCache = new ShopCacheImpl();
		
		shop = new Shop();
		shop.setShopName("shop 1");
		shop.setShopNumber("1");
		shop.setPostCode("E1");
		
		shop2 = new Shop();
		shop2.setShopName("shop 2");
		shop2.setShopNumber("2");
		shop2.setPostCode("E2");
		
		shop3 = new Shop();
		shop3.setShopName("Shop 3");
		shop3.setShopNumber("12");
		shop3.setPostCode("E3");
	}
	
	@Test
	public void testGetShopList_Empty(){
		List<Shop> shopList = shopCache.getShopList();
		assertEquals(0, shopList.size());
	}
	
	@Test
	public void tesAddShop(){
				
		shopCache.add(shop);
		
		List<Shop> shopList = shopCache.getShopList();
		assertEquals(1, shopList.size());
		assertEquals(shop, shopList.get(0));
	}
	
	@Test
	public void tesAddShop_Multiple_Shops(){
	
		shopCache.add(shop);
		shopCache.add(shop2);
		shopCache.add(shop3);
		
		List<Shop> shopList = shopCache.getShopList();
		assertEquals(3, shopList.size());
		assertEquals(shop, shopList.get(0));
		assertEquals(shop2, shopList.get(1));
		assertEquals(shop3, shopList.get(2));
	}
	
}
