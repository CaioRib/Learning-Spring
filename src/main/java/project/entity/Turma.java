package project.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"nome"}))
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turma")
    private Long id;
    private String nome;
    private BigDecimal mensalidade;
}
