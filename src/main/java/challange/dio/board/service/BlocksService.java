package challange.dio.board.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import challange.dio.board.model.Blocks;
import challange.dio.board.repository.BlocksRepository;
import challange.dio.board.exception.EntityNotFoundException;

@Service
public class BlocksService {

    @Autowired
    private BlocksRepository blocksRepository;

    public Blocks createBlock(Blocks block) {
        return blocksRepository.save(block);
    }

    public List<Blocks> getAllBlocks() {
        return blocksRepository.findAll();
    }

    public Blocks getBlockById(Long id) {
        return blocksRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Block not found"));
    }

    public void deleteBlock(Long id) {
        blocksRepository.deleteById(id);
    }
}