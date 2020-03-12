package com.erp.crm.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.util.StringUtils;

import com.erp.config.util.ErpConfigure;
import com.erp.crm.model.PessoaFisica;
import com.erp.crm.model.PessoaFisica;
import com.erp.crm.model.PessoaFisica_;
import com.erp.crm.model.Pessoa_;
import com.erp.crm.model.enumerate.TipoPessoa;
import com.erp.crm.model.enumerate.TipoPessoaFisica;
import com.erp.crm.repository.filtro.PessoaFisicaFiltro;
import com.erp.crm.repository.queryinterface.PessoaFisicaRepositoryQuery;

public class PessoaFisicaRepositoryImpl implements PessoaFisicaRepositoryQuery {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<PessoaFisica> listPessoaFisicaWithPagination(PessoaFisicaFiltro pessoaFisicaFiltro, Pageable pageable) {
	
		List<PessoaFisica> listaPessoaFisica = new ArrayList<>();
		List<Predicate> lp = new ArrayList<>(); 
		TypedQuery<PessoaFisica> query = null;
		
		int totalRegistrosPorPaginas = pageable.getPageSize();
		int paginaAtual = pageable.getPageNumber();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPaginas;
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PessoaFisica> criteriaQuery = criteriaBuilder.createQuery(PessoaFisica.class);
		Root<PessoaFisica> rootFromPessoaFisica = criteriaQuery.from(PessoaFisica.class);

		Sort sort = pageable.getSort();
		if ( !sort.isUnsorted() ) {
			Sort.Order order = sort.iterator().next();
			String propriedade = order.getProperty();
			criteriaQuery.orderBy(order.isAscending()? criteriaBuilder.asc(rootFromPessoaFisica.get(propriedade)): criteriaBuilder.desc(rootFromPessoaFisica.get(propriedade)));
		}
	
		lp = filtros(pessoaFisicaFiltro, criteriaBuilder, rootFromPessoaFisica );
		
		if ( lp.size() != ErpConfigure.LISTA_VAZIA ) {
		    criteriaQuery.where(criteriaBuilder.and(lp.toArray(new Predicate[lp.size()])));
		    query = entityManager.createQuery(criteriaQuery);
		} else {
			query = entityManager.createQuery(criteriaQuery);
		}
		
	    query.setFirstResult(primeiroRegistro);
	    query.setMaxResults(totalRegistrosPorPaginas);

	    listaPessoaFisica = query.getResultList();
	
	    return new PageImpl<>(listaPessoaFisica, pageable, totalRegistro(lp) );
	}
	
	
    private Long totalRegistro(List<Predicate> lp ) {
		
		TypedQuery<Long> query = null;

		CriteriaBuilder criteriaBuider = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery =  criteriaBuider.createQuery(Long.class);
		Root<PessoaFisica> rootFrom = criteriaQuery.from(PessoaFisica.class);
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

    private List<Predicate> filtros(PessoaFisicaFiltro pessoaFiltro, CriteriaBuilder cb, Root<PessoaFisica> from) {
		List<Predicate> predicados = new ArrayList<>();
		if (!StringUtils.isEmpty(pessoaFiltro.getNome())){
			predicados.add( cb.like(cb.lower(from.get(PessoaFisica_.nome)), "%"+pessoaFiltro.getNome()+"%"));
		} 
		if (!StringUtils.isEmpty(pessoaFiltro.getCpf())){
			predicados.add( cb.like(cb.lower(from.get(PessoaFisica_.cpf)), "%"+pessoaFiltro.getCpf()+"%"));
		} 
		predicados.add(cb.equal(cb.lower(from.get(PessoaFisica_.TIPO_PESSOA)), TipoPessoaFisica.CLIENTE.getDescricao()));
		predicados.add(cb.equal(cb.lower(from.get(PessoaFisica_.TIPODE_PESSOA)), TipoPessoa.FISICA.getDescricao()));
		return predicados;
	}
    
	@Override
	public Optional<PessoaFisica> findPessoaFisicaByCpf(String cpf) {
		TypedQuery<PessoaFisica> query = entityManager.createQuery("SELECT pf FROM PessoaFisica pf WHERE pf.cpf  = :cpf", PessoaFisica.class);
	    return query.setParameter("cpf", cpf)
					.setMaxResults(1)
					.getResultList()
					.stream()
					.findFirst();
	}

}
