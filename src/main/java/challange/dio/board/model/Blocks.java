package challange.dio.board.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BLOCKS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blocks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "blocked_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime blockedAt;

    @Column(name = "block_reason", nullable = false)
    private String blockReason;

    @Column(name = "unblocked_at")
    private LocalDateTime unblockedAt;

    @Column(name = "unblock_reason")
    private String unblockReason;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Cards card;
}
