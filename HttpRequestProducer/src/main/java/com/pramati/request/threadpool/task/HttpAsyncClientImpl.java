package com.pramati.request.threadpool.task;



import java.io.IOException;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.Response;
import com.pramati.request.threadpool.main.MainForAsyncClient;

public class HttpAsyncClientImpl implements Runnable{

	private static final Logger logger = Logger.getLogger(HttpAsyncClientImpl.class);
	
	int i;
	
	public HttpAsyncClientImpl(int i2) {
		this.i=i2;
	}
	
	@Override
	public void run() {
		
		long startTime = System.currentTimeMillis();
		
		try {
			//System.out.println("Processing started for Request Number "+i);
			BoundRequestBuilder requestBuilder = MainForAsyncClient.pool.checkOut();
			Future<Response> response = requestBuilder.execute();
			MainForAsyncClient.pool.checkIn(requestBuilder);
			logger.debug("Toatal Time for Task "+i+" is "+(System.currentTimeMillis() - startTime));
			MainForAsyncClient.futureList.add(response);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
}
