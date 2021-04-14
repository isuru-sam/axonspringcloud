package com.axon.eurakaclient2.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.axon.eurakaclient2.command.UpdateCustomerCommand;
import com.axon.eurakaclient2.event.CustomerUpdatedEvent;

@Aggregate
public class Customer {
	@AggregateIdentifier
	private String customerId;
	private String status;
	 @CommandHandler
		public Customer(UpdateCustomerCommand cmd) {
		
			AggregateLifecycle.apply(new CustomerUpdatedEvent(cmd.getCustomerId(),cmd.getStatus()));
		}
	  

		@EventSourcingHandler
		private void handleUpdatedEvent(CustomerUpdatedEvent event) {
			customerId = event.getCustomerId();
			System.out.println("even t fired");
			
		}
}
