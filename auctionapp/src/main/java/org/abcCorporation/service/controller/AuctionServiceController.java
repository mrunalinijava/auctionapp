package org.abcCorporation.service.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.abcCorporation.enums.BidResult;
import org.abcCorporation.model.AuctionItem;
import org.abcCorporation.model.BidItem;
import org.abcCorporation.service.AuctionService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;

@RestController
@RequestMapping("/")
@CrossOrigin
public class AuctionServiceController {
	
	@Autowired
	private AuctionService auctionService;
	
	@RequestMapping(value = "/auctionItems", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String,BigInteger>> saveAuctionItem( @RequestBody AuctionItem auctionItem,
												            HttpServletRequest request) throws Exception {
		if(auctionItem.getReservePrice().compareTo(BigDecimal.ZERO) == 0) {
			throw new Exception("Unable to save AuctionItem as reser");
		}
		Map<String,BigInteger> map = auctionService.saveAuctionItem(auctionItem);
		if(MapUtils.isNotEmpty(map)) {
			return ResponseEntity.ok(map);
		}else{
			throw new Exception("Unable to save AuctionItem");
		}
	}
	
	@RequestMapping(value = "/auctionItems", method = {RequestMethod.GET})
	public ResponseEntity<List<AuctionItem>> getAllAuctionItems(HttpServletRequest req) {
		List<AuctionItem> auctionItemsList = auctionService.getAllAuctionItems();
		return ResponseEntity.ok(auctionItemsList);
	}
	
	@RequestMapping(value = "/auctionItems/{auctionItemId}", method = {RequestMethod.GET})
	public ResponseEntity<AuctionItem> getAuctionItem(@PathVariable("auctionItemId") BigInteger auctionItemId,
															  			   			 HttpServletRequest req) throws Exception {
		AuctionItem auctionItem = auctionService.getAuctionItem(auctionItemId);
		if(auctionItem != null) {
			return ResponseEntity.ok(auctionItem);
		}else {
			throw new Exception("Unable to retrieve AuctionItem");
		}
	}
	
	@RequestMapping(value = "/bids", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String,String>> saveBids( @RequestBody BidItem bidItem,
											  						 HttpServletRequest request) throws Exception {
		Map<String,String> map = new HashMap<>();
		BigInteger auctionItemId = bidItem.getAuctionItemId();
		AuctionItem auctionItem = auctionService.getAuctionItem(auctionItemId);
		if(auctionItem != null) {
			BidItem bidItemSaved = auctionService.saveBidItem(bidItem); // Audit Log of All Bidding
			if(bidItemSaved != null) {
				BidResult bidResult = auctionService.validateBidForAuction(bidItemSaved, auctionItem);
				if(bidResult != BidResult.BID_ACCEPTED && bidResult != BidResult.OUTBID_ACCEPTED) {
					throw new Exception(bidResult.getNotificationMessage());
				}
				map.put("message",bidResult.getNotificationMessage());
				return ResponseEntity.ok(map);
			}else {
				throw new Exception("Unable to save bidItem");
			}
		}else {
			throw new Exception("Bid cannot be processed for an invalid AuctionItem");
		}
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handleMyException(Exception exp){
		Map<String,String>map = new HashMap<>();
		map.put("errorMessage", exp.getMessage());
		return new ResponseEntity<>(map,HttpStatus.EXPECTATION_FAILED);
	}
	
}
