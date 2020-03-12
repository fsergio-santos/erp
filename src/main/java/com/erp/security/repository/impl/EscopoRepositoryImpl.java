package com.erp.security.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.erp.config.util.ErpConfigure;
import com.erp.crm.model.PessoaFisica;
import com.erp.security.model.Escopo;
import com.erp.security.repository.interfacequery.EscopoRepositoryQuery;

public class EscopoRepositoryImpl implements EscopoRepositoryQuery{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<Escopo> listEscopoPagination(Pageable pageable) {
		List<Escopo> listaEscopo = new ArrayList<>();
		List<Predicate> lp = new ArrayList<>(); 
		TypedQuery<Escopo> query = null;
		
		int totalRegistrosPorPaginas = pageable.getPageSize();
		int paginaAtual = pageable.getPageNumber();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPaginas;
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Escopo> criteriaQuery = criteriaBuilder.createQuery(Escopo.class);
		Root<Escopo> rootFromEscopo = criteriaQuery.from(Escopo.class);

		Sort sort = pageable.getSort();
		if ( !sort.isUnsorted() ) {
			Sort.Order order = sort.iterator().next();
			String propriedade = order.getProperty();
			criteriaQuery.orderBy(order.isAscending()? criteriaBuilder.asc(rootFromEscopo.get(propriedade)): criteriaBuilder.desc(rootFromEscopo.get(propriedade)));
		}
	
		query = entityManager.createQuery(criteriaQuery);
	    query.setFirstResult(primeiroRegistro);
	    query.setMaxResults(totalRegistrosPorPaginas);

	    listaEscopo = query.getResultList();
	
	    return new PageImpl<>(listaEscopo, pageable, totalRegistro() );

	}
	
	private Long totalRegistro() {
		TypedQuery<Long> query = null;
		CriteriaBuilder criteriaBuider = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery =  criteriaBuider.createQuery(Long.class);
		Root<Escopo> rootFrom = criteriaQuery.from(Escopo.class);
		criteriaQuery.select(criteriaBuider.count(rootFrom));
		query = entityManager.createQuery(criteriaQuery);
		Long result = query.getSingleResult();
		return result;
	}


}
