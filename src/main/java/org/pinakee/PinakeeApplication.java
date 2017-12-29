package org.pinakee;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.pinakee.transform.XqueryTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class PinakeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PinakeeApplication.class, args);
	}
}

@Configuration
class BootRunner implements CommandLineRunner{

	@Autowired
	XqueryTransformer xqueryTransformer;
	
	@Autowired
	ApplicationContext appContext;
	
	@Override
	public void run(String... arg0) throws Exception {
		Resource resource=appContext.getResource("classpath:customer.xml");
		File file= resource.getFile();
		String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
		System.out.println(xqueryTransformer.transform(content));
	}
	
}