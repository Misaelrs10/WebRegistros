package br.com.newprogram.domain.cliente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.com.newprogram.application.util.StringUtil;
import br.com.newprogram.domain.acesso.Acesso;
import br.com.newprogram.domain.cliente.Cliente.Situacao;

import java.util.List;

@Stateless
public class ClienteRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public void store(Cliente cliente) {
		em.persist(cliente);
	}
	
	public void update(Cliente cliente) {
		em.merge(cliente);
	}
	
	public Cliente findById(String id) {
		return em.find(Cliente.class, id);
	}
	
	public Cliente findByCpf(Integer cpf) {
		try {
		return em.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :cpf", Cliente.class)
				.setParameter("cpf", cpf)
				.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}
	
	public void delete(String id) {
		Cliente cliente = findById(id);
		
		if (cliente != null) {
			em.remove(cliente);
		}
	}
	
	public String getMaxIdAno() {
		return em.createQuery("SELECT MAX(c.id) FROM Cliente c WHERE c.id LIKE :ano", String.class)
				.setParameter("ano", Year.now() + "%")
				.getSingleResult();
	}
	
	public List<Cliente> listClientes(String id, String nome, Integer cpf, Integer celular){
		StringBuilder jpql = new StringBuilder("SELECT c FROM Cliente c WHERE ");
		
		if (!StringUtil.isEmpty(id)) {
			jpql.append("c.id = :id AND ");
		}
		
		if(!StringUtil.isEmpty(nome)) {
			jpql.append("c.nome LIKE :nome AND ");
		}
		
		if (cpf != null) {
			jpql.append("c.cpf = :cpf AND ");
		}
		
		if (celular != null) {
			jpql.append("c.celular = :celular AND ");
		}
		
		jpql.append("1 = 1");
		TypedQuery<Cliente> q = em.createQuery(jpql.toString(),Cliente.class);
	
		if (!StringUtil.isEmpty(id)) {
			q.setParameter("id", id);
		}
		
		if(!StringUtil.isEmpty(nome)) {
			q.setParameter("nome", "%" + nome + "%");
		}
		
		if (cpf != null) {
			q.setParameter("cpf", cpf);
		}
		
		if (celular != null) {
			q.setParameter("celular", celular);
		}
		
		return q.getResultList();
	}
	
	public List<Cliente> listSituacoesClientes(Situacao situacao){
		return em.createQuery("SELECT c FROM Cliente c WHERE c.situacao = :situacao ORDER BY c.nome", Cliente.class)
				.setParameter("situacao", situacao)
				.getResultList();
	}
	
	public List<Acesso> listAcessosClientes(String id, LocalDate dataInicial, LocalDate dataFinal){
		StringBuilder jpql = new StringBuilder("SELECT c FROM Acesso c WHERE ");
		
		if(StringUtil.isEmpty(id)) {
			jpql.append("c.cliente.id = id AND ");
		}
		
		if(dataInicial != null) {
			jpql.append("c.entrada >= :entradaInicio AND ");
		}
		if(dataFinal != null) {
			jpql.append("c.saida <= :saidaFim AND ");
		}	
		jpql.append("1 = 1 ORDER BY c.entrada");
		
		TypedQuery<Acesso> q = em.createQuery(jpql.toString(), Acesso.class);
		
		if(StringUtil.isEmpty(id)) {
			q.setParameter("id", id);
		}
		
		if(dataInicial != null) {
			LocalDateTime ldt = LocalDateTime.of(dataInicial, LocalTime.of(0, 0, 0));
			q.setParameter("entradaInicio", ldt);
		}
		if(dataFinal != null) {
			LocalDateTime ldt = LocalDateTime.of(dataFinal, LocalTime.of(23, 59, 59));
			q.setParameter("saidaFim", ldt);
		}
		
		return q.getResultList();
	}	
}

