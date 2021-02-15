package org.abcCorporation.dao.jparepository;

import java.math.BigInteger;

import org.abcCorporation.model.BidItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BidItemRepository extends JpaRepository<BidItem, BigInteger> {
	
	@Query("UPDATE BidItem b SET b.notificationMessage=:notificationMessage where b.auctionItemId=:auctionItemId AND b.bidderName=:bidderName")
	public void updateBidBasedOnAuctionItemIdAndBidderName(@Param("notificationMessage") String notificationMessage, 
			   											   @Param("auctionItemId") BigInteger auctionItemId,
			   											   @Param("bidderName") String bidderName);
}
