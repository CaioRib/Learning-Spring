package project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.ap.shaded.freemarker.core.ArithmeticEngine;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.entity.Prova;
import project.entity.Turma;
import project.mapper.AlunoMapper;
import project.entity.Aluno;
import project.repository.AlunoRepository;
import project.repository.TurmaRepository;
import project.request.AlunoPostResquestBody;
import project.request.AlunoPutResquestBody;
import project.response.AlunoGetResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;

    public AlunoGetResponseBody findById(long id){
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno nao encontrado"));

        return AlunoMapper.INSTANCE.toAlunoGetResponseBody(aluno);

    }

    public List<Aluno> findAll(){
        return alunoRepository.findAll();
    }

    public Aluno save(AlunoPostResquestBody alunoPostResquestBody){
        Aluno newAluno = AlunoMapper.INSTANCE.toAluno(alunoPostResquestBody);
        return alunoRepository.save(newAluno);
    }

    public void delete(Long id){
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno nao encontrado"));

        alunoRepository.delete(aluno);
    }

    public void replace(AlunoPutResquestBody alunoPutResquestBody) {
        Aluno newAluno = AlunoMapper.INSTANCE.toAluno(alunoPutResquestBody);

        Aluno oldAluno = alunoRepository.findById(newAluno.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno nao encontrado"));

        newAluno.setId(oldAluno.getId());
        newAluno.setTurma(oldAluno.getTurma());

        alunoRepository.save(newAluno);
    }

    public void register(Long id_aluno, Long id_turma){
        Aluno aluno = alunoRepository.findById(id_aluno)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno nao encontrado"));

        Turma turma = turmaRepository.findById(id_turma)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Turma nao encontrada"));;

        aluno.setTurma(turma);
        alunoRepository.save(aluno);

    }

    public String meanGrade(Long id){
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno nao encontrado"));

        List<Prova> provas = aluno.getProvas();
        BigDecimal mean = BigDecimal.ZERO;

        for(Prova prova : provas){
            mean = mean.add(prova.getNota());
        }
        mean = mean.divide(BigDecimal.valueOf(provas.size()));

        return (((mean.compareTo(BigDecimal.valueOf(5)) > 0) ? "Aprovado" : "Reprovado") + " com nota " + mean.toString());

    }
}
