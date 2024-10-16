package fr.fms.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import fr.fms.entities.Article;

public class JDBC {

	public static void main(String[] args) throws IOException {
		Properties prop = CreateConfigFile.readPropertiesFile("conf.properties");

		loadJDBCdriver(prop.getProperty("db.driver.class"));

		try (Connection connection = connectToDB(prop.getProperty("db.url"), prop.getProperty("db.login"),
				prop.getProperty("db.pwd"))) {

			if (connection != null) {
				// EXO 2
				// displayListOfArticles(getListOfArticles(connection));

				// EXO 3
				// addArticle(connection, new Article("Souris", "Corsair", 89.9));
				// updateArticle(connection, 7, new Article("Casque Audio", "Logitoch", 99.99));
				// deleteArticle(connection, 19);
				displayArticleInfo(getArticle(connection, 17));

				// displayListOfArticles(getListOfArticles(connection));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void loadJDBCdriver(String jbdcDriver) {
		try {
			Class.forName(jbdcDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection connectToDB(String url, String login, String pwd) {

		try {
			return DriverManager.getConnection(url, login, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ArrayList<Article> getListOfArticles(Connection connection) {
		String strSql = "SELECT * FROM T_Articles;";
		ArrayList<Article> articles = new ArrayList<>();

		try (Statement statement = connection.createStatement()) {

			try (ResultSet resultSet = statement.executeQuery(strSql)) {

				while (resultSet.next()) {
					int rsIdUser = resultSet.getInt(1);
					String rsDescription = resultSet.getString(2);
					String rsBrand = resultSet.getString(3);
					double rsPrice = resultSet.getDouble(4);
					int rsIdCategory = resultSet.getInt(5);

					articles.add(new Article(rsIdUser, rsDescription, rsBrand, rsPrice, rsIdCategory));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return articles;
	}

	public static void displayListOfArticles(ArrayList<Article> articles) {
		System.out.printf("%-5s %-28s %-20s %-15s %-5s%n", "ID", "Description", "Brand", "Price", "IdCategory");
		System.out.println("-------------------------------------------------------------------------------------");

		articles.forEach((article) -> {
			System.out.printf("%-5d %-28s %-20s %-15.2f %-5d%n", article.getId(), article.getDescription(),
					article.getBrand(), article.getPrice(), article.getIdCategory());
		});
		System.out.println("-------------------------------------------------------------------------------------");
	}

	public static void addArticle(Connection connection, Article article) {
		String strSql = "INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES (?,?,?);";

		try (PreparedStatement ps = connection.prepareStatement(strSql)) {
			ps.setString(1, article.getDescription());
			ps.setString(2, article.getBrand());
			ps.setDouble(3, article.getPrice());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateArticle(Connection connection, int articleId, Article article) {
		String strSql = "UPDATE T_Articles SET Description = ?, Brand = ?, UnitaryPrice = ? WHERE IdArticle = ?;";

		try (PreparedStatement ps = connection.prepareStatement(strSql)) {
			ps.setString(1, article.getDescription());
			ps.setString(2, article.getBrand());
			ps.setDouble(3, article.getPrice());
			ps.setInt(4, articleId);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void deleteArticle(Connection connection, int articleId) {
		String strSql = "DELETE FROM T_Articles WHERE IdArticle = ?;";

		try (PreparedStatement ps = connection.prepareStatement(strSql)) {
			ps.setInt(1, articleId);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Article getArticle(Connection connection, int articleId) {
		String strSql = "SELECT a.IdArticle, a.Description, a.Brand, a.UnitaryPrice, c.CatName, c.Description FROM T_Articles AS a JOIN T_Categories AS c ON a.IdCategory = c.IdCategory WHERE a.IdArticle = ?;";
		Article article = null;

		try (PreparedStatement ps = connection.prepareStatement(strSql)) {
			ps.setInt(1, articleId);
			try (ResultSet resultSet = ps.executeQuery()) {
				if (resultSet.next()) {
					int id = resultSet.getInt("a.IdArticle");
					String description = resultSet.getString("a.Description");
					String brand = resultSet.getString("a.Brand");
					double price = resultSet.getDouble("a.UnitaryPrice");
					String catName = resultSet.getString("c.CatName");
					String catDescription = resultSet.getString("c.Description");

					article = new Article(id, description, brand, price, catName, catDescription);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return article;
	}

	public static void displayArticleInfo(Article article) {
		System.out.printf("%-5s %-28s %-20s %-15s %-20s %-30s%n", "ID", "Description", "Brand", "Price", "Cat. Name",
				"Cat. Description");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-5d %-28s %-20s %-15.2f %-20s %-20s%n", article.getId(), article.getDescription(),
				article.getBrand(), article.getPrice(), article.getCatName(), article.getCatDescription());
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------");
	}
}
