package challange.dio.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import challange.dio.board.model.Cards;

public interface CardsRepository extends JpaRepository<Cards, Long> {
}