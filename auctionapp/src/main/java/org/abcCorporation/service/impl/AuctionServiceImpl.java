package org.abcCorporation.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.abcCorporation.dao.AuctionServiceDAO;
import org.abcCorporation.enums.BidResult;
import org.abcCorporation.model.AuctionItem;
import org.abcCorporation.model.BidItem;
import org.abcCorporation.model.Item;
import org.abcCorporation.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionServiceImpl implements AuctionService{
	
	@Autowired
	AuctionServiceDAO auctionServiceDAO;
	
	@Override
	public Map<String,BigInteger> saveAuctionItem(AuctionItem auctionItem) {
		BigInteger auctionItemId = auctionServiceDAO.saveAuctionItem(auctionItem);
		Map<String,BigInteger> map = new HashMap<>();
		map.put("auctionItemId", auctionItemId);
		return map;
	}

	@Override
	public Item saveItem(Item item) {
		return auctionServiceDAO.saveItem(item);
	}

	@Override
	public BidItem saveBidItem(BidItem bidItem) {
		return auctionServiceDAO.saveBidItem(bidItem);
	}

	@Override
	public List<AuctionItem> getAllAuctionItems() {
		return auctionServiceDAO.getAllAuctionItems();
	}

	@Override
	public AuctionItem getAuctionItem(BigInteger auctionItemId) {
		return auctionServiceDAO.getAuctionItem(auctionItemId);
	}
	
	public BidResult validateBidForAuction(BidItem bidItem, AuctionItem auctionItem){
		
		BigDecimal maxAutoBidAmt = bidItem.getMaxAutoBidAmount();
		BigDecimal reservePrice = auctionItem.getReservePrice();
		BigDecimal currentBid = auctionItem.getCurrentBid();
		boolean isFirstOutBidder = currentBid.compareTo(BigDecimal.ZERO) == 0;
		
		int reservepriceDiff = maxAutoBidAmt.compareTo(reservePrice);
		int currentBidDiff = maxAutoBidAmt.compareTo(currentBid);
		
		if(reservepriceDiff < 0) {
			if(currentBidDiff > 0) {
				auctionItem.setCurrentBid(maxAutoBidAmt);
				auctionItem.setBidderName(bidItem.getBidderName());
				auctionServiceDAO.saveAuctionItem(auctionItem);
			}
			return BidResult.RESERVE_PRICE_NOT_MET; // First Rule "If the reserve price is not met" point
		}else{
			if(isFirstOutBidder) {
				auctionItem.setCurrentBid(maxAutoBidAmt);
				auctionItem.setBidderName(bidItem.getBidderName());
				auctionServiceDAO.saveAuctionItem(auctionItem);
				return BidResult.BID_ACCEPTED; // First Bid accepted when the currentBid was zero
			}else {
				if(currentBidDiff >= 1) {  // bidder with highest max auto-bid amount shud pay 1 dollar more than next highest bidder
					auctionServiceDAO.updateBidBasedOnAuctionItemIdAndBidderName(BidResult.BIDDER_IS_OUTBID, auctionItem);// Broadcast Notification
					auctionItem.setCurrentBid(maxAutoBidAmt);
					auctionItem.setBidderName(bidItem.getBidderName());
					auctionServiceDAO.saveAuctionItem(auctionItem);
					return BidResult.OUTBID_ACCEPTED;
				}else {
					return BidResult.OUTBID_NOT_ACCEPTED; // Failure case for Outbid not accepted
				}
			}
		}
		
	}
	
}
