package com.shopmanager.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shopmanager.Shop;

/**
 * Implementation of {@link ShopCache} which stores shops in an array
 * 
 * @author Keith
 *
 */
@Component
public class ShopCacheImpl implements ShopCache{

	List<Shop> shopList;
	
	public ShopCacheImpl() {
		shopList = Collections.synchronizedList(new ArrayList<Shop>());
	}
	
	@Override
	public boolean add(Shop shop) {
		
		synchronized (shopList) {
			if(!shopList.contains(shop)){
				return shopList.add(shop);
			}
			return false;
		}	
	}

	@Override
	public List<Shop> getShopList() {
		return new ArrayList<>(shopList);
	}

}
