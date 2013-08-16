/**
 * 
 */
package com.pramati.pool.client;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sandeep-t
 *
 */
public abstract class AbstractObjectPool<T> {
	  private long expirationTime;

	  final private Map<T, Long> locked, unlocked;

	  public AbstractObjectPool() {
	    expirationTime = 30000; // 30 seconds
	    locked = new HashMap<T, Long>();
	    unlocked = new HashMap<T, Long>();
	  }

	  protected abstract T create();

	  public abstract boolean validate(T o);

	  public abstract void expire(T o);

	  public synchronized T checkOut() {
	    long now = System.currentTimeMillis();
	    T t;
	    if (unlocked.size() > 0) {
	    //System.out.println("Total Number of created objects "+ unlocked.size());	
	      Enumeration<T> e = (Enumeration<T>) unlocked.keySet();
	      while (e.hasMoreElements()) {
	        t = e.nextElement();
	        if ((now - unlocked.get(t)) > expirationTime) {
	          // object has expired
	          unlocked.remove(t);
	          expire(t);
	          t = null;
	        } else {
	          if (validate(t)) {
	            unlocked.remove(t);
	            locked.put(t, now);
	            return t;
	          } else {
	            // object failed validation
	            unlocked.remove(t);
	            expire(t);
	            t = null;
	          }
	        }
	      }
	    }
	    // no objects available, create a new one
	    t = create();
	    locked.put(t, now);
	    return t;
	  }

	  public synchronized void checkIn(T t) {
	    locked.remove(t);
	    unlocked.put(t, System.currentTimeMillis());
	  }
	}