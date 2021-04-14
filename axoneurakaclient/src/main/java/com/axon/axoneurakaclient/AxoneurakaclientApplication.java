package com.axon.axoneurakaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class AxoneurakaclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AxoneurakaclientApplication.class, args);
	}

}
