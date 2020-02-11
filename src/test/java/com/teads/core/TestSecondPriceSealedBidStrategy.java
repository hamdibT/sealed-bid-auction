package com.teads.core;

import com.google.common.collect.Lists;
import com.teads.exception.BiddingException;
import com.teads.model.Auction;
import com.teads.model.Bidder;
import com.teads.model.Winner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestSecondPriceSealedBidStrategy {

	private Auction auction;
	private static BiddingStrategy STRATEGY;
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@BeforeClass
	public static void setup() {
		STRATEGY = new SecondPriceSealedBidStrategy();
	}

	@Before
	public void init() {
		auction = new Auction(100);
	}

	@Test
	public void testAuction_With_No_Bidders() {
		exceptionRule.expect(BiddingException.class);
		exceptionRule.expectMessage("Auction can't be performed as no bidder participate to it");
		STRATEGY.apply(auction);
	}

	@Test
	public void testAuction_With_One_Effective_Bidder() {
		exceptionRule.expect(BiddingException.class);
		exceptionRule.expectMessage("Auction can't be performed as there's only one effective bidder");
		Bidder bidder1 = new Bidder("A", Lists.newArrayList(100, 500));
		Bidder bidder2 = new Bidder("B", Lists.newArrayList(90));
		auction.addBidder(bidder1);
		auction.addBidder(bidder2);
		Assert.assertEquals("Expecting bidding number in this auction", 2, auction.getBidders().size());

		STRATEGY.apply(auction);
	}

	@Test
	public void testAuction_With_Two_Effective_Bidder() {
		Bidder bidder1 = new Bidder("A", Lists.newArrayList(100, 500));
		Bidder bidder2 = new Bidder("B", Lists.newArrayList(400));

		auction.addBidder(bidder1);
		auction.addBidder(bidder2);

		Assert.assertEquals("Expecting bidding number in this auction", 2, auction.getBidders().size());
		Winner winner = STRATEGY.apply(auction);
		Assert.assertEquals("the winner is ", "A", winner.getName());
		Assert.assertEquals("the winning price is  ", Integer.valueOf(400), winner.getPrice());
	}

	@Test
	public void testAuction_With_More_Than_Two_Effective_Bidder() {
		Bidder bidder1 = new Bidder("A", Lists.newArrayList(100, 500));
		Bidder bidder2 = new Bidder("B", Lists.newArrayList(400));
		Bidder bidder3 = new Bidder("C", Lists.newArrayList(200));

		auction.addBidder(bidder1);
		auction.addBidder(bidder2);
		auction.addBidder(bidder3);

		Assert.assertEquals("Expecting bidding number in this auction", 3, auction.getBidders().size());
		Winner winner = STRATEGY.apply(auction);
		Assert.assertEquals("the winner is ", "A", winner.getName());
		Assert.assertEquals("the winning price is  ", Integer.valueOf(400), winner.getPrice());
	}
}
