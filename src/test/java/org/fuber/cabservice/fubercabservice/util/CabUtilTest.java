package org.fuber.cabservice.fubercabservice.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CabUtilTest {
	
	@Test
	public void calculateDistance(){
		assertEquals(5,CabUtil.getDistance(0, 0, 3, 4),0.001);		
	}
	
	@Test
	public void bookingIdIncrements(){
		int temp = CabUtil.getBookingId();
		int temp1 = CabUtil.getBookingId();
		assertEquals(temp+1, temp1);
	}
}
