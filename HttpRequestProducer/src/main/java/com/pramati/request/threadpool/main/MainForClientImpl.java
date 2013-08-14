/**
 * 
 */
package com.pramati.request.threadpool.main;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

import com.pramati.pool.client.clientImpl.Request;
import com.pramati.pool.client.clientImpl.Response;






/**
 * @author sandeep-t
 *
 */
public class MainForClientImpl{
	
	 
	private static final Logger logger = Logger.getLogger(MainForClientImpl.class);
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(100); 
		List<Future<Response>> futureList = new ArrayList<Future<Response>>();
		
		for (int j = 0; j < 20; j++) {
			int i = 0;
			logger.debug("J  "+j);
			long startTime = System.currentTimeMillis();
			while ((System.currentTimeMillis() - startTime) < 1000) {
				// Fire a request.
				try {
					System.out.println("III "+i++);
					Future<Response> response = executor
							.submit(new Request(new URL("http://localhost:8080/HttpRequestConsumer/DataConsumer")));
					futureList.add(response);
					
				} catch (MalformedURLException e) {
					logger.error("Exception occured while processing the Request ",e);
					//e.printStackTrace();
				}

				
			}
			logger.debug("J "+j+ "FutureList Size "+futureList.size() );
		}
		// Shutdown the threads during shutdown of your app
		
		executor.shutdown();
		
		logger.debug("Size of future List "+futureList.size());
	/*	if (futureList.size() > 0) {
		int j=0;
	//	System.out.println("Inside Loop " + futureList.size());
		for (Future<Response> futureList1 : futureList) {
			try {
				System.out.println("Response Code " + futureList1.get());
				//System.out.println("Inside Loop Size " + futureList.size());
				//Main.futureList.remove(futureList1);
				j++;
			} catch (ExecutionException e) {
				
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Toatal Response Processed  "+j);
		logger.debug("Toatal Response Processed  "+j);
	}
	else{
	System.out.println("Bingo **********************************");
	}*/
	}
	
}
