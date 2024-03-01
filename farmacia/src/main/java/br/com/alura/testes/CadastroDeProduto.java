package br.com.alura.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.DAO.FabricanteDAO;
import br.com.alura.DAO.ProdutoDAO;
import br.com.alura.modelo.Fabricante;
import br.com.alura.modelo.Produto;
import br.com.alura.util.JPAUtil;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		
		Produto p = produtoDAO.buscarPorId(1);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDAO.buscarPorNomeDoFabricante("EMS");
		todos.forEach(p2 -> System.out.println(p.getNome()));
	
		BigDecimal precoDoProduto = produtoDAO.buscarPrecoDoProdutoComNome("Dipirona");
		System.out.println("Preco do Produto: " +precoDoProduto);
	}

	private static void cadastrarProduto() {
		Fabricante ems = new Fabricante("EMS");
		Produto dipirona = new Produto("Dipirona", "Muito legal", 800, ems );
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		FabricanteDAO categoriaDao = new FabricanteDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(ems);
		produtoDAO.cadastrar(dipirona);
		
		em.getTransaction().commit();
		em.close();
	}

}
