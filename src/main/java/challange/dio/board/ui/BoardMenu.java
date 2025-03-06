package challange.dio.board.ui;

import challange.dio.board.model.Board;
import challange.dio.board.model.BoardColumns;
import challange.dio.board.model.Cards;
import challange.dio.board.service.BoardColumnsService;
import challange.dio.board.service.CardsService;
import challange.dio.board.service.BlocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BoardMenu {

    @Autowired
    private BoardColumnsService boardColumnsService;

    @Autowired
    private CardsService cardsService;

    @Autowired
    private BlocksService blocksService;

    private final Scanner scanner = new Scanner(System.in);

    private Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    public void execute() {
        System.out.printf("Bem vindo ao board %s, selecione a operação desejada\n", board.getName());
        var option = -1;
        while (option != 9) {
            System.out.println("1 - Criar um card");
            System.out.println("2 - Mover um card");
            System.out.println("3 - Bloquear um card");
            System.out.println("4 - Desbloquear um card");
            System.out.println("5 - Cancelar um card");
            System.out.println("6 - Ver board");
            System.out.println("7 - Ver coluna com cards");
            System.out.println("8 - Ver card");
            System.out.println("9 - Voltar para o menu anterior");
            System.out.println("10 - Sair");
            System.out.println("11 - Criar uma coluna");
            try {
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1 -> createCard();
                    case 2 -> moveCardToNextColumn();
                    case 3 -> blockCard();
                    case 4 -> unblockCard();
                    case 5 -> cancelCard();
                    case 6 -> showBoard();
                    case 7 -> showColumn();
                    case 8 -> showCard();
                    case 9 -> System.out.println("Voltando para o menu anterior");
                    case 10 -> System.exit(0);
                    case 11 -> createColumn();
                    default -> System.out.println("Opção inválida, informe uma opção do menu");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
            }
        }
    }

    private void createCard() {
        System.out.println("Informe o título do card");
        var title = scanner.nextLine();
        System.out.println("Informe a descrição do card");
        var description = scanner.nextLine();
        var columns = boardColumnsService.getColumnsByBoardId(board.getId());
        if (columns.isEmpty()) {
            System.out.println("Nenhuma coluna encontrada para o board. Não é possível criar um card.");
            return;
        }
        Cards card = new Cards();
        card.setTitle(title);
        card.setDescription(description);
        card.setBoardColumn(columns.get(0)); // Definindo a primeira coluna como padrão
        cardsService.createCard(card);
        System.out.println("Card criado com sucesso");
    }

    private void createColumn() {
        System.out.println("Informe o nome da coluna");
        var name = scanner.nextLine();
        System.out.println("Informe a ordem da coluna");
        var order = Integer.parseInt(scanner.nextLine());
        System.out.println("Informe o tipo da coluna");
        var kind = scanner.nextLine();
        BoardColumns column = new BoardColumns();
        column.setName(name);
        column.setOrder(order);
        column.setKind(kind);
        column.setBoard(board);
        boardColumnsService.createBoardColumn(column);
        System.out.println("Coluna criada com sucesso");
    }

    private void moveCardToNextColumn() {
        System.out.println("Informe o id do card que deseja mover para a próxima coluna");
        var cardId = Long.parseLong(scanner.nextLine());
        try {
            cardsService.moveToNextColumn(cardId);
            System.out.println("Card movido com sucesso");
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void blockCard() {
        System.out.println("Informe o id do card que será bloqueado");
        var cardId = Long.parseLong(scanner.nextLine());
        System.out.println("Informe o motivo do bloqueio do card");
        var reason = scanner.nextLine();
        try {
            cardsService.blockCard(cardId, reason);
            System.out.println("Card bloqueado com sucesso");
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void unblockCard() {
        System.out.println("Informe o id do card que será desbloqueado");
        var cardId = Long.parseLong(scanner.nextLine());
        System.out.println("Informe o motivo do desbloqueio do card");
        var reason = scanner.nextLine();
        try {
            cardsService.unblockCard(cardId, reason);
            System.out.println("Card desbloqueado com sucesso");
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void cancelCard() {
        System.out.println("Informe o id do card que deseja mover para a coluna de cancelamento");
        var cardId = Long.parseLong(scanner.nextLine());
        try {
            cardsService.cancelCard(cardId);
            System.out.println("Card cancelado com sucesso");
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void showBoard() {
        var columns = boardColumnsService.getColumnsByBoardId(board.getId());
        System.out.printf("Board [%s,%s]\n", board.getId(), board.getName());
        columns.forEach(c -> {
            var cards = cardsService.getCardsByColumnId(c.getId());
            System.out.printf("Coluna [%s] tipo: [%s] tem %s cards\n", c.getName(), c.getKind(), cards.size());
        });
    }

    private void showColumn() {
        var columns = boardColumnsService.getColumnsByBoardId(board.getId());
        var columnIds = columns.stream().map(c -> c.getId()).toList();
        var selectedColumnId = -1L;
        while (!columnIds.contains(selectedColumnId)) {
            System.out.printf("Escolha uma coluna do board %s pelo id\n", board.getName());
            columns.forEach(c -> System.out.printf("%s - %s [%s]\n", c.getId(), c.getName(), c.getKind()));
            selectedColumnId = Long.parseLong(scanner.nextLine());
        }
        var column = boardColumnsService.getBoardColumnById(selectedColumnId);
        System.out.printf("Coluna %s tipo %s\n", column.getName(), column.getKind());
        var cards = cardsService.getCardsByColumnId(column.getId());
        cards.forEach(c -> System.out.printf("Card %s - %s\nDescrição: %s\n", c.getId(), c.getTitle(), c.getDescription()));
    }

    private void showCard() {
        System.out.println("Informe o id do card que deseja visualizar");
        var cardId = Long.parseLong(scanner.nextLine());
        var card = cardsService.getCardById(cardId);
        System.out.printf("Card %s - %s.\n", card.getId(), card.getTitle());
        System.out.printf("Descrição: %s\n", card.getDescription());
        System.out.println(card.isBlocked() ? "Está bloqueado. Motivo: " + card.getBlockReason() : "Não está bloqueado");
        System.out.printf("Está no momento na coluna %s - %s\n", card.getBoardColumn().getId(), card.getBoardColumn().getName());
    }
}