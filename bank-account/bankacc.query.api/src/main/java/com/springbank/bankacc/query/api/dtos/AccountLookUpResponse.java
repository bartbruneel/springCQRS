package com.springbank.bankacc.query.api.dtos;

import com.springbank.bankacc.dtos.BaseResponse;
import com.springbank.bankacc.models.BankAccount;

import java.util.Collections;
import java.util.List;


public class AccountLookUpResponse extends BaseResponse {

    private List<BankAccount> accounts;

    public AccountLookUpResponse(String message) {
        super(message);
    }

    public AccountLookUpResponse(String message, List<BankAccount> accounts) {
        super(message);
        this.accounts = accounts;
    }

    public AccountLookUpResponse(String message, BankAccount account) {
        super(message);
        this.accounts = Collections.singletonList(account);
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }
}
