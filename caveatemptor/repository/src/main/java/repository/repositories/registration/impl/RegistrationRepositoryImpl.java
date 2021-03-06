package repository.repositories.registration.impl;

import java.util.Collection;
import java.util.Map.Entry;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import exceptions.RegistrationException;
import exceptions.messages.ExceptionMessages;
import repository.entities.Registration;
import repository.queries.NamedQueryData;
import repository.repositories.registration.RegistrationRepository;

@Stateless
@Remote(RegistrationRepository.class)
public class RegistrationRepositoryImpl implements RegistrationRepository {

	private EntityManager entityManager;

	public RegistrationRepositoryImpl() {
	}

	@Override
	public void add(Registration registration, EntityManager entityManager) {

		setEntityManager(entityManager);

		if (registration.getId() == null) {
			create(registration);
		} else {
			update(registration);
		}
	}

	private void create(Registration registration) {
		entityManager.persist(registration);
	}

	private void update(Registration registration) {
		entityManager.merge(registration);
	}

	@Override
	public void remove(Registration registration, EntityManager entityManager) {

		setEntityManager(entityManager);
		this.entityManager
				.remove(entityManager.contains(registration) ? registration
						: entityManager.merge(registration));
	}

	@Override
	public Collection<Registration> getCollection(
			NamedQueryData namedQueryData, EntityManager entityManager)
			throws RegistrationException {

		setEntityManager(entityManager);

		try {
			return buildNamedQuery(namedQueryData).getResultList();
		} catch (PersistenceException e) {
			throw new RegistrationException();
		}
	}

	@Override
	public Registration getSingleEntityByQueryData(
			NamedQueryData namedQueryData, EntityManager entityManager)
			throws RegistrationException {

		setEntityManager(entityManager);

		try {
			return (Registration) buildNamedQuery(namedQueryData)
					.getSingleResult();
		} catch (PersistenceException e) {
			throw new RegistrationException(
					ExceptionMessages.REGISTRATION_NOT_FOUND.getDetails());
		}
	}

	@Override
	public Registration getSingleEntityById(Long id, EntityManager entityManager)
			throws RegistrationException {

		setEntityManager(entityManager);

		try {
			return this.entityManager.find(Registration.class, id);
		} catch (PersistenceException e) {
			throw new RegistrationException(
					ExceptionMessages.REGISTRATION_NOT_FOUND.getDetails());
		}
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private Query buildNamedQuery(final NamedQueryData namedQueryData) {

		final Query query = entityManager.createNamedQuery(namedQueryData
				.getNamedQuery());

		for (Entry<String, Object> entry : namedQueryData.getParameters()
				.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query;
	}

}
