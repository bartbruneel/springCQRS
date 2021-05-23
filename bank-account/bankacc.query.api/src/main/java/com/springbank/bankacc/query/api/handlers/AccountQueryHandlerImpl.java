package com.springbank.bankacc.query.api.handlers;

import com.springbank.bankacc.models.BankAccount;
import com.springbank.bankacc.query.api.dtos.AccountLookUpResponse;
import com.springbank.bankacc.query.api.dtos.EqualityType;
import com.springbank.bankacc.query.api.queries.FindAccountByHolderIdQuery;
import com.springbank.bankacc.query.api.queries.FindAccountByIdQuery;
import com.springbank.bankacc.query.api.queries.FindAccountsWithBalanceQuery;
import com.springbank.bankacc.query.api.queries.FindAllAccountsQuery;
import com.springbank.bankacc.query.api.repositories.AccountRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountQueryHandlerImpl implements AccountQueryHandler {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountQueryHandlerImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @QueryHandler
    public AccountLookUpResponse findAccountById(FindAccountByIdQuery query) {
        String id = query.getId();
        Optional<BankAccount> bankAccount = accountRepository.findById(id);
        if(!bankAccount.isPresent()) {
            return new AccountLookUpResponse("Bank account not found with id " + id);
        } else {
           return new AccountLookUpResponse("Bank Account Succesfully Returned", bankAccount.get());
        }
    }

    @Override
    @QueryHandler
    public AccountLookUpResponse findAccountByHolderId(FindAccountByHolderIdQuery query) {
        String id = query.getAccountHolderId();
        Optional<BankAccount> bankAccount = accountRepository.findByAccountHolderId(id);
        if(!bankAccount.isPresent()) {
            return new AccountLookUpResponse("Bank account not found with account holder id " + id);
        } else {
            return new AccountLookUpResponse("Bank Account Succesfully Returned", bankAccount.get());
        }
    }

    @Override
    @QueryHandler
    public AccountLookUpResponse findAllAcounts(FindAllAccountsQuery query) {
        Iterable<BankAccount> all = accountRepository.findAll();
        if(!all.iterator().hasNext()) {
            return new AccountLookUpResponse("No Bank Accounts were found!");
        } else {
            ArrayList<BankAccount> accounts = new ArrayList<>();
            all.iterator().forEachRemaining(i -> accounts.add(i));
            int size = accounts.size();
            return new AccountLookUpResponse("Successfully returned " + size + "Bank Account(s)", accounts);
        }
    }

    @Override
    @QueryHandler
    public AccountLookUpResponse findAccountsWithBalance(FindAccountsWithBalanceQuery query) {
        List<BankAccount> bankAccounts = query.getEqualityType() == EqualityType.GREATER_THAN ?
                accountRepository.findByBalanceGreaterThan(query.getBalance()) :
                accountRepository.findByBalanceLessThan(query.getBalance());

        return bankAccounts != null && !bankAccounts.isEmpty() ?
                new AccountLookUpResponse("Successfully returned " + bankAccounts.size() + "Bank Account(s)", bankAccounts) :
                new AccountLookUpResponse("No Bank Accounts were found!");


    }
}
