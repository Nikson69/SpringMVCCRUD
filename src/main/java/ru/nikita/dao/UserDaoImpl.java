package ru.nikita.dao;


import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.nikita.model.User;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findById(long id) {
		Session session = sessionFactory.openSession();
		User user = session.get(User.class, id);
		session.close();
		return user;
	}

	@Override
	public void save(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx1 = session.beginTransaction();
		session.save(user);
		tx1.commit();
		session.close();
	}

	@Override
	public void update(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx1 = session.beginTransaction();
		session.update(user);
		tx1.commit();
		session.close();
	}

	@Override
	public void delete(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx1 = session.beginTransaction();
		session.delete(user);
		tx1.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		Session session = sessionFactory.openSession();
		CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
		criteriaQuery.from(User.class);
		List<User> users = session.createQuery(criteriaQuery).getResultList();
		session.close();
		return users;
	}

	@Override
	public User authorize(String login) {
		Session session = sessionFactory.openSession();
		Criteria userCriteria = session.createCriteria(User.class);
		userCriteria.add(Restrictions.eq("login", login));
		User user = (User) userCriteria.uniqueResult();
		session.close();
		return user;
	}

	@Override
	public boolean findByLogin(String login) {
		Session session = sessionFactory.openSession();
		Criteria userCriteria = session.createCriteria(User.class);
		userCriteria.add(Restrictions.eq("login", login));
		User user = (User) userCriteria.uniqueResult();
		session.close();
		return user != null;
	}
}
