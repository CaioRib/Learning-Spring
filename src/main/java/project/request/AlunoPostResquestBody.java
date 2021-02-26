package project.request;

import lombok.Data;
import project.entity.Turma;

@Data
public class AlunoPostResquestBody {
    private String nome;
    private String email;
    private String status;
    private Turma turma;
}
