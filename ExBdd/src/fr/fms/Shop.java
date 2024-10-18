package fr.fms;

import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;

public class Shop {

	public static void main(String[] args) {
		ArticleDao dao = new ArticleDao();
		UserDao udao = new UserDao();
		// udao.create(new User("sabrina@fms.fr", "sabrina123"));
		// udao.create(new User("kaenna@fms.fr", "kaenna123"));
		// displayListOfArticles(dao.readAll());
		// dao.create(new Article("Ecouteurs", "Samxhung", 199, 4));
		// displayListOfArticles(dao.readAll());
		// dao.delete(26);
		// displayListOfArticles(dao.readAll());
		// displayUser(udao.read(2));
		// udao.update(new User("Sabrina", "sasa123"), 4);
		// displayUser(udao.read(4));
		// udao.delete(2);
		// displayListOfUsers(udao.readAll());

		Scanner sc = new Scanner(System.in);

		boolean userConnected = false;
		boolean runningApp = true;

		while (runningApp) {
			System.out.println("1 - Montrer articles disponibles");
			if (!userConnected)
				System.out.println("2 - Se connecter");
			if (userConnected)
				System.out.println("3 - Se deconnecter");
			System.out.println("4 - Sortir");

			int userInputChoice = sc.nextInt();
			sc.nextLine();

			switch (userInputChoice) {
			case 1:
				if (userConnected) {
					displayListOfArticles(dao.readAll());
				} else {
					System.out.println("Veuillez vous connecter pour afficher les articles ! \n");
				}
				break;
			case 2:
				System.out.print("Login: ");
				String userInputLogin = sc.nextLine();
				System.out.println("Password: ");
				String userInputPassword = sc.nextLine();

				if (udao.verifyIfUserExists(userInputLogin, userInputPassword) != null) {
					userConnected = true;
					System.out.println("Utilisateur connecté\n");
				} else {
					System.out.println("Email ou mot de pass incorrects\n");
				}
				break;
			case 3:
				userConnected = false;
				System.out.println("Deconexion reussi.\n");
				break;
			case 4:
				System.out.println("Aurevoir !\n");
				runningApp = false;
				break;
			default:
				System.out.println("Choix invalide, veuillez reesayer !\n");
				break;
			}
		}
		sc.close();

	}

	public static void displayUser(User user) {
		System.out.printf("%-5s %-28s %-20s%n", "ID", "Login", "Password");
		System.out.println("---------------------------------------------------------");
		System.out.printf("%-5d %-28s %-20s%n", user.getId(), user.getLogin(), user.getPassword());
		System.out.println("---------------------------------------------------------");
	}

	public static void displayListOfUsers(ArrayList<User> users) {
		System.out.printf("%-5s %-28s %-20s%n", "ID", "Login", "Password");
		System.out.println("---------------------------------------------------------");
		users.forEach(
				(user) -> System.out.printf("%-5d %-28s %-20s%n", user.getId(), user.getLogin(), user.getPassword()));
		System.out.println("---------------------------------------------------------");
	}

	public static void displayListOfArticles(ArrayList<Article> articles) {
		System.out.printf("%-5s %-28s %-20s %-15s %-5s%n", "ID", "Description", "Brand", "Price", "IdCategory");
		System.out.println("-------------------------------------------------------------------------------------");

		articles.forEach((article) -> System.out.printf("%-5d %-28s %-20s %-15.2f %-5d%n", article.getId(),
				article.getDescription(), article.getBrand(), article.getPrice(), article.getIdCategory()));
		System.out.println("-------------------------------------------------------------------------------------");
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
