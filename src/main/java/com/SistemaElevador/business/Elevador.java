package com.SistemaElevador.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

// Classe responsável por encapsular o comportamento e os dados do elevador
@Component
public class Elevador implements ElevadorInterface {  
    private int andarAtual; 
    private int capacidadeMaxima; 
    public ArrayList<String> pessoas; 
    private int totalAndares; 

    // Construtor que inicializa o elevador no térreo (andar 0) sem pessoas
    public void inicializarElevador(int capacidadeMaxima, int totalAndares) {
        this.andarAtual = 0; // O elevador começa no térreo
        this.capacidadeMaxima = capacidadeMaxima; // Define a capacidade máxima do elevador
        this.pessoas = new ArrayList<>(); // Inicializa a lista de pessoas vazia
        this.totalAndares = totalAndares; // Define o número de andares do prédio
    }

    // Método para adicionar uma pessoa ao elevador
    public boolean adicionarPessoa(String nome) {
        // Verifica se ainda há espaço no elevador
        if (pessoas.size() < capacidadeMaxima) {
            pessoas.add(nome); // Adiciona a pessoa à lista
            return true; 
        }
        return false; // Indica que o elevador está lotado
    }

    // Método para remover uma pessoa do elevador
    public boolean removerPessoa(String nome) {
        // Tenta remover a pessoa pelo nome
        return pessoas.remove(nome); // Retorna true se a pessoa foi removida
    }

    // Método para subir um andar
    public boolean subir() {
        // Verifica se o elevador pode subir e se está dentro do limite de capacidade
        if (andarAtual < totalAndares && !estaAcimaCapacidade()) {
            andarAtual++; 
            return true; 
        }
        return false; // Não pode subir
    }

    // Método para descer um andar
    public boolean descer() {
        // Verifica se o elevador pode descer e se está dentro do limite de capacidade
        if (andarAtual > 0 && !estaAcimaCapacidade()) {
            andarAtual--; 
            return true; 
        }
        return false; // Não pode descer
    }

    // Método para exibir as informações do elevador
    public void exibirInformacoes() {
        System.out.println("Andar atual: " + andarAtual);
        System.out.println("Capacidade máxima: " + capacidadeMaxima);
        System.out.println("Pessoas no elevador: " + pessoas.size() + "/" + capacidadeMaxima);
        System.out.println("Lista de pessoas: " + pessoas);
    }

    // Método que verifica se o elevador está acima da capacidade máxima permitida
    public boolean estaAcimaCapacidade() {
        return pessoas.size() > capacidadeMaxima; // Retorna true se o número de pessoas for maior à capacidade
    }

    // Métodos para acessar e modificar andarAtual
    @Override
    public int getAndarAtual() {
        return andarAtual;
    }

    @Override
    public void setAndarAtual(int andar) {
        this.andarAtual = andar;
    }

    // Métodos para acessar a lista de pessoas
    @Override
    public List<String> getPessoas() {
        return pessoas;
    }
}