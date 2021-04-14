package com.axon.axoneurakaclient.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import com.axon.axoneurakaclient.command.IssueCardCommand;
import com.axon.axoneurakaclient.event.CardIssuedEvent;
import com.axon.eurakaclient2.command.UpdateCustomerCommand;

@Aggregate
public class GiftCard {
	  @AggregateIdentifier
	  private String cardId;
	  @Autowired
	    private transient CommandGateway commandGateway;

	  
	  @CommandHandler
		public GiftCard(IssueCardCommand cmd) {
		
			AggregateLifecycle.apply(new CardIssuedEvent(String.valueOf(cmd.getServiceId())));
		}
	  

		@EventSourcingHandler
		private void handleCreatedEvent(CardIssuedEvent event) {
			cardId = event.getCardId();
			 //send the commands
	        commandGateway.send(commandGateway.send(new UpdateCustomerCommand("1","updated")));
		}
}
