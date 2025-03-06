package challange.dio.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import challange.dio.board.model.Blocks;

public interface BlocksRepository extends JpaRepository<Blocks, Long> {
}