package my.easymock.example.dao;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import my.easymock.example.dao.impl.GenericDAOImpl;
import my.easymock.example.pojo.Person;
import my.easymock.example.session.Query;
import my.easymock.example.session.Session;
import my.easymock.example.session.SessionFactory;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Test;

public class GenericDAOImplTests {
	private static final Class<Person> CLAZZ = Person.class;
	private IMocksControl ctrl;
	private SessionFactory sessionFactory;
	private Session session;
	private GenericDAO<Person> personDAO;
	private Person entity = new Person("Name", 42);
	private Integer id = new Integer(1);

	private void initMock() {
		ctrl = EasyMock.createControl();
		sessionFactory = ctrl.createMock(SessionFactory.class);
		session = ctrl.createMock(Session.class);
		personDAO = new GenericDAOImpl<Person>(CLAZZ, sessionFactory);
	}

	@Test
	public void testAdd() {
		initMock();
		ctrl.reset();
		expect(sessionFactory.getCurrentSession()).andReturn(session);
		expect(session.save(entity)).andReturn(id);
		ctrl.replay();
		assertEquals(new Integer(1), personDAO.add(entity));
		ctrl.verify();
	}

	@Test
	public void testGet() {
		initMock();
		ctrl.reset();
		expect(sessionFactory.getCurrentSession()).andReturn(session);
		expect(session.get(CLAZZ, id)).andReturn(entity);
		ctrl.replay();
		assertEquals(entity, personDAO.get(id));
		ctrl.verify();
	}

	@Test
	public void testGetAll() {
		List<Person> entities = new ArrayList<Person>();
		String sql = "from my.easymock.example.pojo.Person";
		initMock();
		Query query = ctrl.createMock(Query.class);
		ctrl.reset();
		expect(sessionFactory.getCurrentSession()).andReturn(session);
		expect(session.createQuery(sql)).andReturn(query);
		expect(query.list()).andReturn(entities);
		ctrl.replay();
		assertEquals(entities, personDAO.getAll());
		ctrl.verify();
	}

	@Test
	public void testDelete() {
		initMock();
		ctrl.reset();
		expect(sessionFactory.getCurrentSession()).andReturn(session);
		session.delete(entity);
		expectLastCall();
		ctrl.replay();
		personDAO.delete(entity);
		ctrl.verify();
	}

	@Test
	public void testUpdate() {
		initMock();
		ctrl.reset();
		expect(sessionFactory.getCurrentSession()).andReturn(session);
		session.update(entity);
		expectLastCall();
		ctrl.replay();
		personDAO.update(entity);
		ctrl.verify();
	}

}
