package challange.dio.board.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import challange.dio.board.model.BoardColumns;

public interface BoardColumnsRepository extends JpaRepository<BoardColumns, Long> {
    List<BoardColumns> findByBoardId(Long boardId);
    Optional<BoardColumns> findFirstByBoardIdAndOrderGreaterThanOrderByOrderAsc(Long boardId, int order);
    Optional<BoardColumns> findFirstByBoardIdAndKind(Long boardId, String kind);
}