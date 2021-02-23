package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.mapper.AlunoMapper;
import project.entity.Aluno;
import project.repository.AlunoRepository;
import project.request.AlunoPostResquestBody;
import project.request.AlunoPutResquestBody;

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

    public Aluno save(AlunoPostResquestBody alunoPostResquestBody){
        Aluno newAluno = AlunoMapper.INSTANCE.toAluno(alunoPostResquestBody);
        return alunoRepository.save(newAluno);
    }

    public void delete(Long id){
        alunoRepository.delete(findById(id));
    }

    public void replace(AlunoPutResquestBody alunoPutResquestBody) {
        Aluno newAluno = AlunoMapper.INSTANCE.toAluno(alunoPutResquestBody);
        Aluno oldAluno = findById(newAluno.getId());

        newAluno.builder()
                .id(oldAluno.getId())
                .build();

        alunoRepository.delete(oldAluno);
        alunoRepository.save(newAluno);
    }
}
