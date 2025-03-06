package challange.dio.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import challange.dio.board.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}