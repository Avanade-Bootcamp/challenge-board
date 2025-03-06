package challange.dio.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import challange.dio.board.model.BoardColumns;

public interface BoardColumnsRepository extends JpaRepository<BoardColumns, Long> {
}