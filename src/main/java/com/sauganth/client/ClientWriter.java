package com.sauganth.client;

import java.util.Map;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;

public class ClientWriter {

	public static void main(String[] args) {
        HazelcastInstance client = HazelcastClient.newHazelcastClient();
        Map<Integer, String> map = client.getMap("customers");
        map.put(2,"sauganth");

	}

}
