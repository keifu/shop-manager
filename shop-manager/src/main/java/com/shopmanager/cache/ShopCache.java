package com.shopmanager.cache;

import java.util.List;

import com.shopmanager.Shop;

/**
 * 
 * Interface for shop cache
 * 
 * @author Keith
 *
 */
public interface ShopCache {
	
	/**
	 * Add shop to cache
	 * 
	 * @param shop
	 */
	public void add(Shop shop);
	
	/**
	 * Returns all shops in cache
	 * 
	 * @return
	 */
	public List<Shop> getShopList();

}
