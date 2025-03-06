package challange.dio.board.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import challange.dio.board.model.Cards;
import challange.dio.board.repository.CardsRepository;
import challange.dio.board.exception.EntityNotFoundException;

@Service
public class CardsService {

    @Autowired
    private CardsRepository cardsRepository;

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
}