package com.shopmanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class ShopControllerIntegrationTest {

	public static final String SHOP_SERVICE_URI = "http://localhost:8080/shop";

	public static final String SHOPLIST_SERVICE_URI = "http://localhost:8080/shoplist";

	private Shop shop;
	private Shop shop2;
	private Shop shop3;
	private Shop shop4;
	private Shop shop_not_exist;

	@Before
	public void setup() {

		shop = new Shop();
		shop.setShopName("Nandos");
		shop.setShopNumber("3 Armada Way");
		shop.setPostCode("E6 7ER");

		shop2 = new Shop();
		shop2.setShopName("Nandos 2");
		shop2.setShopNumber("552 Mile End Rd");
		shop2.setPostCode("E3 4PL");

		shop3 = new Shop();
		shop3.setShopName("Nandos 3");
		shop3.setShopNumber("Cabots Place East");
		shop3.setPostCode("E14 4QT");

		shop4 = new Shop();
		shop4.setShopName("Nandos 4");
		shop4.setShopNumber("Romford Rd");
		shop4.setPostCode("E15 4LJ");
		
		shop_not_exist = new Shop();
		shop_not_exist.setShopName("Fake shop");
		shop_not_exist.setShopNumber("Road");
		shop_not_exist.setPostCode("ABCDEFG1234455");

	}

	@Test
	public void testAddShops() {

		RestTemplate restTemplate = new RestTemplate();

		restTemplate.postForLocation(SHOP_SERVICE_URI, shop);
		restTemplate.postForLocation(SHOP_SERVICE_URI, shop2);
		restTemplate.postForLocation(SHOP_SERVICE_URI, shop3);

		Map<String, Double> params = new HashMap<>();
		// location of E16
		params.put("latitude", 51.5051447);
		params.put("longitude", 0.0195413);

		Shop nearestShop = restTemplate.getForObject(SHOP_SERVICE_URI,
				Shop.class, params);
		assertEquals(shop3, nearestShop);

		@SuppressWarnings("unchecked")
		List<Shop> shopList = restTemplate.getForObject(SHOPLIST_SERVICE_URI,
				List.class);
		assertEquals(4, shopList.size());

	}

	@Test
	public void testAddShops_Duplicate() {

		RestTemplate restTemplate = new RestTemplate();

		restTemplate.postForLocation(SHOP_SERVICE_URI, shop4);
		
		try{
			restTemplate.postForLocation(SHOP_SERVICE_URI, shop4);
			fail("expected exception");
		}catch (HttpClientErrorException e) {
	        assertEquals(HttpStatus.CONFLICT, e.getStatusCode());
	    }

		@SuppressWarnings("unchecked")
		List<Shop> shopList = restTemplate.getForObject(SHOPLIST_SERVICE_URI, List.class);
		assertEquals(1, shopList.size());

	}
	
	@Test
	public void testAddShop_Not_Found() {

		RestTemplate restTemplate = new RestTemplate();

		try{
			restTemplate.postForLocation(SHOP_SERVICE_URI, shop_not_exist);
			fail("expected exception");
		}catch (HttpClientErrorException e) {
	        assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
	    }


	}
}
