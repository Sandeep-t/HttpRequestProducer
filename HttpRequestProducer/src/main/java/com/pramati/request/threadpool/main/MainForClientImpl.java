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

import com.pramati.pool.client.clientimpl.Request;
import com.pramati.pool.client.clientimpl.Response;






/**
 * @author sandeep-t
 *
 */
public class MainForClientImpl{ 
	
	 
	private static final Logger LOGGER = Logger.getLogger(MainForClientImpl.class);
	public static void main(String[] args) throws Exception { 
		
		Request task;
		final String spec = "http://localhost:8080/HttpRequestConsumer/DataConsumer";
		try {
			URL url = new URL(spec); 
			task = new Request(url);
			
		} catch (MalformedURLException e1) {
			LOGGER.error("MalformedURLException occured while parsing the URL "+spec, e1);
			throw new Exception("MalformedURLException occured while parsing the URL "+spec, e1);
		}
		final ExecutorService executor = Executors.newFixedThreadPool(100); 
		final List<Future<Response>> futureList = new ArrayList<Future<Response>>();
		for (int loopCounter = 0; loopCounter < 2; loopCounter++) {
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Loop Number "+loopCounter);	
			}
			
			final long startTime = System.currentTimeMillis();
			
			while ((System.currentTimeMillis() - startTime) < 1000) {
				
				Future<Response> response = executor 
						.submit(task);
				futureList.add(response);
				
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("J "+loopCounter+ "FutureList Size "+futureList.size() );
			}
			
		}
		// Shutdown the threads during shutdown of your app
		
		executor.shutdown(); 
		
	/*	if (futureList.size() > 0) { // NOPMD by sandeep-t on 16/8/13 4:44 PM
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
