package com.springbank.bankacc.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountClosedEvent {

    private String id;
    
}
