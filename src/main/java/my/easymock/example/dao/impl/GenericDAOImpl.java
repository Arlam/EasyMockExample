package my.easymock.example.dao.impl;

import java.io.Serializable;
import java.util.List;

import my.easymock.example.dao.GenericDAO;
import my.easymock.example.session.Query;
import my.easymock.example.session.SessionFactory;

public class GenericDAOImpl<T extends Serializable> implements GenericDAO<T> {
	private final SessionFactory sessionFactory;
	private final Class<T> type;

	public GenericDAOImpl(Class<T> type, SessionFactory sessionFactory) {
		this.type = type;
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer add(T entity) {
		Integer id = null;
		id = (Integer) sessionFactory.getCurrentSession().save(entity);
		return id;
	}

	@Override
	public T get(Integer id) {
		@SuppressWarnings("unchecked")
		T entity = (T) sessionFactory.getCurrentSession().get(type, id);
		return entity;
	}

	@Override
	public List<T> getAll() {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from " + type.getName());
		@SuppressWarnings("unchecked")
		List<T> entities = query.list();
		return entities;
	}

	@Override
	public void delete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public void update(T entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

}
