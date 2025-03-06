package challange.dio.board.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import challange.dio.board.model.BoardColumns;
import challange.dio.board.model.Cards;
import challange.dio.board.repository.CardsRepository;
import challange.dio.board.exception.EntityNotFoundException;

@Service
public class CardsService {

    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private BoardColumnsService boardColumnsService;

    public Cards createCard(Cards card) {
        return cardsRepository.save(card);
    }

    public List<Cards> getAllCards() {
        return cardsRepository.findAll();
    }

    public Cards getCardById(Long id) {
        return cardsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Card not found"));
    }

    public void deleteCard(Long id) {
        cardsRepository.deleteById(id);
    }

    public void moveToNextColumn(Long cardId) {
        Cards card = getCardById(cardId);
        BoardColumns currentColumn = card.getBoardColumn();
        BoardColumns nextColumn = boardColumnsService.getNextColumn(currentColumn);
        if (nextColumn != null) {
            card.setBoardColumn(nextColumn);
            cardsRepository.save(card);
        } else {
            throw new RuntimeException("Não há próxima coluna disponível");
        }
    }

    public void blockCard(Long cardId, String reason) {
        Cards card = getCardById(cardId);
        card.setBlocked(true);
        card.setBlockReason(reason);
        cardsRepository.save(card);
    }

    public void unblockCard(Long cardId, String reason) {
        Cards card = getCardById(cardId);
        card.setBlocked(false);
        card.setBlockReason(reason);
        cardsRepository.save(card);
    }

    public void cancelCard(Long cardId) {
        Cards card = getCardById(cardId);
        BoardColumns cancelColumn = boardColumnsService.getCancelColumn(card.getBoardColumn().getBoard().getId());
        card.setBoardColumn(cancelColumn);
        cardsRepository.save(card);
    }

    public List<Cards> getCardsByColumnId(Long columnId) {
        return cardsRepository.findByBoardColumnId(columnId);
    }
}