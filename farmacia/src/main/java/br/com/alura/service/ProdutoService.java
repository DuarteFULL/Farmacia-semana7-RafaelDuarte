package br.com.alura.service;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.DAO.ProdutoDAO;
import br.com.alura.modelo.Produto;
import br.com.alura.util.JPAUtil;

public class ProdutoService {

public static void listarProdutos() {
        System.out.println("Produtos cadastrados:");

        EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtos = new ProdutoDAO(em);

        List<Produto> todos = produtos.buscarTodos();
     
		todos.forEach(p2 -> System.out.println(p2.toString()));
        
    }


}
