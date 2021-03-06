package com.springbank.user.query.api.handlers;

import com.springbank.user.query.api.dtos.UserLookUpResponse;
import com.springbank.user.query.api.queries.FindAllUsersQuery;
import com.springbank.user.query.api.queries.FindUserByIdQuery;
import com.springbank.user.query.api.queries.SearchUsersQuery;

public interface UserQueryHandler {

    UserLookUpResponse getUserById(FindUserByIdQuery query);

    UserLookUpResponse searchUsers(SearchUsersQuery query);

    UserLookUpResponse getAllUsers(FindAllUsersQuery query);

}
