package org.fuber.cabservice.fubercabservice.entity;

public class CabBIll {
	private int bookingId;
	private Double amount;
	public CabBIll(int bookingId, Double amount) {
		super();
		this.bookingId = bookingId;
		this.amount = amount;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
