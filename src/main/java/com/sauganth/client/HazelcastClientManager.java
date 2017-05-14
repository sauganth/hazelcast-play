package com.sauganth.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastClientManager {
	
	ExecutorService executor = new ThreadPoolExecutor(1,1,1000,TimeUnit.MILLISECONDS,new SynchronousQueue<Runnable>(),new NamedThreadFactory("hazelcast-connect"));

	HazelcastInstance client = null;
	public HazelcastClientManager() {
		
	}
	
	public HazelcastInstance getClient() {
		if (client == null) throw new CacheUninitializedException();
		return client;
	}
	
	public void connect() {
		try {
			executor.execute(()->client=HazelcastClient.newHazelcastClient());
		} catch (RejectedExecutionException re) {
			re.printStackTrace();
		}
	}
}
