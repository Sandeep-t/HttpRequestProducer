/**
 * 
 */
package com.pramati.request.threadpool.task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.ning.http.client.Response;

/**
 * @author sandeep-t
 *
 */
public class ResponseReader implements Runnable{

	Future<Response> response=null;
	
	
	public ResponseReader(Future<Response> response) {
		super();
		this.response = response;
	}


	@Override
	public void run() {
		
		try {
			this.response.get();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
