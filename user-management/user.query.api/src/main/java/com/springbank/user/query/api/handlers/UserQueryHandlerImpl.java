package com.springbank.user.query.api.handlers;

import com.springbank.user.core.models.User;
import com.springbank.user.query.api.dtos.UserLookUpResponse;
import com.springbank.user.query.api.queries.FindAllUsersQuery;
import com.springbank.user.query.api.queries.FindUserByIdQuery;
import com.springbank.user.query.api.queries.SearchUsersQuery;
import com.springbank.user.query.api.repositories.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserQueryHandlerImpl implements UserQueryHandler {

    private final UserRepository userRepository;

    @Autowired
    public UserQueryHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryHandler
    @Override
    public UserLookUpResponse getUserById(FindUserByIdQuery query) {
        final Optional<User> user = userRepository.findById(query.getId());
        return user.isPresent()? new UserLookUpResponse(user.get()) : null;
    }

    @QueryHandler
    @Override
    public UserLookUpResponse searchUsers(SearchUsersQuery query) {
        final List<User> users = new ArrayList<>(userRepository.findByFilterRegex(query.getFilter()));
        return new UserLookUpResponse(users);
    }

    @QueryHandler
    @Override
    public UserLookUpResponse getAllUsers(FindAllUsersQuery query) {
        final List<User> users = new ArrayList<>(userRepository.findAll());
        return new UserLookUpResponse(users);
    }
}
