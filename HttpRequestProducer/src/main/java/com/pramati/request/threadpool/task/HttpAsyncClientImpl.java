package com.pramati.request.threadpool.task;



import java.io.IOException;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.Response;
import com.pramati.request.threadpool.main.Main;

public class HttpAsyncClientImpl implements Runnable{

	private static final Logger logger = Logger.getLogger(HttpAsyncClientImpl.class);
	
	int i;
	
	public HttpAsyncClientImpl(int i2) {
		this.i=i2;
		System.out.println("Processing Thread NUmber "+i2);
	}
	
	@Override
	public void run() {
		
		long startTime = System.currentTimeMillis();
		
		try {
			System.out.println("Processing started for Request Number "+i);
			BoundRequestBuilder checkOut = Main.pool.checkOut();
			Future<Response> response = checkOut.execute();
			Main.pool.checkIn(checkOut);
			logger.debug("Toatal Time for Task "+i+" is "+(System.currentTimeMillis() - startTime));
			Main.futureList.add(response);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
}
