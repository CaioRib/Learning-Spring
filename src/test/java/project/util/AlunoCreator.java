package project.util;

import project.entity.Aluno;

public class AlunoCreator {

    public static Aluno createAlunoToBeSaved(String name, String email){
        return Aluno.builder()
                .nome(name)
                .email(email)
                .build();
    }

    public static Aluno createValidAluno(String name, String email){
        return Aluno.builder()
                .nome(name)
                .email(email)
                .id(1L)
                .build();
    }


    public static Aluno createUpdatedAluno(Aluno aluno){
        return Aluno.builder()
                .nome(aluno.getNome() + " 2")
                .email(aluno.getEmail())
                .id(aluno.getId())
                .build();
    }


}
