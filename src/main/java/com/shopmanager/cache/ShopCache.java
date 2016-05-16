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
	 * Add Shop to cache
	 * 
	 * @param shop
	 * @return true shop is added to cache, false if already present in cache
	 */
	public boolean add(Shop shop);
	
	/**
	 * Returns all shops in cache
	 * 
	 * @return
	 */
	public List<Shop> getShopList();

}
