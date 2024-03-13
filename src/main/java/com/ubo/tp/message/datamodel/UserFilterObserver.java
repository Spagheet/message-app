package com.ubo.tp.message.datamodel;

import java.util.List;

public interface UserFilterObserver {
    public void notifyFilterUpdate(List<User> userList);
}
