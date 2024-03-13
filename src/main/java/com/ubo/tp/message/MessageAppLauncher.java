package main.java.com.ubo.tp.message;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.Database;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.ihm.controler.MessageApp;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe de lancement de l'application.
 *
 * @author S.Lucas
 */
public class MessageAppLauncher {

	/**
	 * Indique si le mode bouchoné est activé.
	 */
	protected static boolean IS_MOCK_ENABLED = false;

	/**
	 * Launcher.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		IDatabase database = new Database();
		EntityManager entityManager = new EntityManager(database);
		/*
		if (IS_MOCK_ENABLED) {
			MessageAppMock mock = new MessageAppMock(database, entityManager);
			mock.showGUI();
		}
		*/
		MessageApp messageApp = new MessageApp(database, entityManager);

		messageApp.init();
		messageApp.show();

		// j'ajoute des user de test
		Map<String, String> user1 = new HashMap<>();
		user1.put("tag", "kevin29200");
		user1.put("password", "kevin");
		user1.put("username", "kevin");
		user1.put("avatar", "/home/jake/Documents/Work/2024/ERGO/MessageApp/src/main/resources/images/tealt.png");

		Map<String, String> user2 = new HashMap<>();
		user2.put("tag", "dave29200");
		user2.put("password", "dave");
		user2.put("username", "dave");
		user2.put("avatar", "/home/jake/Documents/Work/2024/ERGO/MessageApp/src/main/resources/images/spagheet.png");
		messageApp.registerUser(user1);
		messageApp.registerUser(user2);

		messageApp.sendMessage("this is a message", "kevin29200");
		messageApp.sendMessage("this is a tag message #abab", "kevin29200");
		messageApp.sendMessage("this is an at message @dave", "kevin29200");

		messageApp.sendMessage("this is also a message", "dave29200");
		messageApp.sendMessage("this is also a tag message #abab", "dave29200");
		messageApp.sendMessage("this is an at message @kevin", "dave29200");
	}
}
