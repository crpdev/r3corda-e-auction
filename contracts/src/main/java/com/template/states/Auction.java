package com.template.states;

import com.template.contracts.AuctionContract;
import net.corda.core.contracts.*;
import net.corda.core.flows.FlowLogicRef;
import net.corda.core.flows.FlowLogicRefFactory;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

/**
 * Created by rajapandian
 * Date: 10/05/20
 * Project: r3corda-e-auction
 * Package: com.template.states
 **/

@BelongsToContract(AuctionContract.class)
public class Auction implements SchedulableState {

    private final LinearPointer<Asset> auctionItem;
    private final UUID auctionId;
    private final Amount<Currency> basePrice;
    private final Amount<Currency> highestBid;
    private final Party higestBidder;
    private final Amount<Currency> winningBid;
    private final Boolean active;
    private final Instant bidEndTime;
    private final Party auctioneer;
    private final List<Party> bidders;
    private final Party winner;

    public Auction(LinearPointer<Asset> auctionItem, UUID auctionId, Amount<Currency> basePrice, Amount<Currency> highestBid, Party higestBidder, Amount<Currency> winningBid, Boolean active, Instant bidEndTime, Party auctioneer, List<Party> bidders, Party winner) {
        this.auctionItem = auctionItem;
        this.auctionId = auctionId;
        this.basePrice = basePrice;
        this.highestBid = highestBid;
        this.higestBidder = higestBidder;
        this.winningBid = winningBid;
        this.active = active;
        this.bidEndTime = bidEndTime;
        this.auctioneer = auctioneer;
        this.bidders = bidders;
        this.winner = winner;
    }

    public LinearPointer<Asset> getAuctionItem() {
        return auctionItem;
    }

    public UUID getAuctionId() {
        return auctionId;
    }

    public Amount<Currency> getBasePrice() {
        return basePrice;
    }

    public Amount<Currency> getHighestBid() {
        return highestBid;
    }

    public Party getHigestBidder() {
        return higestBidder;
    }

    public Amount<Currency> getWinningBid() {
        return winningBid;
    }

    public Boolean getActive() {
        return active;
    }

    public Instant getBidEndTime() {
        return bidEndTime;
    }

    public Party getAuctioneer() {
        return auctioneer;
    }

    public List<Party> getBidders() {
        return bidders;
    }

    public Party getWinner() {
        return winner;
    }

    @Nullable
    @Override
    public ScheduledActivity nextScheduledActivity(@NotNull StateRef thisStateRef, @NotNull FlowLogicRefFactory flowLogicRefFactory) {
        FlowLogicRef flowLogicRef = flowLogicRefFactory.create("net.corda.samples.flows.EndAuctionFlow$Initiator", auctionId);
        return new ScheduledActivity(flowLogicRef, this.bidEndTime);
    }

    @NotNull
    @Override
    public List<AbstractParty> getParticipants() {
        List<AbstractParty> allParties = new ArrayList<>(bidders);
        return allParties;
    }
}
