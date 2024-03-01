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

        if(todos.size() != 0){
            todos.forEach(p2 -> System.out.println(p2.toString()));
        } else {
            System.out.println("\nNão existe nenhum produto cadastrado.");
        }

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

        if(todos.size() != 0){
            todos.forEach(p2 -> System.out.println(p2.toString()));
        } else {
            System.out.println("\nProduto com o nome '"+pnome+"' não encontrado, tente novamente.");
        }
    }

    public void deletarPorNome(String pnome){
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		
		List<Produto> listTodos = produtoDAO.buscarPorNome(pnome);

        if(listTodos.size() != 0){
            System.out.println("Existe alguem para ser deletado e é: "+listTodos.size());

            em.getTransaction().begin();

            listTodos.forEach(p2 -> produtoDAO.remover(p2));

            em.getTransaction().commit();
		    em.close();
            System.out.println("\nProduto deletado com sucesso!");
        } else {
            System.out.println("Produto com o nome '"+pnome+"' não encontrado, tente novamente.");
        }
        
    }

    public void alterarValor(String pnome, float pvalor){
        EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		
		List<Produto> listTodos = produtoDAO.buscarPorNome(pnome);

        if(listTodos.size() != 0){
            em.getTransaction().begin();

            listTodos.forEach(p2 -> {
                p2.setPreco(pvalor);
                produtoDAO.atualizar(p2);
            });

            em.getTransaction().commit();
		    em.close();
            System.out.println("\nValor do produto atualizado com sucesso!");
        } else {
            System.out.println("Produto com o nome '"+pnome+"' não encontrado, tente novamente.");
        }
    }

    public void alterarFabricante(String pnome, Fabricante pfabricante){
        EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		
		List<Produto> listTodos = produtoDAO.buscarPorNome(pnome);

        if(listTodos.size() != 0){
            FabricanteDAO fabricanteDao = new FabricanteDAO(em);
		
		    em.getTransaction().begin();
		
		    fabricanteDao.cadastrar(pfabricante);

            listTodos.forEach(p2 -> {
                p2.setFabricante(pfabricante);
                produtoDAO.atualizar(p2);
            });

            em.getTransaction().commit();
		    em.close();
            System.out.println("\nFabricante do produto atualizado com sucesso!");
        } else {
            System.out.println("Produto com o nome '"+pnome+"' não encontrado, tente novamente.");
        }
    }

    public void alterarDescricao(String pnome, String pdescricao){
        EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		
		List<Produto> listTodos = produtoDAO.buscarPorNome(pnome);

        if(listTodos.size() != 0){
            em.getTransaction().begin();

            listTodos.forEach(p2 -> {
                p2.setDescricao(pdescricao);
                produtoDAO.atualizar(p2);
            });

            em.getTransaction().commit();
		    em.close();
            System.out.println("\nDescrição do produto atualizado com sucesso!");
        } else {
            System.out.println("Produto com o nome '"+pnome+"' não encontrado, tente novamente.");
        }
    }

    //FIM DA APLICAÇÂO

}
