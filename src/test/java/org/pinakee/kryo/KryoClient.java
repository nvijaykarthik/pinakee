package org.pinakee.kryo;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.pinakee.domain.TranformerInput;
import org.pinakee.domain.Transformed;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;



public class KryoClient {

	@Test
	public void test() throws IOException {
		Client client = new Client(); 
		client.start(); 
		client.connect(5000, "127.0.0.1", 54555, 54777); 
		       
		Kryo kryoClient = client.getKryo(); 
		kryoClient.register(TranformerInput.class); 
		kryoClient.register(Transformed.class); 

		TranformerInput request = new TranformerInput();
		request.setXqueryName("customer ");
		request.setXml("<customers><customer id='1'><name>Foo Industries</name><industry>Chemical</industry><city>Glowing City</city></customer><customer id='2'><name>Bar Refreshments</name><industry>Beverage</industry><city>Desert Town</city></customer><customer id='3'><name>Hello World Services</name><industry>Travel</industry><city>Coral Sands</city></customer></customers>");
		client.sendTCP(request); 

		client.addListener(new Listener() { 
		   public void received (Connection connection, Object object) { 
		              if (object instanceof Transformed) { 
		            	  Transformed response = (Transformed)object; 
		                  System.out.println(response.getContent()); 
		              } 
		           } 
		   });
	}

}
