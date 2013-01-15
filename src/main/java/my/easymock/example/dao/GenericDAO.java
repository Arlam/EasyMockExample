package my.easymock.example.dao;

import java.util.List;

public interface GenericDAO<T> {

	public Integer add(T entity);

	public T get(Integer id);

	public List<T> getAll();

	public void delete(T entity);

	public void update(T entity);

}
