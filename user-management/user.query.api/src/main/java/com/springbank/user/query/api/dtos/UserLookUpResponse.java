package com.springbank.user.query.api.dtos;

import com.springbank.user.core.dto.BaseResponse;
import com.springbank.user.core.models.User;

import java.util.Collections;
import java.util.List;

public class UserLookUpResponse extends BaseResponse {
    private List<User> users;

    public UserLookUpResponse(String message) {
        super(message);
    }

    public UserLookUpResponse(List<User> users) {
        super(null);
        this.users = users;
    }

    public UserLookUpResponse(String message, User user) {
        super(message);
        this.users = Collections.singletonList(user);
    }

    public UserLookUpResponse(User user) {
        super(null);
        this.users = Collections.singletonList(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
