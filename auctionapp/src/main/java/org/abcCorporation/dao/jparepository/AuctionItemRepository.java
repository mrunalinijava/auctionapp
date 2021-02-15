package org.abcCorporation.dao.jparepository;

import java.math.BigInteger;

import org.abcCorporation.model.AuctionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionItemRepository extends JpaRepository<AuctionItem, BigInteger>{
	
}
