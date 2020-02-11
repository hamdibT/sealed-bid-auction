package com.teads;

import com.google.common.collect.Lists;
import com.teads.core.BiddingStrategy;
import com.teads.core.SecondPriceSealedBidStrategy;
import com.teads.model.Auction;
import com.teads.model.Bidder;
import com.teads.model.Winner;

public class Runner {

    public static void main(String[] args) {
        // Teads development test example

        BiddingStrategy strategy = new SecondPriceSealedBidStrategy();
        Auction auction = new Auction(100);

        Bidder bidder1 = new Bidder("A", Lists.newArrayList(110, 130));
        Bidder bidder2 = new Bidder("B", Lists.newArrayList());
        Bidder bidder3 = new Bidder("C", Lists.newArrayList(125));
        Bidder bidder4 = new Bidder("D", Lists.newArrayList(105, 115, 90));
        Bidder bidder5 = new Bidder("E", Lists.newArrayList(132, 135, 140));

        auction.addBidder(bidder1);
        auction.addBidder(bidder2);
        auction.addBidder(bidder3);
        auction.addBidder(bidder4);
        auction.addBidder(bidder5);

        Winner winner = strategy.apply(auction);
        System.out.println("the winner is " + winner.getName() + " and the winning price is " + winner.getPrice());
    }
}
