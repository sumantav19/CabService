/*
 * Created to test the repository for cab list and cab map
 */
package org.fuber.cabservice.fubercabservice.repository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RepositoryTest {
	
	public static void main(String arg[]){
		CabRepository cabRepostory = new CabRepositoryImpl(100,5,"Pink");
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for(int i = 0 ; i<10;i++){
//			System.out.println("Submit Task");
			executor.submit(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//Thread pool just swallows this exception, as if it never happened
					//  print stack trace is to track the created exception
					try {
						System.out.println(cabRepostory.getCab(4, 4));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			
		}
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End of test");
	}

}
