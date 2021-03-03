package project.util;

import project.entity.Aluno;
import project.request.AlunoPostResquestBody;

public class AlunoPostRequestBodyCreator {

    public static AlunoPostResquestBody createAlunoAlunoPostResquestBody(String name, String email){
        return AlunoPostResquestBody.builder()
                .nome(name)
                .email(email)
                .build();

    }


    public static AlunoPostResquestBody toAlunoPostResquestBody(Aluno aluno){
        return AlunoPostResquestBody.builder()
                .nome(aluno.getNome())
                .email(aluno.getEmail())
                .turma(aluno.getTurma())
                .status(aluno.getStatus())
                .build();
    }


}
