package org.fuber.cabservice.fubercabservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.fuber.cabservice.exception.InvalidBookingIdException;
import org.fuber.cabservice.exception.NoCabFoundException;
import org.fuber.cabservice.fubercabservice.FuberCabserviceApplication;
import org.fuber.cabservice.fubercabservice.entity.Cab;
import org.fuber.cabservice.fubercabservice.repository.CabRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=FuberCabserviceApplication.class)
public class CabServiceTest {
	
	@InjectMocks
	CabService cabService;
	
	
	@Mock
	CabRepository cabRepository;
	
	
	
	@Test
	public void testCabRespositoryList(){
		assertNotNull(cabRepository);
	}
	

	
	@Test(expected=NoCabFoundException.class)
	public void testGetNoCab(){
		when(cabRepository.getCab(1, 1)).thenReturn(null);
		cabService.getCab( 2, 3);
	}
	
	
	public void testGetCab(){		
		when(cabRepository.getCab(1, 1)).thenReturn(new Cab(1, "Standard", 2, 3));
		Cab cab = cabService.getCab( 2, 3);
		assertEquals(1, cab.getRate());
		assertEquals("Standard", cab.getCabType());
	}
	
	@Test(expected =  InvalidBookingIdException.class)
	public void testGetCabAfterReleaseInvalidBookingId(){		
		cabService.releaseCab( 10, 2, 2);		
	}
	
	


}
