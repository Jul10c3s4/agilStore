/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agilstore;

/**
 *
 * @author julio
 */
public class Produto {
    private static long counter = 0;
    private final long id;
    private String nome;
    private String categoria;
    private int quantidade;
    private double preco;

    public Produto() {
        this.id = ++counter;  // Garante que cada produto tenha um ID único
    }

    // Construtor com parâmetros para quando o produto for adicionado manualmente
    public Produto(String nome, String categoria, int quantidade, double preco) {
        this.id = ++counter;
        this.nome = nome;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "id: " + id + ";\nnome: " + nome + ";\ncategoria: " + categoria + ";\nquantidade: " + quantidade
                + ";\npreco: " + preco + ";\n";
    }

}

