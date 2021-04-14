package com.axon.axoneurakaclient.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axon.axoneurakaclient.command.IssueCardCommand;

@RestController

public class GiftController {
	private final CommandGateway commandGateway;
	private final QueryGateway queryGateway;
	@Autowired
	public GiftController(CommandGateway commandGateway, QueryGateway queryGateway) {
		this.commandGateway = commandGateway;
		this.queryGateway = queryGateway;
	}
	
	@PostMapping("/api/issuecard")
	public String issueCard() {
		commandGateway.send(new IssueCardCommand(1,1,1));
		return "Saved";
	}

}
