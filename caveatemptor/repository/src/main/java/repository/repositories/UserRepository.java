package repository.repositories;

import java.util.Collection;
import java.util.Map.Entry;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import repository.entities.User;
import repository.queries.INamedQueryData;

@Stateless
@Remote(IUserRepository.class)
public class UserRepository implements IUserRepository {

	private EntityManager entityManager;

	public UserRepository() {
	}

	@Override
	public void add(User user, EntityManager entityManager) {
		setEntityManager(entityManager);
		if (user.getId() <= 0) {
			create(user);
		} else {
			update(user);
		}
	}

	@Override
	public void remove(User user, EntityManager entityManager) {
		setEntityManager(entityManager);
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
	}

	@Override
	public Collection<User> getCollection(INamedQueryData namedQueryData,
			EntityManager entityManager) {
		setEntityManager(entityManager);
		try {
			return buildNamedQuery(namedQueryData).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public User getSingleEntityByQueryData(INamedQueryData namedQueryData,
			EntityManager entityManager) {
		setEntityManager(entityManager);
		try {
			return (User) buildNamedQuery(namedQueryData).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public User getSingleEntityById(int id, EntityManager entityManager) {
		setEntityManager(entityManager);
		return entityManager.find(User.class, id);
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private void create(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
	}

	private void update(User user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
	}

	public Query buildNamedQuery(final INamedQueryData namedQueryData) {
		final Query query = entityManager.createNamedQuery(namedQueryData
				.getNamedQuery());

		for (Entry<String, String> entry : namedQueryData.getParameters()
				.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query;
	}

}
