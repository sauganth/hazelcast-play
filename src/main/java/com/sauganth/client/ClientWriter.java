package com.sauganth.client;

import java.util.Map;

import com.hazelcast.client.HazelcastClientNotActiveException;
import com.hazelcast.core.HazelcastInstanceNotActiveException;

public class ClientWriter {

	public static void main(String[] args) {
        HazelcastClientManager manager = new HazelcastClientManager();
        manager.connect();
        for (int i=0;i<50;i++) {
        	System.out.println("Loop:"+(i+1));
	        try{
	        	Thread.sleep(5000);
	        	Map<Integer, String> map = manager.getClient().getMap("customers");
	        	map.put(2,"sauganth");
	        	System.out.println("Put successful Loop:"+(i+1));
	        } catch (HazelcastInstanceNotActiveException na) {
	        	System.out.println("HazelcastInstanceNotActive: attempting reconnect Loop:"+(i+1));
	        	manager.connect();
	        } catch (HazelcastClientNotActiveException cna) {
	        	System.out.println("HazelcastClientNotActive: attempting reconnect Loop:"+(i+1));
	        	manager.connect();
	        } catch (Exception e) {
	        	System.out.println("Loop:"+(i+1) + " Exception while put: "+e);
	        }
        }

	}

}
