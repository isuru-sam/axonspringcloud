package com.axon.eurakaclient2.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axon.eurakaclient2.command.UpdateCustomerCommand;


@RestController
public class CustomerController {
	private final CommandGateway commandGateway;
	private final QueryGateway queryGateway;
	@Autowired
	public CustomerController(CommandGateway commandGateway, QueryGateway queryGateway) {
		this.commandGateway = commandGateway;
		this.queryGateway = queryGateway;
	}
	
	@PostMapping("/api/issuecard")
	public String updateCustomer() {
		commandGateway.send(new UpdateCustomerCommand("1","updated"));
		System.out.println("updated");
		return "Saved";
	}
}
