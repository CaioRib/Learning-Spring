package project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.entity.Aluno;
import project.request.AlunoPostResquestBody;
import project.request.AlunoPutResquestBody;
import project.response.AlunoGetResponseBody;

@Mapper(componentModel = "spring")
public abstract class AlunoMapper {
    public static final AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);
    public abstract Aluno toAluno(AlunoPostResquestBody alunoPostResquestBody);
    public abstract Aluno toAluno(AlunoPutResquestBody alunoPutResquestBody);
    public abstract AlunoGetResponseBody toAlunoGetResponseBody(Aluno aluno);
}
