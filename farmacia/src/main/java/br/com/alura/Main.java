package br.com.alura;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.alura.DAO.FabricanteDAO;
import br.com.alura.DAO.ProdutoDAO;
import br.com.alura.modelo.Fabricante;
import br.com.alura.modelo.Produto;
import br.com.alura.modelo.RegraDenegocioException;
import br.com.alura.service.ProdutoService;
import br.com.alura.util.JPAUtil;

public class Main {
    //private static ProdutoService service = new ProdutoService();
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        var opcao = Menu();

        while (opcao != 8) {
            try {
                switch (opcao) {
                    case 1:
                        listarProdutos();
                        break;
                    case 2:
                        cadastrarProduto();
                        break;
                    case 3:
                        deletarProduto();
                        break;
                    case 4:
                        consultarProduto();
                        break;
                    case 5:
                        alterarValorProduto();
                        break;
                    case 6:
                        alterarFabricanteProduto();
                    case 7:
                        alterarDescricaoProduto();
                }
            } catch (RegraDenegocioException e) {
                System.out.println("Erro: " +e.getMessage());
                System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
                teclado.next();
            }

            opcao = Menu();

        }

        System.out.println("Finalizando a aplicação.");
    }

    private static int Menu() {
        System.out.println("""
                FARMÁCIA - ESCOLHA UMA OPÇÃO:
                1 - Listar produtos
                2 - Cadastrar produto
                3 - Deletar produto
                4 - Consultar produto
                5 - Alterar valor do produto
                6 - Alterar fabricante do produto 
                7 - Alterar descrição do produto
                8 - Sair
                """);
        return teclado.nextInt();
    }

    
    public static void cadastrarProduto() {
		System.out.println("Digite o nome do produto:");
        var nomeDoProduto = teclado.next();

        System.out.println("Digite a descrição do produto:");
        var descricaoDoProduto = teclado.next();

        System.out.println("Digite o valor do produto:");
        var valorDoProduto = teclado.nextFloat();        

        System.out.println("Digite o fabricante do produto:");
        var fabricanteDoProduto = teclado.next();        
        
        Fabricante fabricante = new Fabricante(fabricanteDoProduto);
		Produto produto = new Produto(nomeDoProduto, descricaoDoProduto, valorDoProduto, fabricante);

        ProdutoService pService = new ProdutoService();
        pService.cadastrarProduto(fabricante, produto);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
	}
    
    private static void listarProdutos() {
        System.out.println("Produtos cadastrados:");

        ProdutoService pService = new ProdutoService();

        pService.listarProdutos();

        System.out.println("\nPressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }
    
    private static void deletarProduto() {
        System.out.print("Digite o nome do produto a ser deletado: ");
        var nomeDoProduto = teclado.next();
        ProdutoService pService = new ProdutoService();

        pService.deletarPorNome(nomeDoProduto);
        
        System.out.println("\nPressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }

    private static void consultarProduto() {
        System.out.print("Digite o nome do produto para consulta: ");
        var nomeDoProduto = teclado.next();

        ProdutoService pService = new ProdutoService();
        pService.buscarProdutoPorNome(nomeDoProduto);
    
        System.out.println("\nPressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }

    private static void alterarValorProduto() {
        System.out.print("Digite o nome do produto: ");
        var nomeDoProduto = teclado.next();
        System.out.print("Digite o novo valor do produto: ");
        var valorDoProduto = teclado.nextFloat();

        ProdutoService pService = new ProdutoService();

        pService.alterarValor(nomeDoProduto, valorDoProduto);

        System.out.println("\nPressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }

    private static void alterarFabricanteProduto() {
        System.out.print("Digite o nome do produto: ");
        var nomeDoProduto = teclado.next();
        System.out.print("Digite o novo fabricante do produto: ");
        var fabricanteDoProduto = teclado.next();

        ProdutoService pService = new ProdutoService();
        Fabricante fabricante = new Fabricante(fabricanteDoProduto);
        
        pService.alterarFabricante(nomeDoProduto, fabricante);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }

    private static void alterarDescricaoProduto() {
        System.out.print("Digite o nome do produto: ");
        var nomeDoProduto = teclado.next();
        System.out.print("Digite a nova descrição do produto: ");
        var descricaoDoProduto = teclado.next();

        ProdutoService pService = new ProdutoService();

        pService.alterarDescricao(nomeDoProduto, descricaoDoProduto);

        System.out.println("\nPressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }

    
}