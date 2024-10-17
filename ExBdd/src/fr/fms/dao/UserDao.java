package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import fr.fms.entities.BddConnection;
import fr.fms.entities.User;

public class UserDao implements Dao<User> {

	private Connection connection;
	private static final Logger LOGGER = Logger.getLogger(Dao.class.getName());

	public UserDao() {

		try {
			this.connection = BddConnection.getConnection();
		} catch (RuntimeException e) {
			LOGGER.severe("Probleme de connexion à la bdd: " + e.getMessage());
			throw new RuntimeException("Probleme de connexion à la bdd: ", e);
		}
	}

	@Override
	public void create(User obj) {
		String strSql = "INSERT INTO T_Users ( Login, Password ) VALUES (?,?);";

		try (PreparedStatement ps = connection.prepareStatement(strSql)) {
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPassword());

			ps.executeUpdate();

		} catch (SQLException e) {
			LOGGER.severe("Probleme dans la creation d'un nouveau utilisateur " + e.getMessage());
			throw new RuntimeException("Probleme avec la methode de creation d'un nouveau utilisateur ", e);
		}

	}

	@Override
	public User read(int id) {
		String strSql = "SELECT * FROM T_Users WHERE T_Users.IdUser = ?;";
		User user = null;

		try (PreparedStatement ps = connection.prepareStatement(strSql)) {
			ps.setInt(1, id);
			try (ResultSet resultSet = ps.executeQuery()) {
				if (resultSet.next()) {
					int idUser = resultSet.getInt("IdUser");
					String login = resultSet.getString("Login");
					String password = resultSet.getString("Password");

					user = new User(idUser, login, password);
				}
			}
		} catch (SQLException e) {
			LOGGER.severe("Probleme dans la lecture d'un utilisateur " + e.getMessage());
			throw new RuntimeException("Probleme dans la lecture de l'utilisateur : " + id, e);
		}

		return user;
	}

	@Override
	public boolean update(User obj, int id) {
		String strSql = "UPDATE T_Users SET Login = ?, Password = ? WHERE IdUser = ?;";

		boolean updated = false;
		try (PreparedStatement ps = connection.prepareStatement(strSql)) {
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPassword());
			ps.setInt(3, id);

			if (ps.executeUpdate() == 1) {
				updated = true;
			}

		} catch (SQLException e) {
			LOGGER.severe("Probleme dans la modification de l'utilisateur " + e.getMessage());
			throw new RuntimeException("Probleme dans la modification de l'utilisateur : " + id, e);
		}

		return updated;
	}

	@Override
	public boolean delete(int id) {
		String strSql = "DELETE FROM T_Users WHERE IdUser = ?;";
		boolean deleted = false;
		try (PreparedStatement ps = connection.prepareStatement(strSql)) {
			ps.setInt(1, id);

			if (ps.executeUpdate() == 1) {
				deleted = true;
			}

		} catch (SQLException e) {
			LOGGER.severe("Probleme dans la suppression de l'utilisateur " + e.getMessage());
			throw new RuntimeException("Probleme dans la suppression de l'utilisateur : " + id, e);
		}

		return deleted;
	}

	@Override
	public ArrayList<User> readAll() {
		String strSql = "SELECT * FROM T_Users;";
		ArrayList<User> users = new ArrayList<>();

		try (Statement statement = connection.createStatement()) {

			try (ResultSet resultSet = statement.executeQuery(strSql)) {

				while (resultSet.next()) {
					int idUser = resultSet.getInt(1);
					String login = resultSet.getString(2);
					String password = resultSet.getString(3);

					users.add(new User(idUser, login, password));
				}
			}
		} catch (SQLException e) {
			LOGGER.severe("Probleme dans la lecture de tout les utilisateurs " + e.getMessage());
			throw new RuntimeException("Probleme dans la lecture de tout les utilisateurs ", e);
		}

		return users;
	}

}
