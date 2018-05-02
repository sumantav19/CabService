package org.fuber.cabservice.fubercabservice.repository;

import org.fuber.cabservice.fubercabservice.entity.Cab;

public interface CabRepository {
	
	public Cab getCabForBookingId(int bookingId);
	public  Cab getCab(int lat, int lng);	
	public  void releaseCab(int bookingId,int lat, int lng);
}
