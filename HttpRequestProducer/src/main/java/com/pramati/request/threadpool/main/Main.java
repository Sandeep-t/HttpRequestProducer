/**
 * 
 */
package com.pramati.request.threadpool.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
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
public class Main {

	private static final Logger logger = Logger.getLogger(Main.class);
	// public static Map<Integer,Long> threadTimeMap=
	// Collections.synchronizedMap(new HashMap<Integer,Long>());
	public static ClientPool pool = new ClientPool(
			"http://localhost:8080/HttpRequestConsumer/DataConsumer", "username",
			"testingClient", new AsyncHttpClient());
	public static List<Future<Response>> futureList = Collections
			.synchronizedList(new ArrayList<Future<Response>>());

	public static void main(String args[]) throws InterruptedException {
		int i = 1;
		ThreadExecutor executor = new ThreadExecutor();
		long startTime = System.currentTimeMillis();
		while ((System.currentTimeMillis() - startTime) < 1 * 60 * 1000) {
			HttpAsyncClientImpl httpImpl = new HttpAsyncClientImpl(i);
			try {

				executor.executeTask(httpImpl);

			} catch (Exception e) {
				logger.error("Exception occured I " + i, e);
			}
			i++;
		}

		executor.shutDownExecutor();
		if (Main.futureList.size() > 0) {
			for (Future<Response> futureList : Main.futureList) {
				try {
					logger.debug("Response Code " + futureList.get());
				} catch (ExecutionException e) {
					logger.error(
							"Exception occured while processing the responses "
									+ i, e);
					e.printStackTrace();
				}

			}
		}

	}
}
