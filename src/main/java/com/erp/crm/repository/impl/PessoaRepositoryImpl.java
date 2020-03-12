package com.erp.crm.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
import org.springframework.util.StringUtils;

import com.erp.config.util.ErpConfigure;
import com.erp.crm.model.Pessoa;
import com.erp.crm.model.PessoaFisica;
import com.erp.crm.model.Pessoa_;
import com.erp.crm.model.enumerate.TipoPessoa;
import com.erp.crm.model.enumerate.TipoPessoaFisica;
import com.erp.crm.repository.filtro.PaisFiltro;
import com.erp.crm.repository.filtro.PessoaFiltro;
import com.erp.crm.repository.queryinterface.PessoaRepositoryQuery;


public class PessoaRepositoryImpl implements PessoaRepositoryQuery{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Pessoa> listPessoaWithPagination(PessoaFiltro pessoaFiltro, Pageable pageable) {
	
		List<Pessoa> listaPessoa = new ArrayList<>();
		List<Predicate> lp = new ArrayList<>(); 
		TypedQuery<Pessoa> query = null;
		
		int totalRegistrosPorPaginas = pageable.getPageSize();
		int paginaAtual = pageable.getPageNumber();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPaginas;
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteriaQuery = criteriaBuilder.createQuery(Pessoa.class);
		Root<Pessoa> rootFromPessoa = criteriaQuery.from(Pessoa.class);

		Sort sort = pageable.getSort();
		if ( !sort.isUnsorted() ) {
			Sort.Order order = sort.iterator().next();
			String propriedade = order.getProperty();
			criteriaQuery.orderBy(order.isAscending()? criteriaBuilder.asc(rootFromPessoa.get(propriedade)): criteriaBuilder.desc(rootFromPessoa.get(propriedade)));
		}
	
		lp = filtros(pessoaFiltro, criteriaBuilder, rootFromPessoa );
		
		if ( lp.size() != ErpConfigure.LISTA_VAZIA ) {
		    criteriaQuery.where(criteriaBuilder.and(lp.toArray(new Predicate[lp.size()])));
		    query = entityManager.createQuery(criteriaQuery);
		} else {
			query = entityManager.createQuery(criteriaQuery);
		}
		
	    query.setFirstResult(primeiroRegistro);
	    query.setMaxResults(totalRegistrosPorPaginas);

	    listaPessoa = query.getResultList();
	
	    return new PageImpl<>(listaPessoa, pageable, totalRegistro(lp) );
	}
	
	
    private Long totalRegistro(List<Predicate> lp ) {
		
		TypedQuery<Long> query = null;

		CriteriaBuilder criteriaBuider = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery =  criteriaBuider.createQuery(Long.class);
		Root<Pessoa> rootFrom = criteriaQuery.from(Pessoa.class);
		criteriaQuery.select(criteriaBuider.count(rootFrom));
		if ( lp.size() != ErpConfigure.LISTA_VAZIA ) {
		    criteriaQuery.where(criteriaBuider.and(lp.toArray(new Predicate[lp.size()])));
		    query = entityManager.createQuery(criteriaQuery);
		} else {
			query = entityManager.createQuery(criteriaQuery);
		}
		Long result = query.getSingleResult();
		return result;
	}

    private List<Predicate> filtros(PessoaFiltro pessoaFiltro, CriteriaBuilder cb, Root<Pessoa> from) {
		List<Predicate> predicados = new ArrayList<>();
		if (!StringUtils.isEmpty(pessoaFiltro.getNome())){
			predicados.add( cb.like(cb.lower(from.get(Pessoa_.nome)), "%"+pessoaFiltro.getNome()+"%"));
		}
		
		predicados.add(cb.equal(cb.lower(from.get(Pessoa_.TIPO_PESSOA)), TipoPessoaFisica.CLIENTE.getDescricao()));
		predicados.add(cb.equal(cb.lower(from.get(Pessoa_.TIPODE_PESSOA)), TipoPessoa.FISICA.getDescricao()));
		return predicados;
	}

	
	
}
