package project.request;

import lombok.Builder;
import lombok.Data;
import project.entity.Turma;

@Data
@Builder
public class AlunoPostResquestBody {
    private String nome;
    private String email;
    private String status;
    private Turma turma;
}
