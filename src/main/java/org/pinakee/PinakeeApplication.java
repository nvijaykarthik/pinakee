package org.pinakee;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.SpringServletContainerInitializer;

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

	public static void main(String[] args) {
		SpringApplication.run(PinakeeApplication.class, args);
	}
	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("org.pinakee.resource"))
                .build()
                .apiInfo(metaData());
             
    }
	private ApiInfo metaData() {
		Collection<VendorExtension> coll=new ArrayList<VendorExtension>();
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
 }