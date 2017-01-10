package springTutorial.dao.hibernateDaos;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED)
@SuppressWarnings("unchecked")
public abstract class GenericHibernateDAO<E, K extends Serializable> {

	private SessionFactory sessionFactory;
	protected Class<? extends E> daoType;

	public GenericHibernateDAO() {
		daoType = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void add(E entity) {
		currentSession().save(entity);
	}
	
	public void update(E entity) {
		currentSession().saveOrUpdate(entity);
	}
	
	public void remove(E entity) {
		currentSession().delete(entity);
	}

	public E find(K key) {
		return (E) currentSession().get(daoType, key);
	}

	public List<E> list() {
		return (List<E>) currentSession().createCriteria(daoType).list();
	}
}
