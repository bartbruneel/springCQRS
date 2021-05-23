package com.springbank.bankacc.query.api.handlers;

import com.springbank.bankacc.events.AccountClosedEvent;
import com.springbank.bankacc.events.AccountOpenedEvent;
import com.springbank.bankacc.events.FundsDepositedEvent;
import com.springbank.bankacc.events.FundsWithdrawnEvent;

public interface AccountEventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);

}
