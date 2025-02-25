package challange.dio.board.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CARDS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "board_column_id", nullable = false)
    private BoardColumns boardColumn;
}