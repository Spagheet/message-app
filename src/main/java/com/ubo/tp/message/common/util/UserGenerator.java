package com.ubo.tp.message.common.util;

import com.ubo.tp.message.datamodel.User;

import java.util.HashSet;
import java.util.UUID;

public class UserGenerator {
    public User generateUser(String tag, String password, String username, String avatar) {
        User newUser = new User(UUID.randomUUID(), tag, password, username, new HashSet<>(), avatar);
        return newUser;
    }
}
