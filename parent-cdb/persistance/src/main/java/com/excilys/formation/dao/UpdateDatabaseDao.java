package com.excilys.formation.dao;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.formation.model.Computer;
import com.excilys.formation.dto.dao.QComputerPersist;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UpdateDatabaseDao {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(UpdateDatabaseDao.class);
	
	private EntityManager entityManager;
	
	public UpdateDatabaseDao(SessionFactory sessionFactory) {
		this.entityManager = sessionFactory.createEntityManager();
	}

	public void updateComputerInformations(Computer computer) {
		logger.debug("Mise à jour de l'élément " + computer.getName() + " dans la base de données");
		
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QComputerPersist computerPersist = QComputerPersist.computerPersist;
		
		entityManager.getTransaction().begin();
		
		queryFactory.update(computerPersist).where(computerPersist.id.eq(computer.getId()))
		.set(computerPersist.name, computer.getName())
		.set(computerPersist.introduced, computer.getDateSortie())
		.set(computerPersist.discontinued, computer.getDateRetrait())
		.set(computerPersist.companyId, computer.getCompany().getId() != 0 ? computer.getCompany().getId() : null)
		.execute();
		
		entityManager.getTransaction().commit();

		
	}
}
