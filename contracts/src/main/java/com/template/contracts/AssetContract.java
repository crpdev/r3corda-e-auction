package com.template.contracts;

import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.transactions.LedgerTransaction;
import org.jetbrains.annotations.NotNull;

/**
 * Created by rajapandian
 * Date: 10/05/20
 * Project: r3corda-e-auction
 * Package: com.template.contracts
 **/
public class AssetContract implements Contract {
    @Override
    public void verify(@NotNull LedgerTransaction tx) throws IllegalArgumentException {

    }

    public interface Commands extends CommandData {
        class CreateAsset implements Commands {}
        class TransferAsset implements Commands {}
    }
}
