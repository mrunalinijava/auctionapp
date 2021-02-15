package org.abcCorporation.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AUCTION_ITEM")
public class AuctionItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AUCTION_ITEM_ID")
	private BigInteger auctionItemId;
	
	@Column(name = "CURRENT_BID")
	private BigDecimal currentBid;
	
	@Column(name = "BIDDER_NAME")
	private String bidderName;
	
	@Column(name = "RESERVE_PRICE")
	private BigDecimal reservePrice;
	
	@OneToOne(optional = false, cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "ITEM_ID")
	private Item item;
	
	public AuctionItem() {
		
	}
	
	public BigInteger getAuctionItemId() {
		return auctionItemId;
	}
	public void setAuctionItemId(BigInteger auctionItemId) {
		this.auctionItemId = auctionItemId;
	}
	public BigDecimal getCurrentBid() {
		return currentBid;
	}
	public void setCurrentBid(BigDecimal currentBid) {
		this.currentBid = currentBid;
	}
	public String getBidderName() {
		return bidderName;
	}
	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}
	public BigDecimal getReservePrice() {
		return reservePrice;
	}
	public void setReservePrice(BigDecimal reservePrice) {
		this.reservePrice = reservePrice;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
}
