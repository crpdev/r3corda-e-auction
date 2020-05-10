package com.template.states;

import com.template.contracts.AssetContract;
import net.corda.core.contracts.*;
import net.corda.core.identity.AbstractParty;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by rajapandian
 * Date: 10/05/20
 * Project: r3corda-e-auction
 * Package: com.template.states
 **/

@BelongsToContract(AssetContract.class)
public class Asset implements OwnableState, LinearState {

    private final UniqueIdentifier linearId;
    private final String title;
    private final String description;
    private final String imageUrl;
    private final AbstractParty owner;

    public Asset(UniqueIdentifier linearId, String title, String description, String imageUrl, AbstractParty owner) {
        this.linearId = linearId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.owner = owner;
    }

    @NotNull
    @Override
    public UniqueIdentifier getLinearId() {
        return linearId;
    }

    @NotNull
    @Override
    public List<AbstractParty> getParticipants() {
        return null;
    }

    @NotNull
    @Override
    public AbstractParty getOwner() {
        return owner;
    }

    @NotNull
    @Override
    public CommandAndState withNewOwner(@NotNull AbstractParty newOwner) {
        return new CommandAndState(new AssetContract.Commands.TransferAsset(), new Asset(this.linearId, this.getTitle(), this.getDescription(), this.getImageUrl(), newOwner));
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
