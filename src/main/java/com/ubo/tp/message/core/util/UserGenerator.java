package main.java.com.ubo.tp.message.core.util;

import main.java.com.ubo.tp.message.datamodel.User;

import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

public class UserGenerator {
    public User generateUser(String tag, String password, String username, String avatar) {
        User newUser = new User(UUID.randomUUID(), tag, password, username, new HashSet<>(), avatar);
        return newUser;
    }
}
