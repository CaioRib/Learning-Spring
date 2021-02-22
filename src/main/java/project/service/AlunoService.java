package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.entity.Aluno;
import project.repository.AlunoRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public Aluno findById(long id){
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno nao encontrado"));
    }

    public List<Aluno> findAll(){
        return alunoRepository.findAll();
    }

    public Aluno save(Aluno aluno){
        return alunoRepository.save(Aluno.builder().nome(aluno.getNome()).build());
    }
}
