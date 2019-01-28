package br.com.newprogram.domain.acesso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.newprogram.domain.cliente.Cliente;

@Stateless
public class AcessoRepository {

	@PersistenceContext
	private EntityManager em;
	
	public Acesso findUltimoAcesso(Cliente cliente) {
		try {
		return em.createQuery("SELECT c FROM Acesso c WHERE c.cliente.id = :id ORDER BY c.id DESC", Acesso.class)
				.setParameter("id", cliente.getId())
				.setMaxResults(1)
				.getSingleResult();
		
		} catch (NoResultException e) {
			return null;
		}
	}	
	
	public void store(Acesso acesso) {
		em.persist(acesso);
	}

}
