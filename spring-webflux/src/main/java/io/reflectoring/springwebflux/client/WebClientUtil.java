package io.reflectoring.springwebflux.client;

import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;


@Component
public class WebClientUtil {

	private final WebClient client;


    private static final Logger logger = LoggerFactory.getLogger(WebClientUtil.class);

	// Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults
	// and customizations.
	// We can use it to create a dedicated `WebClient` for our component.
	public WebClientUtil(WebClient.Builder builder) {
		this.client = builder.baseUrl("http://localhost:9000")
				.filter(loggingFilter)
				.filter(responseLoggingFilter())
				.build();
	}

	public Mono<String> getMessage() {
		return this.client.get().uri("/api/users").accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToMono(Greeting.class).map(Greeting::getMessage);
	}

	public void get() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Entering method: " + methodName);

		this.client.get().uri("/api/users").accept(MediaType.APPLICATION_JSON)
				 .retrieve()
				 .bodyToMono(String.class)
			     .subscribe(customName -> System.out.println("webclient recevie " + customName))
		         ;
    	System.out.println("Leaving method: " + methodName);

	}

	public void get1() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Entering method: " + methodName);

		this.client.get().uri("/api/users").accept(MediaType.APPLICATION_JSON).exchangeToMono(response -> {
			System.out.println("Response: " + response);
			
			return Mono.just(response);
		});
		System.out.println("Leaving method: " + methodName);

	}
	private static ExchangeFilterFunction loggingFilter = (clientRequest, nextFilter) -> {
		 logger.info("Sending {} request to {}", clientRequest.method(), clientRequest.url());
         clientRequest.headers().forEach((name, values) ->
                 values.forEach(value -> logger.info("{}: {}", name, value))
         );
         return nextFilter.exchange(clientRequest);
	};
	
	private ExchangeFilterFunction responseLoggingFilter() {
	    return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
	        logger.info("Received response with status code: {}", clientResponse.statusCode());
	        clientResponse.headers().asHttpHeaders().forEach((name, values) ->
	                values.forEach(value -> logger.info("{}: {}", name, value))
	        );
	        return Mono.just(clientResponse);
	    });
	}
	
	}
