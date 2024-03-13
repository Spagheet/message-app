package com.ubo.tp.message.datamodel;

import java.util.List;

public interface FilterObserver {
    public void notifyFilterUpdate(List<Message> messages);
}
