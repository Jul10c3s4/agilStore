/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agilstore;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author julio
 */
public class GerenciaProdutos {
    ArrayList<Produto> produtos = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    String filePath = "produtos.json";
    ObjectMapper mapper = new ObjectMapper();
    File file = new File(filePath);
    
    public void iniciar(){        
        if(file.exists()){       
            return;
        }else{
            escreverProdutos();
        }
    }
    public double inputDouble(){
        double numero = scanner.nextDouble();
        scanner.nextLine();
        return numero;
    }
    public int inputInt(){
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }

    public void exibirMenu(){
        System.out.println("Escolha uma das opções abaixos: ");
        System.out.println("1 - Adicionar produto;");
        System.out.println("2 - Listar produto;");
        System.out.println("3 - Atualizar produto;");
        System.out.println("4 - Excluir produto;");
        System.out.println("5 - Buscar produto;");
        System.out.println("0 - Sair do sistema.");
    }
    
    public void menu(){
        iniciar();
        int resp = 0;

        do{
            exibirMenu();
            resp = inputInt();

            switch (resp) {
                case 1:
                    adicionarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    atualizarProduto();
                    break;
                case 4:
                    excluirProduto();
                    break;
                case 5:
                    buscarProduto();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inexistente!");
                    break;
            }
        }while(resp != 0);
    }

    public void adicionarProduto(){
        System.out.println("\n---------- Cadastro de produto ----------");
        System.out.println("\nInforme o nome do produto: ");
        String nome = scanner.nextLine();
        System.out.println("Informe a categoria do produto: ");
        String categoria = scanner.nextLine();
        System.out.println("Informe a quantidade em estoque: ");
        int quantidade = inputInt();
        System.out.println("Informe o preço do produto: ");
        double preco = inputDouble();
        
        Produto produto = new Produto(nome, categoria, quantidade, preco);
        produtos.add(produto);
        escreverProdutos();
    }

    public void listarProdutos(){
        int resp = 0;
        System.out.println("\n---------- Listagem de produto ----------");
        do{
            System.out.println("\nSelecione a opção de listagem dos produtos: ");
            System.out.println("1 - Listar todos os produtos;");
            System.out.println("2 - Listar por categoria;");
            System.out.println("3 - Listar em ordem alfabética;");
            System.out.println("4 - Listar por quantidade em estoque;");
            System.out.println("5 - Listar por preço;");
            System.out.println("0 - Voltar ao menu principal.");
            resp = inputInt();
            System.out.println();
            lerProdutos();
            switch(resp){
                case 1:
                    verificar();
                    for(Produto p: produtos){
                        System.out.println(p.toString());
                    }
                    break;

                case 2:
                    verificar();
                    if(produtos.size() != 0){
                        ArrayList<String> categorias = pegarCategorias();
                        int counter = 0;
                        System.out.println("Selecione a categoria: ");
                        for(String c: categorias){
                            System.out.println((++counter)+" - "+c);
                        }
                        int index = inputInt();

                        String categoria = categorias.get(index-1);
                        System.out.println("Categoria: "+categoria);
                        for (Produto p : produtos) {
                            if (p.getCategoria().equals(categoria)) {
                                System.out.println(p.toString());
                            }
                        }
                        break;
                    }

                case 3:
                    ArrayList<Produto> produtosPorNome = new ArrayList<>(produtos);
                    produtosPorNome.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
                    for (Produto p: produtosPorNome){
                        System.out.println(p.toString());
                    }
                    break;

                case 4:
                    ArrayList<Produto> produtosPorQuantidade = new ArrayList<>(produtos);
                    perguntarOrdem();
                    int respQuant = inputInt();
                    if(respQuant == 1){
                        produtosPorQuantidade.sort((p1, p2) -> Integer.compare(p1.getQuantidade(), p2.getQuantidade()));
                    }else if(respQuant == 2){
                        produtosPorQuantidade.sort((p1, p2) -> Integer.compare(p2.getQuantidade(), p1.getQuantidade()));
                    }else{
                        System.out.println("Opção inexistente!");
                    }
                    for (Produto p: produtosPorQuantidade){
                        System.out.println(p.toString());
                    }
                    break;

                case 5:
                    ArrayList<Produto> produtosPorPreco = new ArrayList<>(produtos);
                    perguntarOrdem();
                    int respPreco = inputInt();
                    if(respPreco == 1){
                        produtosPorPreco.sort((p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco()));
                    }else{
                        produtosPorPreco.sort((p1, p2) -> Double.compare(p2.getPreco(), p1.getPreco()));
                    }
                    for (Produto p: produtosPorPreco){
                        System.out.println(p.toString());
                    }
                    break;
                    case 0:
                        break;
                default:
                    System.out.println("Opção inexistente!");  
                    break;  
            }
        }while(resp != 0);
    }

