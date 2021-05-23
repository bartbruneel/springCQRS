package com.springbank.bankacc.query.api.handlers;

import com.springbank.bankacc.events.AccountClosedEvent;
import com.springbank.bankacc.events.AccountOpenedEvent;
import com.springbank.bankacc.events.FundsDepositedEvent;
import com.springbank.bankacc.events.FundsWithdrawnEvent;
import com.springbank.bankacc.models.BankAccount;
import com.springbank.bankacc.query.api.repositories.AccountRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@ProcessingGroup("bankaccount-group")
public class AccountEventHandlerImpl implements AccountEventHandler{

    private final AccountRepository accountRepository;

    @Autowired
    public AccountEventHandlerImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventHandler
    @Override
    public void on(AccountOpenedEvent event) {
        BankAccount account = BankAccount.builder()
                .id(event.getId())
                .accountHolderId(event.getAccountHolderId())
                .creationDate(event.getCreationDate())
                .accountType(event.getAccountType())
                .balance(event.getOpenBalance())
                .build();
        accountRepository.save(account);
    }

    @EventHandler
    @Override
    public void on(FundsDepositedEvent event) {
        Optional<BankAccount> account = accountRepository.findById(event.getId());
        if(!account.isPresent()) return;
        BankAccount bankAccount = account.get();
        bankAccount.setBalance(event.getBalance());
        accountRepository.save(account.get());
    }

    @EventHandler
    @Override
    public void on(FundsWithdrawnEvent event) {
        Optional<BankAccount> account = accountRepository.findById(event.getId());
        if(!account.isPresent()) return;
        BankAccount bankAccount = account.get();
        bankAccount.setBalance(event.getBalance());
        accountRepository.save(account.get());
    }

    @EventHandler
    @Override
    public void on(AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());
    }
}
