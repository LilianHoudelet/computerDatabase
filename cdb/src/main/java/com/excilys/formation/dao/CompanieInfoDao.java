package com.excilys.formation.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.excilys.formation.dto.dao.CompanyPersist;
import com.excilys.formation.dto.dao.QCompanyPersist;
import com.excilys.formation.mapper.DtoMapper;
import com.excilys.formation.model.Company;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Component
public class CompanieInfoDao {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(CompanieInfoDao.class);
	
	private EntityManager entityManager;
	private DtoMapper dtoMapper;
	
	public CompanieInfoDao(SessionFactory sessionFactory, DtoMapper dtoMapper) {
		this.entityManager = sessionFactory.createEntityManager();
		this.dtoMapper = dtoMapper;
	}
	
	public List<Company> companyInformations() {
		logger.debug("Récupération de la liste des constructeurs");
		
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QCompanyPersist companyPersist = QCompanyPersist.companyPersist;
		List<CompanyPersist> companyList = queryFactory.selectFrom(companyPersist).orderBy(companyPersist.id.asc()).fetch();
		
		return dtoMapper.mapCompanyPersistToCompanyList(companyList);
	}
	
	public Company companyInformationsId(String name) {
		logger.debug("Récupération de l'id correspondant au nom du constructeur : " + name);
		
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QCompanyPersist companyPersist = QCompanyPersist.companyPersist;
		CompanyPersist company = queryFactory.selectFrom(companyPersist).where(companyPersist.name.eq(name)).fetchOne();
		
		return dtoMapper.mapCompanyPersistToCompany(company);
	}

}
