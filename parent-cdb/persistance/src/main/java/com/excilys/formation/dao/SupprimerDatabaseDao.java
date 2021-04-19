package com.excilys.formation.dao;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.formation.dto.dao.QComputerPersist;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SupprimerDatabaseDao {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(SupprimerDatabaseDao.class);

	private EntityManager entityManager;
	
	public SupprimerDatabaseDao(SessionFactory sessionFactory) {
		this.entityManager = sessionFactory.createEntityManager();
	}
	
	public void deleteComputer(String name) {		

		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QComputerPersist computerPersist = QComputerPersist.computerPersist;
		
		entityManager.getTransaction().begin();
		
		queryFactory
		.delete(computerPersist).where(computerPersist.name.eq(name))
		.execute();
		
		entityManager.getTransaction().commit();
	}
	
	public void deleteComputer(int id) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QComputerPersist computerPersist = QComputerPersist.computerPersist;
		
		entityManager.getTransaction().begin();
		
		queryFactory
		.delete(computerPersist).where(computerPersist.id.eq(id))
		.execute();
		
		entityManager.getTransaction().commit();
	}
}
