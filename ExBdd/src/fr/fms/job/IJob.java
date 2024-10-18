package fr.fms.job;

import java.util.ArrayList;

import fr.fms.entities.Article;
import fr.fms.entities.User;

public interface IJob {
	public Integer login(String email, String password);

	public void createUser(User user);

	public void readUser(int id);

	public boolean updateUser(User user, int id);

	public boolean deleteUser(int id);

	public void createArticle(Article article);

	public void readArticle(int id);

	public boolean updateArticle(Article article, int id);

	public boolean deleteArticle(int id);

	public ArrayList<Article> readAllArticles();
}
