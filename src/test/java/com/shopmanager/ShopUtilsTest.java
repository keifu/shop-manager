package com.shopmanager;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.shopmanager.Shop;
import com.shopmanager.ShopUtils;

@RunWith(JUnitParamsRunner.class)
public class ShopUtilsTest {

	private Shop shop;
	private Shop shop2;
	private Shop shop3;

	@Before
	public void setup() {

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
	public void testGetNearestShop_EmptyShopList() {
		List<Shop> shopList = Collections.emptyList();
		Shop nearestShop = ShopUtils.getNearestShop(1.23, 1.23, shopList);
		assertEquals(null, nearestShop);
	}

	@Test
	public void testGetNearestShop_OneShop() {
		List<Shop> shopList = new ArrayList<>();
		shopList.add(shop);
		Shop nearestShop = ShopUtils.getNearestShop(1.23, 1.23, shopList);
		assertEquals(shop, nearestShop);
	}

	@Test
	public void testGetNearestShop_MultipleShops() {
		List<Shop> shopList = new ArrayList<>();
		shopList.add(shop);
		shopList.add(shop2);
		shopList.add(shop3);
		Shop nearestShop = ShopUtils.getNearestShop(40.0, 40.0, shopList);
		assertEquals(shop2, nearestShop);
	}

	public static Collection<Object[]> getLatLngTestData() {
		return Arrays.asList(new Object[][] { 
				{ 0.0, 0.0, 0.0, 0.0, 0.0 },
				{ 10.0, 10.0, 10.0, 10.0, 0.0 }, 
				{ 10.0, 10.0, 20.0, 20.0, 959.87 },
				{ -10.0, -10.0, -20.0, -20.0, 959.87},
				{ -10.0, -10.0, 20.0, 20.0, 2908.43},
				{ 10.0, 10.0, -20.0, -20.0, 2908.43},
		});
	}

	@Test
	@Parameters(method = "getLatLngTestData")
	public void testCalculateDistance(double lat1, double lng1, double lat2,
			double lng2, double expectedDistance) throws ParseException {
		
		Double distance = ShopUtils.calculateDistance(lat1, lng1, lat2, lng2);
		DecimalFormat df = new DecimalFormat("#.00"); 
		distance = df.parse(df.format(distance)).doubleValue();
		
		assertEquals(Double.valueOf(expectedDistance), distance);

	}

}
