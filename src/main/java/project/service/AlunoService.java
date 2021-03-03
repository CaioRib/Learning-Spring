package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.entity.Aluno;
import project.entity.Prova;
import project.entity.Turma;
import project.mapper.AlunoMapper;
import project.repository.AlunoRepository;
import project.repository.TurmaRepository;
import project.request.AlunoPostResquestBody;
import project.request.AlunoPutResquestBody;
import project.response.AlunoGetResponseBody;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;
    private final AlunoMapper alunoMapper;

    public AlunoGetResponseBody findById(long id){
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno nao encontrado"));

        return alunoMapper.toAlunoGetResponseBody(aluno);

    }

    public List<Aluno> findAll(){
        return alunoRepository.findAll();
    }

    public Aluno save(AlunoPostResquestBody alunoPostResquestBody){
        Aluno newAluno = alunoMapper.toAluno(alunoPostResquestBody);
        newAluno.setStatus("Inativo");
        newAluno.setTurma(null);
        return alunoRepository.save(newAluno);
    }

    public void delete(Long id){
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno nao encontrado"));

        alunoRepository.delete(aluno);
        aluno.setStatus("Inativo");
        aluno.setTurma(null);
        alunoRepository.save(aluno);
    }

    public void replace(AlunoPutResquestBody alunoPutResquestBody) {
        Aluno newAluno = alunoMapper.toAluno(alunoPutResquestBody);

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
        aluno.setStatus("Ativo");
        alunoRepository.save(aluno);

    }

    public String meanGrade(Long id){
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno nao encontrado"));

        List<Prova> provas = (aluno.getProvas() != null ? aluno.getProvas() : Collections.emptyList());
        BigDecimal mean = BigDecimal.ZERO;

        for(Prova prova : provas){
            mean = mean.add(prova.getNota());
        }
        mean = mean.divide(BigDecimal.valueOf(provas.size()));

        return (((mean.compareTo(BigDecimal.valueOf(5)) > 0) ? "Aprovado" : "Reprovado") + " com nota " + mean.toString());

    }

}
