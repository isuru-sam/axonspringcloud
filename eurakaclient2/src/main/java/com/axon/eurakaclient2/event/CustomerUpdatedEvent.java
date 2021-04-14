package com.axon.eurakaclient2.event;

import lombok.Data;

@Data
public class CustomerUpdatedEvent {
	
	private String customerId;
	private String status;
	public CustomerUpdatedEvent(String cid,String status) {
		this.customerId=cid;
		this.status=status;
	}
	

}
