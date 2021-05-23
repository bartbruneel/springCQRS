package com.springbank.bankacc.cmd.api.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.Min;

@Data
@Builder
public class DepositFundsCommand {

    @TargetAggregateIdentifier
    private String id;
    @Min(value = 1, message = "deposited amount should be at least 1 dollar")
    private double amount;

}
