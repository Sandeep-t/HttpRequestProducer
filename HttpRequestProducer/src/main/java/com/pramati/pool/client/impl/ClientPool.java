/**
 * 
 */
package com.pramati.pool.client.impl;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.pramati.pool.client.AbstractObjectPool;

/**
 * @author sandeep-t
 * @param <T>
 *
 */
public class ClientPool extends AbstractObjectPool<BoundRequestBuilder> {
	
	final private String url, dataKey,dataValue;
	final private AsyncHttpClient asyncHttpClient;
	
	
	public ClientPool(String url,String dataKey,String dataValue,AsyncHttpClient asyncHttpClient) {
	    super();
	    
	    this.url = url;
	    this.dataKey = dataKey;
	    this.dataValue=dataValue;
	    this.asyncHttpClient=asyncHttpClient;
	  }
	
	

	/*public ClientPool(String url, AsyncHttpClient asyncHttpClient) {
		super();
		this.url = url;
		this.asyncHttpClient = asyncHttpClient;
	}
*/


	@Override
	  protected BoundRequestBuilder create() {

		return asyncHttpClient.prepareGet(url).addQueryParameter(dataKey, dataValue);
	    
	  } 

	  @Override
	  public void expire(BoundRequestBuilder request) {}

	  @Override
	  public boolean validate(BoundRequestBuilder o) {
		  return true;
	  }
	}