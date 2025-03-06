package challange.dio.board.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import challange.dio.board.model.BoardColumns;
import challange.dio.board.repository.BoardColumnsRepository;
import challange.dio.board.exception.EntityNotFoundException;

@Service
public class BoardColumnsService {

    @Autowired
    private BoardColumnsRepository boardColumnsRepository;

    public BoardColumns createBoardColumn(BoardColumns boardColumn) {
        return boardColumnsRepository.save(boardColumn);
    }

    public List<BoardColumns> getAllBoardColumns() {
        return boardColumnsRepository.findAll();
    }

    public BoardColumns getBoardColumnById(Long id) {
        return boardColumnsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Board column not found"));
    }

    public void deleteBoardColumn(Long id) {
        boardColumnsRepository.deleteById(id);
    }

    public List<BoardColumns> getColumnsByBoardId(Long boardId) {
        return boardColumnsRepository.findByBoardId(boardId);
    }

    public BoardColumns getNextColumn(BoardColumns currentColumn) {
        return boardColumnsRepository.findFirstByBoardIdAndOrderGreaterThanOrderByOrderAsc(
                currentColumn.getBoard().getId(), currentColumn.getOrder())
                .orElseThrow(() -> new EntityNotFoundException("No next column found"));
    }

    public BoardColumns getCancelColumn(Long boardId) {
        return boardColumnsRepository.findFirstByBoardIdAndKind(boardId, "CANCEL")
                .orElseThrow(() -> new EntityNotFoundException("Cancel column not found"));
    }
}