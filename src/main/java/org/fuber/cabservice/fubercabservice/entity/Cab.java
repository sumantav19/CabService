package org.fuber.cabservice.fubercabservice.entity;

public class Cab {

	private int bookingId;
	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getCurrentLat() {
		return currentLat;
	}

	public void setCurrentLat(int currentLat) {
		this.currentLat = currentLat;
	}

	public int getCurrentLng() {
		return currentLng;
	}

	public void setCurrentLng(int currentLng) {
		this.currentLng = currentLng;
	}

	private int rate;
	private String cabType;
	private int currentLat;
	private int currentLng;

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getCabType() {
		return cabType;
	}

	public void setCabType(String cabType) {
		this.cabType = cabType;
	}

	

	public Cab(int rate, String cabType, int currentLat, int currentLng) {		
		
		this.rate = rate;
		this.cabType = cabType;
		this.currentLat = currentLat;
		this.currentLng = currentLng;
	}

	@Override
	public String toString() {
		return "Cab [rate=" + rate + ", cabType=" + cabType + "]";
	}

}
