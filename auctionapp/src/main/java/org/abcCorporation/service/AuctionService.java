package org.abcCorporation.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.abcCorporation.enums.BidResult;
import org.abcCorporation.model.AuctionItem;
import org.abcCorporation.model.BidItem;
import org.abcCorporation.model.Item;

public interface AuctionService {
	public Map<String,BigInteger> saveAuctionItem(AuctionItem auctionItem);
	
	public Item saveItem(Item item);
	
	public BidItem saveBidItem(BidItem bidItem);
	
	public List<AuctionItem> getAllAuctionItems();
	
	public AuctionItem getAuctionItem(BigInteger auctionItemId);
	
	public BidResult validateBidForAuction(BidItem bidItem, AuctionItem auctionItem);
}
