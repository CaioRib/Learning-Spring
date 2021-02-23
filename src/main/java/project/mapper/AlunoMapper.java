package project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.entity.Aluno;
import project.request.AlunoPostResquestBody;
import project.request.AlunoPutResquestBody;

@Mapper(componentModel = "spring")
public abstract class AlunoMapper {
    public static final AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);
    public abstract Aluno toAluno(AlunoPostResquestBody alunoPostResquestBody);
    public abstract Aluno toAluno(AlunoPutResquestBody alunoPutResquestBody);
}
