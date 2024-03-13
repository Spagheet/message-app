package main.java.com.ubo.tp.message.ihm.controler;

public interface AppMenuControllerObserver {
    public void setLoginView();
    public void setRegisterView();

    void setMessagesView();

    void setUsersView();

    void setProfileView();

    void logout();
}
