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
import com.erp.crm.model.PessoaFisica_;
import com.erp.crm.model.PessoaJuridica;
import com.erp.crm.model.PessoaJuridica_;
import com.erp.crm.model.enumerate.TipoPessoa;
import com.erp.crm.model.enumerate.TipoPessoaFisica;
import com.erp.crm.repository.filtro.PessoaFisicaFiltro;
import com.erp.crm.repository.filtro.PessoaJuridicaFiltro;
import com.erp.crm.repository.queryinterface.PessoaJuridicaRepositoryQuery;

public class PessoaJuridicaRepositoryImpl implements PessoaJuridicaRepositoryQuery {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<PessoaJuridica> listPessoaJuridicaWithPagination(PessoaJuridicaFiltro pessoaJuridicaFiltro,Pageable pageable) {
		List<PessoaJuridica> listaPessoaJuridica = new ArrayList<>();
		List<Predicate> lp = new ArrayList<>(); 
		TypedQuery<PessoaJuridica> query = null;
		
		int totalRegistrosPorPaginas = pageable.getPageSize();
		int paginaAtual = pageable.getPageNumber();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPaginas;
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PessoaJuridica> criteriaQuery = criteriaBuilder.createQuery(PessoaJuridica.class);
		Root<PessoaJuridica> rootFromPessoaJuridica = criteriaQuery.from(PessoaJuridica.class);

		Sort sort = pageable.getSort();
		if ( !sort.isUnsorted() ) {
			Sort.Order order = sort.iterator().next();
			String propriedade = order.getProperty();
			criteriaQuery.orderBy(order.isAscending()? criteriaBuilder.asc(rootFromPessoaJuridica.get(propriedade)): criteriaBuilder.desc(rootFromPessoaJuridica.get(propriedade)));
		}
	
		lp = filtros(pessoaJuridicaFiltro, criteriaBuilder, rootFromPessoaJuridica );
		
		if ( lp.size() != ErpConfigure.LISTA_VAZIA ) {
		    criteriaQuery.where(criteriaBuilder.and(lp.toArray(new Predicate[lp.size()])));
		    query = entityManager.createQuery(criteriaQuery);
		} else {
			query = entityManager.createQuery(criteriaQuery);
		}
		
	    query.setFirstResult(primeiroRegistro);
	    query.setMaxResults(totalRegistrosPorPaginas);

	    listaPessoaJuridica = query.getResultList();
	
	    return new PageImpl<>(listaPessoaJuridica, pageable, totalRegistro(lp) );
	}
	
	
	private Long totalRegistro(List<Predicate> lp ) {
		
		TypedQuery<Long> query = null;

		CriteriaBuilder criteriaBuider = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery =  criteriaBuider.createQuery(Long.class);
		Root<PessoaJuridica> rootFrom = criteriaQuery.from(PessoaJuridica.class);
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

    private List<Predicate> filtros(PessoaJuridicaFiltro pessoaFiltro, CriteriaBuilder cb, Root<PessoaJuridica> from) {
		List<Predicate> predicados = new ArrayList<>();
		if (!StringUtils.isEmpty(pessoaFiltro.getNome())){
			predicados.add( cb.like(cb.lower(from.get(PessoaJuridica_.nome)), "%"+pessoaFiltro.getNome()+"%"));
		} 
		if (!StringUtils.isEmpty(pessoaFiltro.getNome())){
			predicados.add( cb.like(cb.lower(from.get(PessoaJuridica_.cnpj)), "%"+pessoaFiltro.getCnpj()+"%"));
		}
		predicados.add(cb.equal(cb.lower(from.get(PessoaFisica_.TIPO_PESSOA)), TipoPessoaFisica.CLIENTE.getDescricao()));
		predicados.add(cb.equal(cb.lower(from.get(PessoaFisica_.TIPODE_PESSOA)), TipoPessoa.JURIDICA.getDescricao()));
		return predicados;
	}

	@Override
	public Optional<PessoaJuridica> findPessoaJuridicaByCnpj(String cnpj) {
		TypedQuery<PessoaJuridica> query = entityManager.createQuery("SELECT pj FROM PessoaJuridica pj WHERE pj.cnpj = :cnpj", PessoaJuridica.class);
	    return query.setParameter("cnpj", cnpj)
					.setMaxResults(1)
					.getResultList()
					.stream()
					.findFirst();
	}

}
