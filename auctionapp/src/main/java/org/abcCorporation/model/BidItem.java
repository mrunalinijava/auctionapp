package org.abcCorporation.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "BID_ITEM")
public class BidItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BID_ITEM_ID")
	private BigInteger bidItemId;
	
	@Column(name="AUCTION_ITEM_ID")
	private BigInteger auctionItemId;
	
	@Column(name="MAX_AUTOBID_AMOUNT")
	private BigDecimal maxAutoBidAmount;
	
	@Column(name="BIDDER_NAME")
	private String bidderName;
	
	@Column(name="NOTIFICATION_MESSAGE")
	private String notificationMessage;
	
	public BidItem() {
		
	}
	
	public BigInteger getAuctionItemId() {
		return auctionItemId;
	}
	public void setAuctionItemId(BigInteger auctionItemId) {
		this.auctionItemId = auctionItemId;
	}
	public BigDecimal getMaxAutoBidAmount() {
		return maxAutoBidAmount;
	}
	public void setMaxAutoBidAmount(BigDecimal maxAutoBidAmount) {
		this.maxAutoBidAmount = maxAutoBidAmount;
	}
	public String getBidderName() {
		return bidderName;
	}
	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}
	public String getNotificationMessage() {
		return notificationMessage;
	}
	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}
	
}
