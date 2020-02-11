package com.teads.core;

import com.teads.exception.BiddingException;
import com.teads.model.Auction;
import com.teads.model.Bidder;
import com.teads.model.Winner;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This strategy consists on :
 * The buyer winning the auction is the one with the highest bid above or equal to the reserve price.
 * The winning price is the highest bid price from a non-winning buyer above the reserve price (or the reserve price if none applies).
 */

public class SecondPriceSealedBidStrategy implements BiddingStrategy {

    public Winner apply(final Auction auction) {

        if (auction.getBidders().size() == 0) {
            throw new BiddingException("Auction can't be performed as no bidder participate to it");
        }

        //bidders retained to participate to the auction (price >= reserve price)
        Set<Bidder> effectiveBids = auction.getBidders().stream()
                .filter(bidder -> bidder.getPrices().stream().anyMatch(price -> price >= auction.getReservePrice())).collect(Collectors.toSet());

        //The winning price is derived from a non winning participant so auction can't be performed without 2 retained bidders
        if (effectiveBids.size() < 2) {
            throw new BiddingException("Auction can't be performed as there's only one effective bidder");
        }

        //Fetch the winner based on its price
        Bidder winner = effectiveBids.stream().max(Comparator.comparingInt(this::getMaximumPrice))
                .orElseThrow(NoSuchElementException::new);

        //Fetching the second highest price from the eligible bidders (the winner has been removed from this list )
        Integer winningPrice = effectiveBids.stream().filter(bidder -> !bidder.equals(winner)).map(Bidder::getPrices).flatMap(Collection::stream).max(Integer::compareTo).orElseThrow(NoSuchElementException::new);

        return new Winner(winner.getName(),winningPrice);
    }

    /**
     * get the maximum bid price for a bidder
     *
     * @param bidder
     * @return
     */
    private int getMaximumPrice(final Bidder bidder) {
        return bidder.getPrices().stream().mapToInt(value -> value).max().orElseThrow(NoSuchElementException::new);
    }
}
