package com.springbank.user.query.api.controllers;

import com.springbank.user.query.api.dtos.UserLookUpResponse;
import com.springbank.user.query.api.queries.FindAllUsersQuery;
import com.springbank.user.query.api.queries.FindUserByIdQuery;
import com.springbank.user.query.api.queries.SearchUsersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/userLookup")
public class UserLookupController {
    private final QueryGateway queryGateway;

    @Autowired
    public UserLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "/")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookUpResponse> getAllUsers() {
        try {
            FindAllUsersQuery findAllUsersQuery = new FindAllUsersQuery();
            UserLookUpResponse response = queryGateway.query(findAllUsersQuery, ResponseTypes.instanceOf(UserLookUpResponse.class)).join();
            if(response == null || response.getUsers() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            final String errorMessage = "failed to complete get all users request";
            System.out.println(ex);
            return new ResponseEntity<>(new UserLookUpResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path ="/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookUpResponse> getUserById(@PathVariable(value = "id") String id) {
        try {
            FindUserByIdQuery findUserByIdQuery = new FindUserByIdQuery(id);
            UserLookUpResponse response = queryGateway.query(findUserByIdQuery, ResponseTypes.instanceOf(UserLookUpResponse.class)).join();
            if(response == null || response.getUsers() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            final String errorMessage = "failed to complete get user by id request";
            System.out.println(ex);
            return new ResponseEntity<>(new UserLookUpResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path ="/byFilter/{filter}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookUpResponse> searchUser(@PathVariable(value = "filter") String filter) {
        try {
            SearchUsersQuery query = new SearchUsersQuery(filter);
            UserLookUpResponse response = queryGateway.query(query, ResponseTypes.instanceOf(UserLookUpResponse.class)).join();
            if(response == null || response.getUsers() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            final String errorMessage = "failed to complete user search request";
            System.out.println(ex);
            return new ResponseEntity<>(new UserLookUpResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
