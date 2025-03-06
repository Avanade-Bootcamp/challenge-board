package challange.dio.board.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import challange.dio.board.model.Board;
import challange.dio.board.repository.BoardRepository;
import challange.dio.board.exception.EntityNotFoundException;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public Board createBoard(String name) {
        Board board = new Board();
        board.setName(name);
        return boardRepository.save(board);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Board not found"));
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}