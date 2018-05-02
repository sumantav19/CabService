package org.fuber.cabservice.fubercabservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.fuber.cabservice.exception.InvalidBookingIdException;
import org.fuber.cabservice.exception.InvalidCabTypeException;
import org.fuber.cabservice.exception.NoCabFoundException;
import org.fuber.cabservice.fubercabservice.FuberCabserviceApplication;
import org.fuber.cabservice.fubercabservice.entity.Cab;
import org.fuber.cabservice.fubercabservice.repository.CabRepository;
import org.fuber.cabservice.fubercabservice.repository.CabRepositoryImpl;
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
	CabServiceImpl cabService;
	
	
	@Mock
	Map<String,CabRepository> cabRepositoryList;	
	
	
	@Test(expected=InvalidCabTypeException.class)
	public void testGetCab(){
		when(cabRepositoryList.get("pink")).thenReturn(null);
		cabService.getCab("pink", 2, 3);
	}
	
	@Test(expected=NoCabFoundException.class)
	public void testGetCabPinkNoCab(){
		when(cabRepositoryList.get("pink")).thenReturn(new CabRepositoryImpl(0,0,"pink"));
		cabService.getCab("pink", 2, 3);
	}
	
	@Test(expected=NoCabFoundException.class)
	public void testGetCabNoCabFoundOnCabLeft(){
		when(cabRepositoryList.get("pink")).thenReturn(new CabRepositoryImpl(0,0,"pink"));
		cabService.getCab("pink", 2, 4);
		cabService.getCab("pink", 1, 4);
	}
	
	@Test(expected=InvalidCabTypeException.class)
	public void testReleaseCabWithInvalidCabType(){
		when(cabRepositoryList.get("pink")).thenReturn(null);
		cabService.releaseCab("pink", 10, 2, 3);
	}
	
	@Test
	public void testGetCabAfterRelease(){
		when(cabRepositoryList.get("pink")).thenReturn(new CabRepositoryImpl(1,0,"pink"));
		Cab cab = cabService.getCab("pink", 2, 4);
		cabService.releaseCab("pink", cab.getBookingId(), 2, 2);
		assertNotNull(cabService.getCab("pink", 1, 4));
	}


}
