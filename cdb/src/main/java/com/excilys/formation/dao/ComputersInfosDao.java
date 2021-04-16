package com.excilys.formation.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.formation.dto.dao.ComputerPersist;
import com.excilys.formation.dto.dao.QComputerPersist;
import com.excilys.formation.mapper.DtoMapper;
import com.excilys.formation.model.Computer;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ComputersInfosDao {
	
	private EntityManager entityManager;
	private DtoMapper dtoMapper;
	
	public ComputersInfosDao(SessionFactory sessionFactory, DtoMapper dtoMapper) {
		this.entityManager = sessionFactory.createEntityManager();
		this.dtoMapper = dtoMapper;
	}
	
	public List<Computer> computerInformations() {
		
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QComputerPersist computerPersist = QComputerPersist.computerPersist;
		
		List<ComputerPersist> computerList = queryFactory.selectFrom(computerPersist).orderBy(computerPersist.id.asc()).fetch();
		
		return dtoMapper.mapComputerPersistToComputerList(computerList);
		

	}

	public Integer computerInformationsNbEltsFiltre(String chaine) {
		
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QComputerPersist computerPersist = QComputerPersist.computerPersist;
		
		return (int) queryFactory.selectFrom(computerPersist).where(computerPersist.name.contains(chaine)).fetchCount();
	}
	
	public List<Computer> computerInformationsPageFilterSorted(int taillePage, int page, String chaine, String order, Order upOrDown) {
		
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QComputerPersist computerPersist = QComputerPersist.computerPersist;
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		OrderSpecifier<?> ordered = new OrderSpecifier(upOrDown, Expressions.path(Object.class, QComputerPersist.computerPersist, order));
		
		List<ComputerPersist> computerList = queryFactory.selectFrom(computerPersist).where(computerPersist.name.contains(chaine)).orderBy(ordered).limit(taillePage).offset(page*taillePage).fetch();
		
		return dtoMapper.mapComputerPersistToComputerList(computerList);
	}

	
	public Computer computerInformationsDetails(String nomMachine) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QComputerPersist computerPersist = QComputerPersist.computerPersist;
		
		ComputerPersist computerList = queryFactory.selectFrom(computerPersist).where(computerPersist.name.eq(nomMachine)).fetchOne();
		
		return dtoMapper.mapComputerPersistToComputer(computerList);
	}

	public Computer computerInformationsDetails(int id) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QComputerPersist computerPersist = QComputerPersist.computerPersist;
		
		ComputerPersist computerList = queryFactory.selectFrom(computerPersist).where(computerPersist.id.eq(id)).fetchOne();
		
		return dtoMapper.mapComputerPersistToComputer(computerList);
	}

}
