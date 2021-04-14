package com.axon.axoneurakaclient.command;



import org.axonframework.commandhandling.RoutingKey;
import org.axonframework.modelling.command.TargetAggregateIdentifier;



import lombok.Data;

@Data
public class IssueCardCommand {
	@TargetAggregateIdentifier
	@RoutingKey
	private final Integer serviceId;
	private final Integer numberOfCards;
	private final Integer amount;
}
