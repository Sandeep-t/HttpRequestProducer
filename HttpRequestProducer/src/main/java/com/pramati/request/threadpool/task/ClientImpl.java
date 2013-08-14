/**
 * 
 */
package com.pramati.request.threadpool.task;

import java.net.MalformedURLException;
import java.net.URL;

import com.pramati.pool.client.clientImpl.Request;

/**
 * @author sandeep-t
 *
 */
public class ClientImpl implements Runnable{

	@Override
	public void run() {
		try {
			new Request(new URL("http://localhost:8080/HttpRequestConsumer/DataConsumer"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
