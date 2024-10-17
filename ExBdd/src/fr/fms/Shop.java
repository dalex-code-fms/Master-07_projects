package fr.fms;

import java.util.ArrayList;

import fr.fms.dao.ArticleDao;
import fr.fms.entities.Article;

public class Shop {

	public static void main(String[] args) {
		ArticleDao dao = new ArticleDao();

		// displayListOfArticles(dao.readAll());
		// dao.Create(new Article("Ecouteurs", "Samxhung", 199, 4));
		displayListOfArticles(dao.readAll());
		dao.delete(19);

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
