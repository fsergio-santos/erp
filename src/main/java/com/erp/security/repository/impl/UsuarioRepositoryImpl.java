package com.erp.security.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import com.erp.config.util.ErpConfigure;
import com.erp.crm.model.PessoaFisica;
import com.erp.security.model.Role;
import com.erp.security.model.RolePermissao;
import com.erp.security.model.Role_;
import com.erp.security.model.Usuario;
import com.erp.security.model.Usuario_;
import com.erp.security.repository.filtro.UsuarioFiltro;
import com.erp.security.repository.interfacequery.UsuarioRepositoryQuery;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	@Override
	public Page<Usuario> listUsuarioWithPagination(UsuarioFiltro usuarioFiltro, Pageable pageable){ 
		List<Usuario> listaUser = new ArrayList<>();
		List<Predicate> lp = new ArrayList<>(); 
		TypedQuery<Usuario> query = null;

		int totalRegistrosPorPaginas = pageable.getPageSize();
		int paginaAtual = pageable.getPageNumber();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPaginas;

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);

		Root<Usuario> rootFromUser = criteriaQuery.from(Usuario.class);

		Sort sort = pageable.getSort();
		if ( !sort.isUnsorted() ) {
			Sort.Order order = sort.iterator().next();
			String propriedade = order.getProperty();
			criteriaQuery.orderBy(order.isAscending()? criteriaBuilder.asc(rootFromUser.get(propriedade)): criteriaBuilder.desc(rootFromUser.get(propriedade)));
		}

		lp = filtros(usuarioFiltro, criteriaBuilder, rootFromUser );

		if ( lp.size() != ErpConfigure.LISTA_VAZIA ) {
			criteriaQuery.where(criteriaBuilder.and(lp.toArray(new Predicate[lp.size()])));
			query = entityManager.createQuery(criteriaQuery);
		} else {
			query = entityManager.createQuery(criteriaQuery);
		}

		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPaginas);

		listaUser = query.getResultList();

		return new PageImpl<>(listaUser, pageable, totalRegistro(lp) );
	}

	private Long totalRegistro(List<Predicate> lp) {

		TypedQuery<Long> query = null;
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Usuario> rootFrom = criteriaQuery.from(Usuario.class);
		criteriaQuery.select(criteriaBuilder.count(rootFrom));

		if (lp.size() != -1) {
			criteriaQuery.where(criteriaBuilder.and(lp.toArray(new Predicate[lp.size()])));
			query = entityManager.createQuery(criteriaQuery);
		} else {
			query = entityManager.createQuery(criteriaQuery);
		}
		Long result = query.getSingleResult();
		return result;
	}

	private List<Predicate> filtros(UsuarioFiltro usuarioFiltro, CriteriaBuilder cb, Root<Usuario> from) {
		List<Predicate> predicados = new ArrayList<>();
		if (!StringUtils.isEmpty(usuarioFiltro.getUser_name())) {
			predicados.add(cb.like(cb.lower(from.get(Usuario_.username)), "%" + usuarioFiltro.getUser_name() + "%"));
		}
		return predicados;
	}
	
	@Override
	public Usuario findUsuarioAtivoPeloEmail(String email) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> rootFrom = criteriaQuery.from(Usuario.class);
		Predicate predicates = criteriaBuilder.conjunction();
		predicates = criteriaBuilder.and(criteriaBuilder.equal(rootFrom.get(Usuario_.email), email),
				     criteriaBuilder.equal(rootFrom.get(Usuario_.ativo), true));
		criteriaQuery.select(rootFrom);
		criteriaQuery.where(predicates);
		TypedQuery<Usuario> query = entityManager.createQuery(criteriaQuery);
		try {
			return query.getSingleResult();
		} catch(NoResultException nre) {
		}
		return null;
	}
	
	@Override
	public Usuario findByIdUsuarioRoleAndPermissions(Long id) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> rootFromUsuario = criteriaQuery.from(Usuario.class);
		criteriaQuery.select(rootFromUsuario);
		Join<Usuario, Role> joinUsuarioRole = rootFromUsuario.join(Usuario_.roles);
		Join<Role, RolePermissao> joinRolePermissao = joinUsuarioRole.join(Role_.rolePermissao);
		Predicate predicates = criteriaBuilder.conjunction();
		predicates = criteriaBuilder.and(criteriaBuilder.equal(rootFromUsuario.get(Usuario_.id), id));
		criteriaQuery.where(predicates);
		TypedQuery<Usuario> query = entityManager.createQuery(criteriaQuery);
        try {
        	Usuario usuario = query.getSingleResult(); 
        	return usuario;	
        } catch (NoResultException e) {
		}
 		return null;
	}
	
	
	@Override
	public Optional<Usuario> findUsuarioByEmail(String email) {
		TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.email =:email", Usuario.class);
	    return query.setParameter("email",email)
					.setMaxResults(1)
					.getResultList()
					.stream()
					.findFirst();
	}
	
	
	@Override
	public Optional<Usuario> findUsuarioByName(String name) {
		TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.username =:name", Usuario.class);
	    return query.setParameter("name",name)
					.setMaxResults(1)
					.getResultList()
					.stream()
					.findFirst();
	}
	

}
