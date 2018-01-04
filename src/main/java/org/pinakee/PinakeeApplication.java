package org.pinakee;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.SpringServletContainerInitializer;

import com.esotericsoftware.kryonet.Server;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PinakeeApplication extends SpringServletContainerInitializer{

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(PinakeeApplication.class, args);
	}
	
	@Bean
    public Docket productApi() {
		log.debug("Creating swagger docket");
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("org.pinakee.resource"))
                .build()
                .apiInfo(metaData());
             
    }
	private ApiInfo metaData() {
		Collection<VendorExtension> coll=new ArrayList<VendorExtension>();
		log.debug("Swagger API info");
        ApiInfo apiInfo = new ApiInfo(
                "Rest API For Pinakee",
                "REST API For XML transformation using xquery", 
                "1.0",
                "Terms of service",
                new Contact("Vijaykarthik N", "https://github.com/settings/profile", "nvijaykarthik@gmail.com"),
               "MIT",
                "https://opensource.org/licenses/MIT",coll);
        return apiInfo;
    }
	
	@Bean
	public Server server() {
		return new Server();
	}
 }