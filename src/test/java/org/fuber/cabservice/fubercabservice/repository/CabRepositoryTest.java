package org.fuber.cabservice.fubercabservice.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;
import org.fuber.cabservice.exception.InvalidBookingIdException;
import org.fuber.cabservice.fubercabservice.FuberCabserviceApplication;
import org.fuber.cabservice.fubercabservice.entity.Cab;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FuberCabserviceApplication.class)
public class CabRepositoryTest {

	@Autowired
	@Qualifier("pink")
	CabRepository cabRepository;

	@Test
	public void getCab() {
		// assertNotNull(cabRepository);
		Cab cab = cabRepository.getCab(2, 2);
		assertNotNull(cab);
	}

	@Test
	public void getCabStandard() {
		// assertNotNull(cabRepository);
		Cab cab = cabRepository.getCab(2, 2);
		assertEquals("Pink", cab.getCabType());
		assertEquals(5, cab.getRate());
	}

	@Test
	public void getCabNull() {
		CabRepository cabRepository = new CabRepositoryImpl(0, 0, "Standard");
		assertNull(cabRepository.getCab(2, 2));
	}

	@Test(expected = InvalidBookingIdException.class)
	public void releaseCabThrow() {
		cabRepository.releaseCab(10, 2, 2);
	}

	@Test(expected = InvalidBookingIdException.class)
	public void getCabBookingIdThrow() {
		cabRepository.getCabForBookingId(100);
	}

	@Test
	public void getCabBookingId() {
		Cab cab = cabRepository.getCab(2, 2);
		assertEquals(cab, cabRepository.getCabForBookingId(cab.getBookingId()));
	}

	@Test
	public void getMultipleCabBooking() {
		CabRepository cabRepository = new CabRepositoryImpl(100, 5, "Test");
		final ExecutorService executor = Executors.newFixedThreadPool(10);
		Assertions.assertThatCode(() -> {
			for (int i = 0; i < 10; i++) {
				executor.submit(new Runnable() {
					
					@Override
					public void run() {
						for (int j = 0; j < 5; j++) {
							// TODO Auto-generated method stub
							try {
								Cab cab = cabRepository.getCab(2, 2);
								//						System.out.println(cab.getBookingId());
								cabRepository.releaseCab(cab.getBookingId(),j, 10);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
				});
			}
			executor.shutdown();
			
			executor.awaitTermination(1, TimeUnit.DAYS);
		}).doesNotThrowAnyException();

	}
}
