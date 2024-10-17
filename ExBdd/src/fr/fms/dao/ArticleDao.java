package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import fr.fms.entities.Article;
import fr.fms.entities.BddConnection;

public class ArticleDao implements Dao<Article> {
	private Connection connection;
	private static final Logger LOGGER = Logger.getLogger(Dao.class.getName());

	public ArticleDao() {

		try {
			this.connection = BddConnection.getConnection();
		} catch (RuntimeException e) {
			LOGGER.severe("Probleme de connexion à la bdd: " + e.getMessage());
			throw new RuntimeException("Probleme de connexion à la bdd: ", e);
		}

	}

	@Override
	public void create(Article obj) {
		String strSql = "INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES (?,?,?);";

		try (PreparedStatement ps = connection.prepareStatement(strSql)) {
			ps.setString(1, obj.getDescription());
			ps.setString(2, obj.getBrand());
			ps.setDouble(3, obj.getPrice());

			ps.executeUpdate();

		} catch (SQLException e) {
			LOGGER.severe("Probleme dans la creation d'un article " + e.getMessage());
			throw new RuntimeException("Probleme avec la methode de creation d'article ", e);
		}
	}

	@Override
	public Article read(int id) {
		String strSql = "SELECT a.IdArticle, a.Description, a.Brand, a.UnitaryPrice, c.CatName, c.Description FROM T_Articles AS a JOIN T_Categories AS c ON a.IdCategory = c.IdCategory WHERE a.IdArticle = ?;";
		Article article = null;

		try (PreparedStatement ps = connection.prepareStatement(strSql)) {
			ps.setInt(1, id);
			try (ResultSet resultSet = ps.executeQuery()) {
				if (resultSet.next()) {
					int idArticle = resultSet.getInt("a.IdArticle");
					String description = resultSet.getString("a.Description");
					String brand = resultSet.getString("a.Brand");
					double price = resultSet.getDouble("a.UnitaryPrice");
					String catName = resultSet.getString("c.CatName");
					String catDescription = resultSet.getString("c.Description");

					article = new Article(idArticle, description, brand, price, catName, catDescription);
				}
			}
		} catch (SQLException e) {
			LOGGER.severe("Probleme dans la lecture d'un article " + e.getMessage());
			throw new RuntimeException("Probleme dans la lecture de l'artile : " + id, e);
		}

		return article;
	}

	@Override
	public boolean update(Article obj, int id) {
		String strSql = "UPDATE T_Articles SET Description = ?, Brand = ?, UnitaryPrice = ? WHERE IdArticle = ?;";

		boolean updated = false;
		try (PreparedStatement ps = connection.prepareStatement(strSql)) {
			ps.setString(1, obj.getDescription());
			ps.setString(2, obj.getBrand());
			ps.setDouble(3, obj.getPrice());
			ps.setInt(4, id);

			if (ps.executeUpdate() == 1) {
				updated = true;
			}

		} catch (SQLException e) {
			LOGGER.severe("Probleme dans l'actualisation d'un article " + e.getMessage());
			throw new RuntimeException("Probleme dans l'actualisation de l'artile : " + id, e);
		}

		return updated;
	}

	@Override
	public boolean delete(int id) {
		String strSql = "DELETE FROM T_Articles WHERE IdArticle = ?;";
		boolean deleted = false;
		try (PreparedStatement ps = connection.prepareStatement(strSql)) {
			ps.setInt(1, id);

			if (ps.executeUpdate() == 1) {
				deleted = true;
			}

		} catch (SQLException e) {
			LOGGER.severe("Probleme dans la suppression d'un article " + e.getMessage());
			throw new RuntimeException("Probleme dans la suppression de l'artile : " + id, e);
		}

		return deleted;
	}

	@Override
	public ArrayList<Article> readAll() {
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
			LOGGER.severe("Probleme dans la lecture de tout les articles " + e.getMessage());
			throw new RuntimeException("Probleme dans la lecture de tout les articles ", e);
		}

		return articles;
	}

}
