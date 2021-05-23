package com.springbank.bankacc.query.api.handlers;


import com.springbank.bankacc.query.api.dtos.AccountLookUpResponse;
import com.springbank.bankacc.query.api.queries.FindAccountByHolderIdQuery;
import com.springbank.bankacc.query.api.queries.FindAccountByIdQuery;
import com.springbank.bankacc.query.api.queries.FindAccountsWithBalanceQuery;
import com.springbank.bankacc.query.api.queries.FindAllAccountsQuery;

public interface AccountQueryHandler {
    AccountLookUpResponse findAccountById(FindAccountByIdQuery query);

    AccountLookUpResponse findAccountByHolderId(FindAccountByHolderIdQuery query);

    AccountLookUpResponse findAllAcounts(FindAllAccountsQuery query);

    AccountLookUpResponse findAccountsWithBalance(FindAccountsWithBalanceQuery query);
}
