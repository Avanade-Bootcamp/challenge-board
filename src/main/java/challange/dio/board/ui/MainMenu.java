package challange.dio.board.ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import challange.dio.board.service.BoardService;

@Component
public class MainMenu {

    @Autowired
    private BoardService boardService;

    private final Scanner scanner = new Scanner(System.in);

    public void execute() {
        System.out.println("Bem vindo ao gerenciador de boards, escolha a opção desejada");
        while (true) {
            try {
                System.out.println("1 - Criar um novo board");
                System.out.println("2 - Selecionar um board existente");
                System.out.println("3 - Excluir um board");
                System.out.println("4 - Sair");
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> createBoard();
                    case 2 -> selectBoard();
                    case 3 -> deleteBoard();
                    case 4 -> {
                        System.out.println("Saindo...");
                        scanner.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Opção inválida, informe uma opção do menu");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next(); // Limpa a entrada inválida
            }
        }
    }

    private void createBoard() {
        System.out.println("Informe o nome do board");
        var boardName = scanner.next();
        boardService.createBoard(boardName);
        System.out.println("Board " + boardName + " criado com sucesso");
    }

    private void selectBoard() {
        System.out.println("Informe o nome do board");
        var boardName = scanner.next();
        var board = boardService.getAllBoards().stream()
                .filter(b -> b.getName().equals(boardName))
                .findFirst()
                .orElse(null);
        if (board != null) {
            System.out.println("Board " + boardName + " selecionado com sucesso");
        } else {
            System.out.println("Board " + boardName + " não encontrado");
        }
    }

    private void deleteBoard() {
        System.out.println("Informe o nome do board");
        var boardName = scanner.next();
        var board = boardService.getAllBoards().stream()
                .filter(b -> b.getName().equals(boardName))
                .findFirst()
                .orElse(null);
        if (board != null) {
            boardService.deleteBoard(board.getId());
            System.out.println("Board " + boardName + " excluído com sucesso");
        } else {
            System.out.println("Board " + boardName + " não encontrado");
        }
    }
}