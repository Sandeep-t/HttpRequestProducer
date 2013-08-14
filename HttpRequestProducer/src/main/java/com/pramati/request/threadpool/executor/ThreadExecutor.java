/**
 * 
 */
package com.pramati.request.threadpool.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class ThreadExecutor {

	 private static final Logger logger =
	 Logger.getLogger(ThreadExecutor.class);

	public ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10000);

	ThreadPoolExecutor threadPool;

	public ThreadExecutor() {
		
		threadPool=new ThreadPoolExecutor(8, 12, 10000, TimeUnit.SECONDS,queue,new ThreadPoolExecutor.CallerRunsPolicy());

	}

	public void executeTask(Runnable task) {
		System.out.println("Q Size " + queue.size());
		threadPool.execute(task);
	}

	public void shutDownExecutor() {
		 logger.debug("Shutting Down the executor with Queue Size.." +
		 queue.size());
		threadPool.shutdown();
	}

}
