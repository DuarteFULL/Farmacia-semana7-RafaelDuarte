package br.com.alura.DAO;

import javax.persistence.EntityManager;

import br.com.alura.modelo.Fabricante;

public class FabricanteDAO {
	
	private EntityManager em;

	public FabricanteDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Fabricante fabricante) {
		this.em.persist(fabricante);
	}
	
	public void atualizar(Fabricante fabricante) {
		this.em.merge(fabricante);
	}
	
	public void remover(Fabricante fabricante) {
		fabricante = em.merge(fabricante);
		this.em.remove(fabricante);
	}

}
