package org.fuber.cabservice.fubercabservice.service;

import java.util.Map;

import org.fuber.cabservice.exception.InvalidCabTypeException;
import org.fuber.cabservice.exception.InvalidBookingIdException;
import org.fuber.cabservice.exception.NoCabFoundException;
import org.fuber.cabservice.fubercabservice.entity.Cab;
import org.fuber.cabservice.fubercabservice.repository.CabRepository;
import org.fuber.cabservice.fubercabservice.util.CabUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CabServiceImpl implements CabService {
	@Autowired
	Map<String,CabRepository> cabRespositoryList;	

	
	public Cab getCab(String cabType,int lat, int lng){
		CabRepository cabRepository = cabRespositoryList.get(cabType);
		if( cabRepository == null){
			throw new InvalidCabTypeException("Incorrect cab type");
		}
		Cab cab = cabRepository.getCab(lat, lng);
		if(cab == null){
			throw new NoCabFoundException("No cab available of type "+cabType);
		}		
		cab.setCurrentLat(lat);
		cab.setCurrentLng(lng);
		return cab;
	}
	
	public double releaseCab(String cabType,int bookingId, int lat, int lng){
		CabRepository cabRepository = cabRespositoryList.get(cabType);
		if( cabRepository == null){
			throw new InvalidCabTypeException("Incorrect cab type");
		}
		Cab cab = cabRepository.getCabForBookingId(bookingId);
		if(cab == null){
			throw new InvalidBookingIdException("No cab present with  booking id "+bookingId);
		}
		int startLat = cab.getCurrentLat();
		int startLng =  cab.getCurrentLng();
		cabRepository.releaseCab(bookingId, lat, lng);
		
		return getJourneyBill(startLat,startLng, lat, lng,cab.getRate());		
	}
	
	
	public double getJourneyBill(int startLat,int startLng,int endLat,int endLng,int rate){
		return CabUtil.getDistance(startLat, startLng, endLat, endLng) * rate;
	}

	
}
