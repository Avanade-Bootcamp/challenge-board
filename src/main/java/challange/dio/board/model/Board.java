package challange.dio.board.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}