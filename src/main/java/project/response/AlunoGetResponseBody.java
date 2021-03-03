package project.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import project.entity.Turma;

@Data
@Builder
@AllArgsConstructor
public class AlunoGetResponseBody {
    private String nome;
    private String email;
    private String status;
    private Turma turma;
}
