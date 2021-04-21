package com.excilys.formation.dao;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.formation.dto.dao.ComputerPersist;
import com.excilys.formation.mapper.DtoMapper;
import com.excilys.formation.model.Computer;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AjoutOrdinateurDao {

	static Logger logger = org.slf4j.LoggerFactory.getLogger(AjoutOrdinateurDao.class);

	private EntityManager entityManager;
	private DtoMapper dtoMapper;
	
	public AjoutOrdinateurDao(SessionFactory sessionFactory, DtoMapper dtoMapper) {
		this.entityManager = sessionFactory.createEntityManager();
		this.dtoMapper = dtoMapper;
	}
	
	public void computerInformations(Computer computer) {
		
		ComputerPersist computerPersist = dtoMapper.mapComputerToComputerPersist(computer);
		
		entityManager.getTransaction().begin();
		entityManager.persist(computerPersist);
		entityManager.getTransaction().commit();
		entityManager.clear();
	}
	
}
