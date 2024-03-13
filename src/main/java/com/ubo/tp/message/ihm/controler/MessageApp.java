package main.java.com.ubo.tp.message.ihm.controler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.core.directory.IWatchableDirectory;
import main.java.com.ubo.tp.message.core.directory.WatchableDirectory;
import main.java.com.ubo.tp.message.core.util.UserGenerator;
import main.java.com.ubo.tp.message.datamodel.*;
import main.java.com.ubo.tp.message.ihm.component.ActionsComponent;
import main.java.com.ubo.tp.message.ihm.component.ActionsComponentObserver;
import main.java.com.ubo.tp.message.ihm.view.FolderSelectionView;
import main.java.com.ubo.tp.message.ihm.view.MessageAppMainView;
import main.java.com.ubo.tp.message.ihm.view.panel.ControlableContentPanel;
import main.java.com.ubo.tp.message.logger.DatabaseLogger;
import main.java.com.ubo.tp.message.logger.DirectoryLogger;

/**
 * Classe principale l'application.
 *
 * @author S.Lucas
 */
public class MessageApp implements ActionsComponentObserver, IDatabaseObserver {

	ActionsComponent actionsComponent;

	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Vue principale de l'application.
	 */
	protected MessageAppMainView mMainView;

	protected FolderSelectionView folderSelectionView;

	/**
	 * Classe de surveillance de répertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;

	protected DirectoryLogger mDirectoryLogger;

	/**
	 * Répertoire d'échange de l'application.
	 */
	protected String mExchangeDirectoryPath;

	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;

	protected ControlableContentPanel controlableContentPanel;
	protected UserGenerator userGenerator;
	protected Session session;
	protected MessageFilter messageFilter;
	protected UserFilter userFilter;

	/**
	 * Constructeur.
	 *
	 * @param entityManager
	 * @param database
	 */
	public MessageApp(IDatabase database, EntityManager entityManager) {
		this.mEntityManager = entityManager;

		this.mDatabase = database;
		this.mDatabase.addObserver(new DatabaseLogger());
		this.mDatabase.addObserver(this);

		this.messageFilter = new MessageFilter();
		this.userFilter = new UserFilter();

		this.userGenerator = new UserGenerator();
		this.controlableContentPanel = new ControlableContentPanel();

		this.mMainView = new MessageAppMainView(controlableContentPanel);
		this.folderSelectionView = new FolderSelectionView();

		this.actionsComponent = new ActionsComponent(this, controlableContentPanel);

		this.messageFilter.addObserver(actionsComponent.getMessagesController());
		this.userFilter.addObserver(actionsComponent.getUsersController());

		this.session = new Session();
		session.addObserver(this.actionsComponent);
	}

	/**
	 * Initialisation de l'application.
	 */
	public void init() {
		// Init du look and feel de l'application
		this.initLookAndFeel();

		// Initialisation du répertoire d'échange
		//this.initDirectory(folderSelectionView.getDirectory());

		// Initialisation de l'IHM
		this.initGui();
	}

	/**
	 * Initialisation du look and feel de l'application.
	 */
	protected void initLookAndFeel() {
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
		this.mMainView.initGUI();
	}

	/**
	 * Indique si le fichier donné est valide pour servir de répertoire d'échange
	 *
	 * @param directory , Répertoire à tester.
	 */
	protected boolean isValideExchangeDirectory(File directory) {
		// Valide si répertoire disponible en lecture et écriture
		return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
				&& directory.canWrite();
	}

	/**
	 * Initialisation du répertoire d'échange.
	 *
	 * @param directoryPath
	 */
	protected void initDirectory(String directoryPath) {

		mExchangeDirectoryPath = directoryPath;
		mWatchableDirectory = new WatchableDirectory(directoryPath);
		mDirectoryLogger = new DirectoryLogger();
		mEntityManager.setExchangeDirectory(directoryPath);

		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
		mWatchableDirectory.addObserver(mDirectoryLogger);
	}

	public void show() {
		// ... setVisible?
		this.mMainView.showGUI();
	}

	public User findUser(String tag) {
		Set<User> result = this.mDatabase.getUsers();
		for(User u : result) {
			if(u.getUserTag().equals(tag)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public boolean registerUser(Map<String, String> fields) {
		String tag = fields.get("tag");
		String password = fields.get("password");
		String username = fields.get("username");
		String avatar = fields.get("avatar");
		if(this.findUser(tag)==null) {
			this.mDatabase.addUser(userGenerator.generateUser(tag, password, username, avatar));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean loginUser(String tag, String password) {
		User user = this.findUser(tag);
		if(user != null && user.getUserPassword().equals(password)) {
			System.out.println("USER FOUND");
			this.session.setUser(user);
			this.actionsComponent.goBackToMenu();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void sendMessage(String message) {
		User sender = this.session.getUser();
		Message newMessage = new Message(sender, message);
		this.mDatabase.addMessage(newMessage);
	}
	public void sendMessage(String message, String user) {
		User sender = this.findUser(user);
		Message newMessage = new Message(sender, message);
		this.mDatabase.addMessage(newMessage);
	}
	public List<User> getFollowers() {
		User currentUser = this.session.getUser();
		List<User> followers = new ArrayList<>();
		for(User user : this.mDatabase.getUsers()) {
			if(user.getFollows().contains(currentUser)) {
				followers.add(user);
			}
		}
		return followers;
	}

	@Override
	public void setFilter(String filter) {
		this.messageFilter.setFilter(filter);
	}
	@Override
	public void setUserFilter(String filter) {
		this.userFilter.setFilter(filter);
	}
	@Override
	public void unsubscribeEvent(User user) {
		this.session.removeFollowing(user);
	}
	@Override
	public void subscribeEvent(User user) {
		this.session.addFollowing(user);
	}
	@Override
	public void changeAvatar(String path) {
		this.session.setAvatarPath(path);
	}

	@Override
	public void logout() {
		this.session.logout();
	}

	@Override
	public void notifyMessageAdded(Message addedMessage) {
		this.messageFilter.notifyMessageAdded(addedMessage);
	}
	@Override
	public void notifyMessageDeleted(Message deletedMessage) {
		this.messageFilter.notifyMessageDeleted(deletedMessage);
	}
	@Override
	public void notifyMessageModified(Message modifiedMessage) {
		this.messageFilter.notifyMessageModified(modifiedMessage);
	}
	@Override
	public void notifyUserAdded(User addedUser) {
		this.userFilter.notifyUserAdded(addedUser);
	}
	@Override
	public void notifyUserDeleted(User deletedUser) {
		this.userFilter.notifyUserDeleted(deletedUser);
	}
	@Override
	public void notifyUserModified(User modifiedUser) {
		this.userFilter.notifiyUserModified(modifiedUser);
	}
}
