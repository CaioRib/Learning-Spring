package project.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TurmaGetResponseBody {
    private String nome;
    private BigDecimal mensalidade;
    private List<AlunoGetResponseBody> alunos;
}
