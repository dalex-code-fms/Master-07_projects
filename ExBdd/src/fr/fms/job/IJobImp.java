package fr.fms.job;

import java.util.ArrayList;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;

public class IJobImp implements IJob {
	private ArticleDao articleDao;
	private UserDao userDao;

	public IJobImp() {
		this.articleDao = new ArticleDao();
		this.userDao = new UserDao();
	}

	public Integer login(String email, String password) {
		return userDao.verifyIfUserExists(email, password);
	}

	public void createUser(User user) {
		userDao.create(user);
	}

	public void readUser(int id) {
		userDao.read(id);
	}

	public boolean updateUser(User user, int id) {
		return userDao.update(user, id);
	}

	public boolean deleteUser(int id) {
		return userDao.delete(id);
	}

	public void createArticle(Article article) {
		articleDao.create(article);
	}

	public void readArticle(int id) {
		articleDao.read(id);
	}

	public boolean updateArticle(Article article, int id) {
		return articleDao.update(article, id);
	}

	public boolean deleteArticle(int id) {
		return articleDao.delete(id);
	}

	public ArrayList<Article> readAllArticles() {
		return articleDao.readAll();
	}

}
