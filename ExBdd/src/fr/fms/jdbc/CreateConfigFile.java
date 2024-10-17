package fr.fms.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class CreateConfigFile {

	private static final Logger LOGGER = Logger.getLogger(CreateConfigFile.class.getName());

	public static Properties readPropertiesFile(String fileName) throws IOException {

		Properties prop = new Properties();

		try (FileInputStream fis = new FileInputStream(fileName)) {
			prop.load(fis);
		} catch (FileNotFoundException e) {
			LOGGER.severe(String.format("Le fichier: %s n'a pas été trouvé.%n", fileName));
			throw new FileNotFoundException(fileName);
		}

		return prop;
	}
}
