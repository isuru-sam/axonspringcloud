package com.axon.axoneurakaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class AxoneurakaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(AxoneurakaserverApplication.class, args);
	}

}
