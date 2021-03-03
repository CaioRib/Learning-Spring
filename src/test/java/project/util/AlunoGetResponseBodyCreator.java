package project.util;

import project.response.AlunoGetResponseBody;

public class AlunoGetResponseBodyCreator {

    public static AlunoGetResponseBody createAlunoGetResponseBody(String name, String email) {
        return AlunoGetResponseBody.builder()
                .nome(name)
                .email(email)
                .build();
    }

    public static AlunoGetResponseBody toAluno(AlunoGetResponseBody alunoGetResponseBody){
        return alunoGetResponseBody.builder()
                .nome(alunoGetResponseBody.getNome())
                .email(alunoGetResponseBody.getEmail())
                .turma(alunoGetResponseBody.getTurma())
                .status(alunoGetResponseBody.getStatus())
                .build();
    }
}
