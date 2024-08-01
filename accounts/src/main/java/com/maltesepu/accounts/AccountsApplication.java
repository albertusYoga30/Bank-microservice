package com.maltesepu.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "Simple Bank Accounts microservice REST API DOCUMENTATION",
				version = "v1.0.0",
				contact = @Contact(
						name = "Yoga Dwi Prasetyo",
						email = "albertusYoga@gmail.com",
						url = "linkedin.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.yoga.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Simple bank microservice rest api documentation",
				url = "www.business.com"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
