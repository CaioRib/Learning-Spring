package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno")
    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    @Pattern(regexp = ".+@pagseguro.com", message = "Apenas emails @pagseguro.com sao permitidos.")
    private String email;

    private String status;

    @ManyToOne
    @JsonIgnoreProperties("alunos")
    private Turma turma;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aluno")
    @JsonIgnoreProperties("aluno")
    private List<Prova> provas;

}
