package com.shopmanager;

import java.util.Objects;

public class Shop {

	private String shopName;
	private String shopNumber;
	private String postCode;
	private Double latitude;
	private Double longitude;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopNumber() {
		return shopNumber;
	}

	public void setShopNumber(String shopNumber) {
		this.shopNumber = shopNumber;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(shopName, shopNumber, postCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shop other = (Shop) obj;
	
		return Objects.equals(this.shopName, other.shopName)
				&& Objects.equals(this.shopNumber, other.shopNumber)
				&& Objects.equals(this.postCode, other.postCode);
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s, %f,%f", shopName, shopNumber, postCode,
				latitude, longitude);
	}

}
