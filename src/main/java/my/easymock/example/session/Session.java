package my.easymock.example.session;

import java.io.Serializable;

public interface Session {
	public Serializable save(Object entity);

	public Object get(Class clazz, Serializable id);

	public Query createQuery(String queryString);

	public void delete(Object object);

	public void update(Object object);
}
