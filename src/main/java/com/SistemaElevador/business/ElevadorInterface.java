package com.SistemaElevador.business;

import java.util.List;

public interface ElevadorInterface {
    // Inicializa o elevador com a capacidade máxima e o total de andares
    void inicializarElevador(int capacidadeMaxima, int totalAndares);
    // Adiciona uma pessoa ao elevador
    boolean adicionarPessoa(String nome);

    // Remove uma pessoa do elevador
    boolean removerPessoa(String nome);

    // Move o elevador para cima
    boolean subir();

    // Move o elevador para baixo
    boolean descer();

    // Exibe as informações do elevador
    void exibirInformacoes();

    // Verifica se o elevador está acima da capacidade máxima permitida
    boolean estaAcimaCapacidade();

    // Métodos para acessar e modificar andarAtual
    int getAndarAtual();
    void setAndarAtual(int andar);

    // Métodos para acessar a lista de pessoas
    List<String> getPessoas();
}