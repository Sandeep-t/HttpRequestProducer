package com.pramati.request.threadpool.task;



import java.io.IOException;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.Response;
import com.pramati.request.threadpool.main.MainForAsyncClient;

public class HttpAsyncClientImpl implements Runnable{

	private static final Logger LOGGER = Logger.getLogger(HttpAsyncClientImpl.class);
	
	int requestCount;
	
	public HttpAsyncClientImpl(int requestCount) {
		this.requestCount=requestCount;
	}
	
	@Override
	public void run() {
		
		final long startTime = System.currentTimeMillis();
		
		try {
			//System.out.println("Processing started for Request Number "+i);
			BoundRequestBuilder requestBuilder = MainForAsyncClient.pool.checkOut();
			final Future<Response> response = requestBuilder.execute();
			MainForAsyncClient.pool.checkIn(requestBuilder);
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Toatal Time for Task "+requestCount+" is "+(System.currentTimeMillis() - startTime));	
			}
			MainForAsyncClient.futureList.add(response);
			
		} catch (IOException e) {
			LOGGER.error("Exception occured while processing the RequestNumber "+ requestCount,e);
			
		}
		
		}
}
