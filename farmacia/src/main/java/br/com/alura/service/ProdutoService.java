package br.com.alura.service;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.DAO.FabricanteDAO;
import br.com.alura.DAO.ProdutoDAO;
import br.com.alura.modelo.Fabricante;
import br.com.alura.modelo.Produto;
import br.com.alura.util.JPAUtil;

public class ProdutoService {

    public void listarProdutos() {

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtos = new ProdutoDAO(em);

        List<Produto> todos = produtos.buscarTodos();

        todos.forEach(p2 -> System.out.println(p2.toString()));

    }

    public void cadastrarProduto(Fabricante fabricante, Produto produto){
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		FabricanteDAO fabricanteDao = new FabricanteDAO(em);
		
		em.getTransaction().begin();
		
		fabricanteDao.cadastrar(fabricante);
		produtoDao.cadastrar(produto);
		
		em.getTransaction().commit();
		em.close();
    }

    public void buscarProdutoPorNome(String pnome){
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtos = new ProdutoDAO(em);
		
		List<Produto> todos = produtos.buscarPorNome(pnome);

        todos.forEach(p2 -> System.out.println(p2.toString()));
    }

    public void deletarPorNome(String pnome){
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtos = new ProdutoDAO(em);
		
		List<Produto> todos = produtos.buscarPorNome(pnome);

        todos.forEach(p2 -> produtos.remover(p2));
    }

    


}
