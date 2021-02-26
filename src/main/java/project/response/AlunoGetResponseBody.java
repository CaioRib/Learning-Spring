package project.response;

import lombok.Data;
import project.entity.Turma;

@Data
public class AlunoGetResponseBody {
    private String nome;
    private String email;
    private String status;
    private Turma turma;
}