    public void atualizarProduto(){
        System.out.println("\n---------- Atualização de produto ----------");
        Produto p = buscarProdutoId();
        if (p != null) {
            int resp = 0;
            do{
                    System.out.println("\nDados atuais:\n"+p.toString());
                    System.out.println("Informe o campo que deseja atualizar: ");
                    System.out.println("1 - Nome;");
                    System.out.println("2 - Categoria;");
                    System.out.println("3 - Quantidade;");
                    System.out.println("4 - Preço;");
                    System.out.println("0 - voltar ao menu principal.");
                    resp = inputInt();
                    System.out.println();
                    switch (resp) {
                        case 1:
                            System.out.println("\nInforme o novo nome: ");
                            String nome = scanner.nextLine().trim(); 

                            while (nome.isEmpty()) {
                                System.out.println("O nome não pode ser vazio. Informe novamente:");
                                nome = scanner.nextLine().trim();
                            }

                            p.setNome(nome);
                            System.out.println("\nProduto atualizado!");
                            break;
                        case 2:
                            System.out.println("Informe a nova categoria: ");
                            String categoria = scanner.nextLine().trim();
                            
                            while (categoria.isEmpty()) {
                                System.out.println("A categoria não pode ser vazia. Informe novamente:");
                                categoria = scanner.nextLine().trim();
                            }

                            p.setCategoria(categoria);
                            System.out.println("Produto atualizado!");
                            break;

                        case 3:
                            System.out.println("Informe a nova quantidade: ");
                            int quantidade = inputInt();

                            while (quantidade < 0) {
                                System.out.println("A quantidade deve ser um número inteiro positivo. Informe novamente:");
                                quantidade = inputInt();
                            }
                            p.setQuantidade(quantidade);
                            System.out.println("Produto atualizado!");
                            break;
                        case 4:
                            System.out.println("Informe o novo preço: ");
                            double preco = scanner.nextDouble();
                            while (preco <= 0) {
                                System.out.println("O preço deve ser um número positivo. Informe novamente:");
                                preco = inputDouble();
                            }
                            p.setPreco(preco);
                            System.out.println("Produto atualizado!");
                            break;
                        case 0:
                            escreverProdutos();
                            break;
                        default:
                            System.out.println("Opção inexistente!");
                            break;
                    }
                }while (resp != 0);
        }
    }

    public void excluirProduto(){
        System.out.println("\n---------- Exclusão de produto ----------");
        Produto produto = buscarProdutoId();

        if (produto != null ) {
            System.out.println("\nTem certeza que deseja excluir o produto "+produto.getNome()+":");
            System.out.println("1 - Sim;");
            System.out.println("2 - Não;");

            int resp = scanner.nextInt();
            if (resp == 1) {
                produtos.remove(produto);
                System.out.println("\nO produto "+produto.getNome()+" foi removido com sucesso!\n");
                escreverProdutos();
            }else if(resp == 2){
                System.out.println("\nA remoção do produto "+produto.getNome()+" foi cancelada!\n");
            }else{
                System.out.println("Opção inexistente!");
            }
        }
    }
    
    public ArrayList<String> pegarCategorias(){
        ArrayList<String> categorias = new ArrayList<>();
        for (Produto p : produtos) {
            if(!categorias.contains(p.getCategoria())){
                categorias.add(p.getCategoria());
            }
        }
        return categorias;
    }

    public void perguntarOrdem(){
        System.out.println("Informe em que ordem deseja ordenar os produtos: ");
        System.out.println("1 - crescente;");
        System.out.println("2 - decrescente.");
    }

    public Produto buscarProdutoId(){
        System.out.println("\nInforme o id do produto: ");
        int id = inputInt();
        for (Produto p : produtos) {
            if(p.getId() == id){
                return p;
            }
        }
        System.out.println("\nProduto não encontrado!\n");
        return null;
    }
    public void buscarProduto(){
        System.out.println("\n---------- Buscagem de produto ----------");
        System.out.println("\nComo deseja buscar o produto: ");
        System.out.println("1 - por id;");
        System.out.println("2 - por nome;");
        int resp = inputInt();
        lerProdutos();
        if(resp == 1){
            Produto p = buscarProdutoId();
            if(p != null){
                System.out.println("\nProduto encontrado:\n"+p.toString());
            }
        }else if(resp == 2){
            System.out.println("\nInforme o nome do produto: ");
            String nome = scanner.nextLine();
            ArrayList<Produto> produtosEncontrados = new ArrayList<>();
            for (Produto p : produtos) {
                if(p.getNome().contains(nome.substring(0, 1).toUpperCase() + nome.substring(1))){
                    produtosEncontrados.add(p);
                }
            }
            if (produtosEncontrados.size() > 0) {
                System.out.println("Produtos encontrados:\n");
                for (Produto p : produtosEncontrados) {
                    System.out.println(p.toString());
                }
            }
            if(produtosEncontrados.size() == 0){
                System.out.println("\nproduto não encontrado!\n");
            }
        }else{
            System.out.println("\nopção inexistente!\n");
        }
    }
    public void lerProdutos(){
        try {
            if (file.length() == 0) {
                produtos = new ArrayList<>();
                return;
            }
            produtos = mapper.readValue(file, new TypeReference<ArrayList<Produto>>() {});
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
    
    public void escreverProdutos(){
        try {
            mapper.writeValue(file, produtos);
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo JSON: " + e.getMessage());
        }
    }
    
    public void verificar(){
        if(produtos.size() == 0){
            System.out.println("Não possui produtos cadastrados!");
        }
    }
}

