package com.pramati.pool.client.clientImpl;

import java.net.URL;
import java.util.concurrent.Callable;



public class Request implements Callable<Response> {
    private URL url;

    public Request(URL url) {
    	try{
    		this.url = url;	
    	}
        catch (Exception e) {
        	e.printStackTrace();
		}
    }

    @Override
    public Response call() throws Exception {
        return new Response(url.openStream());
    }
} 