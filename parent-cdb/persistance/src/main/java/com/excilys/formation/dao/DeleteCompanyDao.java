package com.excilys.formation.dao;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.dto.dao.QCompanyPersist;
import com.excilys.formation.dto.dao.QComputerPersist;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DeleteCompanyDao {
	
	public final static String DELETE_COMPANY = "DELETE FROM company WHERE id = ? ";
	
	public final static String DELETE_COMPUTER = "DELETE FROM computer WHERE company_id = ?";
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(DeleteCompanyDao.class);

	private EntityManager entityManager;
	
	public DeleteCompanyDao(SessionFactory sessionFactory) {
		this.entityManager = sessionFactory.createEntityManager();
	}
	
	@Transactional
	public void deleteCompany(int companyId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QComputerPersist computerPersist = QComputerPersist.computerPersist;
		QCompanyPersist companyPersist = QCompanyPersist.companyPersist;
		
		entityManager.getTransaction().begin();
		
		queryFactory
		.delete(computerPersist).where(computerPersist.companyId.eq(companyId))
		.execute();
		
		queryFactory
		.delete(computerPersist).where(companyPersist.id.eq(companyId))
		.execute();

		entityManager.getTransaction().commit();
	}
}
