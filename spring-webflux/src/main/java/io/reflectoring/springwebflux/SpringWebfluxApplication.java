package io.reflectoring.springwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import io.reflectoring.springwebflux.client.WebClientUtil;

@EnableMongoAuditing
@EnableReactiveMongoRepositories
@SpringBootApplication
public class SpringWebfluxApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringWebfluxApplication.class, args);
		WebClientUtil webClientUtil = context.getBean(WebClientUtil.class);
		// We  need to block for the content here or the JVM might exit before the message is logged
		//System.out.println(">> message = " + webClientUtil.getMessage().block());
		webClientUtil.get();
		//webClientUtil.get();
		System.out.println("end of main");
		
	}
}
