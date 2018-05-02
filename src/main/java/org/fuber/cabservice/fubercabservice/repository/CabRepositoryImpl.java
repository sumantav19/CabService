package org.fuber.cabservice.fubercabservice.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fuber.cabservice.exception.InvalidBookingIdException;
import org.fuber.cabservice.fubercabservice.entity.Cab;
import org.fuber.cabservice.fubercabservice.util.CabUtil;



public class CabRepositoryImpl  implements CabRepository{
	List<Cab> freeCab;	
	Map<Integer,Cab> occupiedCabMap;
	
	public CabRepositoryImpl() {
		freeCab =  new ArrayList<Cab>();
		occupiedCabMap =  new HashMap<Integer, Cab>(); 
		for(int i = 0; i< 10; i++){
			freeCab.add(new Cab(1,"Standard",i,i));
		}
	}
	
	
	public CabRepositoryImpl(int initialCabCount,int rate,String cabType) {
		freeCab =  new ArrayList<Cab>();
		occupiedCabMap =  new HashMap<Integer, Cab>(); 
		for(int i = 0; i< initialCabCount; i++){
			freeCab.add(new Cab(rate,cabType,i,i));
		}
	}
	
	public Cab getCabForBookingId(int bookingId){
		Cab cab = occupiedCabMap.get(bookingId);
		if(cab == null){
			throw new InvalidBookingIdException("Invalid booking id");
		}
		return cab;
	}
	public synchronized Cab getCab(int lat, int lng){
		if(freeCab.isEmpty()){
			return null;
		}
		Iterator<Cab> it  =  freeCab.iterator();
		double minDistance = Integer.MAX_VALUE;
		int minDistanceCabIndex=-1;
		for (int i = 0;it.hasNext();i++){
			Cab currentCab  = it.next();
			double currentDistance =  CabUtil.getDistance(currentCab.getCurrentLat(), currentCab.getCurrentLng(), lat, lng);
			if(minDistance > currentDistance){
				minDistance =  currentDistance;
				minDistanceCabIndex = i;
			}
		}
		Cab cab = freeCab.remove(minDistanceCabIndex);
		int bookingId =  CabUtil.getBookingId();
		occupiedCabMap.put(bookingId, cab);
		cab.setBookingId(bookingId);
		return cab;
	}
	
	public synchronized void releaseCab(int bookingId,int lat, int lng){
		Cab cab = occupiedCabMap.remove(bookingId);		
		if(cab == null){
			throw new InvalidBookingIdException("Invalid booking id");
		}		
		cab.setCurrentLat(lat);
		cab.setCurrentLng(lng);
		cab.setBookingId(-1);
		freeCab.add(cab);
	}
}
