package project.request;

import lombok.Data;

import javax.persistence.*;

@Data
public class TurmaPostRequestBody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turma")
    private Long id;
    private String nome;
}
