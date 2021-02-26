package project.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import project.entity.Aluno;
import project.request.AlunoPostResquestBody;
import project.request.AlunoPutResquestBody;
import project.response.AlunoGetResponseBody;

@Component
@Mapper(componentModel = "spring")
public abstract class AlunoMapper {
    public abstract Aluno toAluno(AlunoPostResquestBody alunoPostResquestBody);
    public abstract Aluno toAluno(AlunoPutResquestBody alunoPutResquestBody);
    public abstract AlunoGetResponseBody toAlunoGetResponseBody(Aluno aluno);
}
