package com.axon.eurakaclient2.command;

import org.axonframework.commandhandling.RoutingKey;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


import lombok.Data;
@Data

public class UpdateCustomerCommand {
	@TargetAggregateIdentifier
	@RoutingKey
	private final String customerId;
	private String status;
	public UpdateCustomerCommand(String customerId, String status) {
		this.customerId = customerId;
		this.status = status;
	}
	
	
}
