package challange.dio.board;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component  
public class Runner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // Criando o Scanner para capturar a entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Pedindo uma entrada ao usuário
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        // Exibindo o nome digitado
        System.out.println("Olá, " + nome + "! Bem-vindo ao nosso sistema.");

        // Fechar o scanner após o uso
        scanner.close();
    }
}