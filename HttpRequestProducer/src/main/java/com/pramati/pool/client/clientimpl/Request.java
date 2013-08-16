package com.pramati.pool.client.clientimpl;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;



public class Request implements Callable<Response> {
	
    final private URL url;
   
    public Request(URL url)  {
    		this.url = url;	
    }

    @Override
    public Response call() throws IOException {
        return new Response(url.openStream());
    }
} 