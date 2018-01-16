package org.pinakee.kryo;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.pinakee.domain.TranformerInput;
import org.pinakee.domain.Transformed;
import org.pinakee.exception.XqueryNotFoundException;
import org.pinakee.service.TransformerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import net.sf.saxon.s9api.SaxonApiException;
/**
 * Still Development and testing is in progress.
 * @author Vijaykarthik N
 *
 */
@Component
public class KryoServer {

	@Autowired
	Server server;
	
	@Value("${pinakee.kryop.start.server.auto}")
	boolean startKryo;
	
	@Autowired
	TransformerService transformerService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PostConstruct
	public void startKryoServer() {
		
			try {
			if(startKryo)
				handleKryo();
			} catch (IOException e) {
				log.error("Error Starting kryo server {}",e);
			}
	}
	
	public void handleKryo() throws IOException {
		log.info("Starting Kryo Server Listening on tcp:54555, UDP:54777");
		server.start(); 
		server.bind(54555, 54777);

		Kryo kryo = server.getKryo(); 
		kryo.register(TranformerInput.class); 
		kryo.register(Transformed.class); 

		server.addListener(new Listener() { 
		        public void received (Connection connection, Object object) { 
		              if (object instanceof TranformerInput) { 
		            	  TranformerInput request = (TranformerInput)object; 
		            	  log.debug("Input Message : {} ",request); 
		            	  Transformed response;
						try {
							response = transformerService.retrieveXqueryFromDB(request.getXqueryName(),request.getXml(),null);
						} catch (Exception e) {
							response=new Transformed();
							response.setStatus(-1);
							response.setContent(ExceptionUtils.getStackTrace(e));
						}
		            	  log.trace("Output Message : {} ",response); 
		            	  server.sendToTCP(connection.getID(), response);
		           } 
		        }
		});
	}
	
}
