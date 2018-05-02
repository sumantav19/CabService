package org.fuber.cabservice.fubercabservice.service;

import org.fuber.cabservice.exception.InvalidBookingIdException;
import org.fuber.cabservice.exception.NoCabFoundException;
import org.fuber.cabservice.fubercabservice.entity.Cab;
import org.fuber.cabservice.fubercabservice.repository.CabRepository;
import org.fuber.cabservice.fubercabservice.util.CabUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CabService {
	
	@Autowired
	CabRepository cabRespository;	

	
	public Cab getCab(int lat, int lng){
		
		Cab cab = cabRespository.getCab(lat, lng);
		if(cab == null){
			throw new NoCabFoundException("No cab available ");
		}		
		cab.setCurrentLat(lat);
		cab.setCurrentLng(lng);
		return cab;
	}
	
	public double releaseCab(int bookingId, int lat, int lng){
		
		Cab cab = cabRespository.getCabForBookingId(bookingId);
		if(cab == null){
			throw new InvalidBookingIdException("No cab present with  booking id "+bookingId);
		}
		int startLat = cab.getCurrentLat();
		int startLng =  cab.getCurrentLng();
		cabRespository.releaseCab(bookingId, lat, lng);
		
		return getJourneyBill(startLat,startLng, lat, lng,cab.getRate());		
	}
	
	
	
	public double getJourneyBill(int startLat,int startLng,int endLat,int endLng,int rate){
		return CabUtil.getDistance(startLat, startLng, endLat, endLng) * rate;
	}

}
