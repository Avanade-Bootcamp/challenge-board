package challange.dio.board.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BOARDS_COLUMNS",
       uniqueConstraints = @UniqueConstraint(columnNames = {"board_id", "`order`"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "`order`", nullable = false)
    private int order;

    @Column(nullable = false, length = 7)
    private String kind;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;
}
