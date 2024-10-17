package fr.fms.dao;

import java.util.ArrayList;

public interface Dao<T> {

	public void create(T obj);

	public T read(int id);

	public boolean update(T obj, int id);

	public boolean delete(int id);

	public ArrayList<T> readAll();

}
