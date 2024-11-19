package com.SistemaElevador;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.SistemaElevador.business.ElevadorInterface;

// Classe principal que gerencia as interações do usuário com o elevador
@Component
@Configuration
@ComponentScan(basePackages = "com.SistemaElevador")
public class Main {

    @Autowired
    private ElevadorInterface elevador;
    private final int capacidadeMaxima = 5;
    private final int totalAndares = 10;

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class)) {
            Main main = context.getBean(Main.class); // Obter a instância da classe Main do contexto do Spring
            main.run(); // Chamar o método run na instância
        }
    }

    public void run() {
        elevador.inicializarElevador(capacidadeMaxima, totalAndares); // Inicializa o elevador com capacidade máxima e o total de andares
        try (Scanner scanner = new Scanner(System.in)) { // Scanner para entrada de dados com try-with-resources
            String input; // Armazena a opção selecionada pelo usuário

            // Laço principal do programa
            do {
                exibirMenu(); 
                input = scanner.nextLine(); 
                executarAcao(input, scanner); // Executa a ação correspondente à entrada
            } while (!input.equals("0")); // Sai do programa quando a entrada for "0"
        }
    }

    // Método para exibir o menu de opções
    private void exibirMenu() {
        System.out.println("-----------------------");
        System.out.println("1) Adicionar pessoa");
        System.out.println("2) Remover pessoa");
        System.out.println("3) Subir um andar");
        System.out.println("4) Descer um andar");
        System.out.println("5) Exibir informações");
        System.out.println("0) Encerrar");
    }

    // Método para executar a ação selecionada pelo usuário
    private void executarAcao(String input, Scanner scanner) {
        System.out.print("\033\143"); // Comando para limpar a tela (funciona em terminais compatíveis)
        switch (input) {
            case "1":
                System.out.println("Digite o nome da pessoa:");
                String nomeAdd = scanner.nextLine(); // Lê o nome da pessoa
                if (elevador.adicionarPessoa(nomeAdd)) {
                    System.out.println(nomeAdd + " entrou no elevador.");
                } else {
                    System.out.println("Elevador lotado! Não é possível adicionar mais pessoas.");
                }
                break;
            case "2":
                System.out.println("Digite o nome da pessoa para remover:");
                String nomeRemove = scanner.nextLine(); // Lê o nome da pessoa a ser removida
                if (elevador.removerPessoa(nomeRemove)) {
                    System.out.println(nomeRemove + " saiu do elevador.");
                } else {
                    System.out.println("Pessoa não encontrada no elevador.");
                }
                break;
            case "3":
                if (elevador.subir()) {
                    System.out.println("Elevador subiu um andar.");
                } else if (elevador.estaAcimaCapacidade()) {
                    System.out.println("Elevador está acima da capacidade e não pode subir!");
                } else {
                    System.out.println("Não é possível subir. Já está no último andar.");
                }
                break;
            case "4":
                if (elevador.descer()) {
                    System.out.println("Elevador desceu um andar.");
                } else if (elevador.estaAcimaCapacidade()) {
                    System.out.println("Elevador está acima da capacidade e não pode descer!");
                } else {
                    System.out.println("Não é possível descer. Já está no térreo.");
                }
                break;
            case "5":
                elevador.exibirInformacoes(); // Exibe as informações do elevador
                break;
            case "0":
                System.out.println("Encerrando...");
                break;
            default:
                System.out.println("Ação não encontrada!");
        }
    }
}