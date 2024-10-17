package fr.fms.entities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import fr.fms.jdbc.CreateConfigFile;

public class BddConnection {

	private static BddConnection instance;
	private static final Logger LOGGER = Logger.getLogger(BddConnection.class.getName());
	private static final String FILENAME = "conf.properties";
	private static final String DRIVER = "db.driver.class";
	private static final String URL = "db.url";
	private static final String LOGIN = "db.login";
	private static final String PASSWORD = "db.pwd";

	private BddConnection() {

	}

	public static BddConnection getInstance() {
		if (instance == null) {
			instance = new BddConnection();
		}
		return instance;
	}

	public Connection getConnection() {

		try {
			Properties prop = CreateConfigFile.readPropertiesFile(FILENAME);

			loadJdbcDriver(prop.getProperty(DRIVER));

			return DriverManager.getConnection(prop.getProperty(URL), prop.getProperty(LOGIN),
					prop.getProperty(PASSWORD));
		} catch (IOException e) {
			LOGGER.severe("Erreur lors de la lecture du fichier de configuration : " + e.getMessage());
			throw new RuntimeException("Erreur de configuration", e);
		} catch (SQLException e) {
			LOGGER.severe("Erreur lors de la connexion à la base de données : " + e.getMessage());
			throw new RuntimeException("Impossible de se connecter à la base de données", e);
		}
	}

	public static void loadJdbcDriver(String jbdcDriver) {
		try {
			Class.forName(jbdcDriver);
		} catch (ClassNotFoundException e) {
			LOGGER.severe("Driver JDBC introuvable : " + e.getMessage());
			throw new RuntimeException("Le driver JDBC specifie est introuvable : " + jbdcDriver, e);
		}
	}

}
