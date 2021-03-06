package com.springbank.user.cmd.api.commands;

import com.springbank.user.core.models.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UpdateUserCommand {

    @TargetAggregateIdentifier
    private String id;
    @NotNull(message = "no user details were provided")
    @Valid
    private User user;

}
