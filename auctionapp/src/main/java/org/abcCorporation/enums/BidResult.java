package org.abcCorporation.enums;

public enum BidResult {
	
	RESERVE_PRICE_NOT_MET("Your bid for the auction item is not accepted as the Reserve price is not met"),
	BID_ACCEPTED("Your bid for the auction item is accepted"),
	OUTBID_ACCEPTED("Your bid for the auction item has outbid the previous bids"),
	OUTBID_NOT_ACCEPTED("Your bid for the auction item has failed to outbid the previous bids"),
	BIDDER_IS_OUTBID("Your bid for the auction item has been outbid by another bid");
	
	private String notificationMessage;
	
	BidResult(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}
	
	public String getNotificationMessage() {
		return notificationMessage;
	}
}
