package org.abcCorporation.dao;

import java.math.BigInteger;
import java.util.List;

import org.abcCorporation.dao.jparepository.AuctionItemRepository;
import org.abcCorporation.dao.jparepository.BidItemRepository;
import org.abcCorporation.dao.jparepository.ItemRepository;
import org.abcCorporation.enums.BidResult;
import org.abcCorporation.model.AuctionItem;
import org.abcCorporation.model.BidItem;
import org.abcCorporation.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuctionServiceDAO {
	
	@Autowired
	AuctionItemRepository auctionItemRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	BidItemRepository bidItemRepository;
	
	public BigInteger saveAuctionItem(AuctionItem auctionItem) {
		AuctionItem savedAuctionItem = auctionItemRepository.save(auctionItem);
		return savedAuctionItem.getAuctionItemId();
	}
	
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}
	
	public BidItem saveBidItem(BidItem bidItem) {
		return bidItemRepository.save(bidItem);
	}
	
	public List<AuctionItem> getAllAuctionItems(){
		return auctionItemRepository.findAll();
	}
	
	public AuctionItem getAuctionItem(BigInteger autionItemId) {
		return auctionItemRepository.findOne(autionItemId);
	}
	
	public void updateBidBasedOnAuctionItemIdAndBidderName(BidResult bidResult, AuctionItem auctionItem) {
		bidItemRepository.updateBidBasedOnAuctionItemIdAndBidderName(bidResult.getNotificationMessage(), auctionItem.getAuctionItemId(), auctionItem.getBidderName());
	}
}
