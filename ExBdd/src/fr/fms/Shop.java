package fr.fms;

import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.entities.Article;
import fr.fms.entities.User;
import fr.fms.job.IJobImp;

public class Shop {

	private ArrayList<Article> cartList = new ArrayList<Article>();

	public static void main(String[] args) {
		Shop shop = new Shop();
		IJobImp job = new IJobImp();

		// job.createUser(new User("sabrina@fms.fr", "sabrina123"));
		// job.createUser(new User("kaenna@fms.fr", "kaenna123"));

		// displayListOfArticles(job.readAllArticles());
		// job.createArticle(new Article("Ecouteurs", "Samxhung", 199, 4));
		// displayListOfArticles(job.readAllArticles());
		// job.deleteArticle(26);
		// displayListOfArticles(job.readAllArticles());
		// displayUser(udao.read(2));
		// job.updateUser(new User("Sabrina", "sasa123"), 4);
		// displayUser(job.readUser(4));
		// job.deleteUser(2);
		// displayListOfUsers(job.readAllUsers());

		Scanner sc = new Scanner(System.in);

		boolean userConnected = false;
		boolean runningApp = true;

		while (runningApp) {
			if (!userConnected) {
				userConnected = shop.loginMenu(sc, job);
			}

			if (userConnected) {
				shop.displayHomeMenu();

				int userScannerChoice = sc.nextInt();

				switch (userScannerChoice) {
				case 1:
					shop.displayListOfArticles(job.readAllArticles());
				}
			}

		}

		sc.close();

	}

	public void displayHomeMenu() {
		System.out.println(" --------------- Bienvenue dans FMS Shop ---------------\n");
		System.out.println("Veuillez choisir une option: ");
		System.out.println("1 - Liste d'articles disponibles | 2 - Panier");
	}

	public boolean loginMenu(Scanner sc, IJobImp job) {
		boolean userConnected = false;

		System.out.println(" --------------- Connexion Menu ---------------");
		System.out.println("Veuillez saisir: ");
		System.out.print(" - Email: ");
		String userScannerEmail = sc.nextLine();
		System.out.print(" - Mot de pass: ");
		String userScannerPassword = sc.nextLine();

		if (job.login(userScannerEmail, userScannerPassword) != null) {
			userConnected = true;
			System.out.println("Connexion réussi !\n");
		} else {
			System.out.println("Email et/ou mot de passe incorrects\n");
		}

		return userConnected;
	}

	public void displayCart() {
		if (cartList.size() <= 0) {
			System.out.println("Panier vide !\n");
		} else {
			displayListOfArticles(cartList);
		}
	}

	public void displayUser(User user) {
		System.out.printf("%-5s %-28s %-20s%n", "ID", "Login", "Password");
		System.out.println("---------------------------------------------------------");
		System.out.printf("%-5d %-28s %-20s%n", user.getId(), user.getLogin(), user.getPassword());
		System.out.println("---------------------------------------------------------");
	}

	public void displayListOfUsers(ArrayList<User> users) {
		System.out.printf("%-5s %-28s %-20s%n", "ID", "Login", "Password");
		System.out.println("---------------------------------------------------------");
		users.forEach(
				(user) -> System.out.printf("%-5d %-28s %-20s%n", user.getId(), user.getLogin(), user.getPassword()));
		System.out.println("---------------------------------------------------------");
	}

	public void displayListOfArticles(ArrayList<Article> articles) {
		System.out.printf("%-5s %-28s %-20s %-15s %-5s%n", "ID", "Description", "Brand", "Price", "IdCategory");
		System.out.println("-------------------------------------------------------------------------------------");

		articles.forEach((article) -> System.out.printf("%-5d %-28s %-20s %-15.2f %-5d%n", article.getId(),
				article.getDescription(), article.getBrand(), article.getPrice(), article.getIdCategory()));
		System.out.println("-------------------------------------------------------------------------------------");
	}

	public void displayArticleInfo(Article article) {
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
