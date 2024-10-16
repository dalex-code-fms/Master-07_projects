package fr.fms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Article;

public class JDBC {

	public static void main(String[] args) {

		ArrayList<Article> articles = new ArrayList<>();

		loadJDBCdriver();

		try (Connection connection = connectToDB()) {

			if (connection != null) {
				articles = getListOfArticles(connection);
				displayArticles(articles);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void loadJDBCdriver() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection connectToDB() {
		String url = "jdbc:mariadb://localhost:3306/shop";
		String login = "root";
		String pwd = "fms2024";

		try {
			return DriverManager.getConnection(url, login, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ArrayList<Article> getListOfArticles(Connection connection) {
		String strSql = "SELECT * FROM T_Articles";
		ArrayList<Article> articles = new ArrayList<>();

		try (Statement statement = connection.createStatement()) {

			try (ResultSet resultSet = statement.executeQuery(strSql)) {

				while (resultSet.next()) {
					int rsIdUser = resultSet.getInt(1);
					String rsDescription = resultSet.getString(2);
					String rsBrand = resultSet.getString(3);
					double rsPrice = resultSet.getDouble(4);

					articles.add(new Article(rsIdUser, rsDescription, rsBrand, rsPrice));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return articles;
	}

	public static void displayArticles(ArrayList<Article> articles) {
		System.out.printf("%-5s %-28s %-20s %-10s%n", "ID", "Description", "Brand", "Price");
		System.out.println("-------------------------------------------------------------------");

		articles.forEach((article) -> {
			System.out.printf("%-5d %-28s %-20s %.2f€%n", article.getId(), article.getDescription(), article.getBrand(),
					article.getPrice());
		});
		System.out.println("-------------------------------------------------------------------");
	}

}
