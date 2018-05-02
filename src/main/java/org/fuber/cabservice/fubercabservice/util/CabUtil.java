package org.fuber.cabservice.fubercabservice.util;

import java.util.concurrent.atomic.AtomicInteger;

public class CabUtil {	
	private static AtomicInteger id = new AtomicInteger(0);
	public static double getDistance(int startLat,int startLng, int endLat, int endLng){
		return Math.sqrt(Math.pow(startLat-endLat,2) + Math.pow(startLng-endLng, 2));
	}
	public static int getBookingId(){
		return id.getAndIncrement();
	}

}
