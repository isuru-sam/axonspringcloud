package com.axon.axoneurakaclient.event;

import lombok.Data;

@Data
public class CardIssuedEvent {
	
	private String cardId;
	public CardIssuedEvent(String cid) {
		this.cardId=cid;
	}
	

}
