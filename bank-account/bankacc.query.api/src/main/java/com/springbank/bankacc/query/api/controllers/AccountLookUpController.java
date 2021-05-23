package com.springbank.bankacc.query.api.controllers;

import com.springbank.bankacc.query.api.dtos.AccountLookUpResponse;
import com.springbank.bankacc.query.api.dtos.EqualityType;
import com.springbank.bankacc.query.api.queries.FindAccountByHolderIdQuery;
import com.springbank.bankacc.query.api.queries.FindAccountByIdQuery;
import com.springbank.bankacc.query.api.queries.FindAccountsWithBalanceQuery;
import com.springbank.bankacc.query.api.queries.FindAllAccountsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/bankAccountLookup")
public class AccountLookUpController {

    private final QueryGateway queryGateway;

    @Autowired
    public AccountLookUpController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "/")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookUpResponse> getAllAccounts() {
        try {
            FindAllAccountsQuery query = new FindAllAccountsQuery();
            AccountLookUpResponse response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookUpResponse.class)).join();
            if(response == null || response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            final String safeErrorMessage = "Failed to complete get all accounts request";
            System.out.println(ex.toString());
            return new ResponseEntity<>(new AccountLookUpResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byId/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookUpResponse> getAccountById(@PathVariable(value = "id") String id) {
        try {
            FindAccountByIdQuery query = new FindAccountByIdQuery(id);
            AccountLookUpResponse response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookUpResponse.class)).join();
            if(response == null || response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            final String safeErrorMessage = "Failed to complete get account by id request";
            System.out.println(ex.toString());
            return new ResponseEntity<>(new AccountLookUpResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byHolderId/{accountHolderId}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookUpResponse> getAccountByHolderId(@PathVariable(value = "accountHolderId") String accountHolderId) {
        try {
            FindAccountByHolderIdQuery query = new FindAccountByHolderIdQuery(accountHolderId);
            AccountLookUpResponse response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookUpResponse.class)).join();
            if(response == null || response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            final String safeErrorMessage = "Failed to complete get account by account holder id request";
            System.out.println(ex.toString());
            return new ResponseEntity<>(new AccountLookUpResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/withBalance/{equalityType}/{balance}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookUpResponse> getAccountByHolderId(@PathVariable(value = "equalityType") EqualityType equalityType,
                                                                      @PathVariable(value = "balance") double balance) {
        try {
            FindAccountsWithBalanceQuery query = new FindAccountsWithBalanceQuery(equalityType, balance);
            AccountLookUpResponse response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookUpResponse.class)).join();
            if(response == null || response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            final String safeErrorMessage = "Failed to complete get account by balance request";
            System.out.println(ex.toString());
            return new ResponseEntity<>(new AccountLookUpResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
