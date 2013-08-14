/**
 * 
 */
package com.pramati.request.threadpool.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import com.pramati.pool.client.impl.ClientPool;
import com.pramati.request.threadpool.executor.ThreadExecutor;
import com.pramati.request.threadpool.task.HttpAsyncClientImpl;

/**
 * @author SandeepTiwari
 * 
 */
public class MainForAsyncClient {

	private static final Logger logger = Logger.getLogger(MainForAsyncClient.class);
	// public static Map<Integer,Long> threadTimeMap=
	// Collections.synchronizedMap(new HashMap<Integer,Long>());
	public static ClientPool pool = new ClientPool(
			"http://localhost:8080/HttpRequestConsumer/DataConsumer",
			"username","testingClient", new AsyncHttpClient());
	
/*	public static ClientPool pool1 = new ClientPool(
			"http://localhost/index.html", new AsyncHttpClient());*/
	
	
	public static List<Future<Response>> futureList = new ArrayList<Future<Response>>();

	public static void main(String args[]) throws InterruptedException {
		int i = 1;
		ThreadExecutor executor = new ThreadExecutor();
		long startTime = System.currentTimeMillis();
		while ((System.currentTimeMillis() - startTime) < 1000) {
			// while (i<300000) {
			HttpAsyncClientImpl httpImpl = new HttpAsyncClientImpl(i);
			try {
				executor.executeTask(httpImpl);

			} catch (Exception e) {
				logger.error("Exception occured for Reqest " + i, e);
			}
			i++;
			
		}
		logger.debug("OUT " + MainForAsyncClient.futureList.size());
		
		
		/*if (Main.futureList.size() > 0) {
			int j=0;
			logger.debug("Inside Loop " + Main.futureList.size());
			for (Future<Response> futureList1 : Main.futureList) {
				try {
					logger.debug("Response Code " + futureList1.get());
					logger.debug("Inside Loop Size " + Main.futureList.size());
					//Main.futureList.remove(futureList1);
					j++;
				} catch (ExecutionException e) {
					logger.error(
							"Exception occured while processing the responses "
									+ i, e);
					e.printStackTrace();
				}
			}
			logger.debug("Toatal Response Processed  "+j);
		}
		else{
		logger.debug("Bingo **********************************");
		}*/
		executor.shutDownExecutor();
	}
}
