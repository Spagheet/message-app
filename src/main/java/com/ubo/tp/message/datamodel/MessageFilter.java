package main.java.com.ubo.tp.message.datamodel;

import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.ihm.controler.MessagesController;

import java.util.*;

public class MessageFilter {
    List<Message> messageList;
    List<FilterObserver> observers;
    String filter;

    public MessageFilter() {
        this.filter = "";
        this.messageList = new ArrayList<Message>();
        this.observers = new ArrayList<>();
    }
    protected void notifyObservers() {
        List<Message> filteredMessages = this.getFilteredList();
        for(FilterObserver o : observers) {
            o.notifyFilterUpdate(filteredMessages);
        }
    }
    public void addObserver(FilterObserver observer) {
        this.observers.add(observer);
    }
    protected void sortMessage() {
        class MessageComparator implements Comparator<Message> {
            @Override
            public int compare(Message m1, Message m2) {
                if(m1.getEmissionDate() == m2.getEmissionDate()) {
                    return 0;
                } else {
                    return (m1.getEmissionDate()>m2.getEmissionDate()?1:-1);
                }
            }
        }
        this.messageList.sort(new MessageComparator());
    }

    protected List<Message> getFilteredList() {
        if(this.filter.equals("")) {
            return this.messageList;
        }
        String atMention = "@.*";
        String tagMention = "#.*";
        boolean matchesAtMention = filter.matches(atMention);
        boolean matchesTagMention = filter.matches(tagMention);
        boolean oneFilter = matchesTagMention ^ matchesAtMention;
        System.out.println("getFilteredList\n@mention : "+matchesAtMention+"\n#mention : "+matchesTagMention);
        if(oneFilter) {
            if(matchesAtMention) {
                // assumption : filter is of form @NAME
                String name = filter.substring(1);
                return this.filterByAtMention(name);
            } else {
                // assumption : filter is of form #TAG
                String tag = filter.substring(1);
                return filterByTag(tag);
            }
        } else {
            List<Message> filteredList = new ArrayList<>();
            filteredList.addAll(this.filterByAtMention(filter));
            filteredList.addAll(this.filterByTag(filter));
            return filteredList;
        }
    }

    protected List<Message> filterByAtMention(String name) {
        List<Message> filteredList = new ArrayList<>();
        System.out.println("filter by at mention : " + name);
        for(Message m : messageList) {
            if(m.getSender().getName().equals(name) || m.containsUserTag(name) || m.getText().matches(".*"+ "@" + name+".*")) {
                filteredList.add(m);
            }
        }
        return filteredList;
    }
    protected List<Message> filterByTag(String tag) {
        List<Message> filteredList = new ArrayList<>();
        System.out.println("filter by tag mention : " + tag);
        for(Message m : messageList) {
            if(m.containsTag(tag)) {
                filteredList.add(m);
            }
        }
        return filteredList;
    }
    public void setFilter(String filter) {
        this.filter = filter;
        this.notifyObservers();
    }

    public void notifyMessageAdded(Message addedMessage) {
        this.messageList.add(addedMessage);
        this.notifyObservers();
    }

    public void notifyMessageDeleted(Message deletedMessage) {
        this.messageList.remove(deletedMessage);
        this.notifyObservers();
    }

    public void notifyMessageModified(Message modifiedMessage) {
        UUID uuid = modifiedMessage.getUuid();
        Message modified = null;
        for(Message m : messageList) {
            if(m.getUuid().equals(uuid)) {
                modified = m;
                break;
            }
        }
        if(modified != null) {
            messageList.remove(modified);
            messageList.add(modifiedMessage);
        }
        this.sortMessage();
        this.notifyObservers();
    }
}
