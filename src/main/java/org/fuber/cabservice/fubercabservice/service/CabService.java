package org.fuber.cabservice.fubercabservice.service;

import org.fuber.cabservice.fubercabservice.entity.Cab;
import org.springframework.stereotype.Service;

@Service
public interface CabService {
	
	public Cab getCab(String cabType,int lat, int lng);
	public double releaseCab(String cabType,int bookingId, int lat, int lng);
	public double getJourneyBill(int startLat,int startLng,int endLat,int endLng,int rate);	
	
}	
