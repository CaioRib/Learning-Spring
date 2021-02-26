package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.entity.Aluno;
import project.entity.Prova;
import project.repository.AlunoRepository;
import project.repository.ProvaRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProvaService {
    private final ProvaRepository provaRepository;
    private final AlunoRepository alunoRepository;

    public Prova save(Long id_aluno, BigDecimal nota) {
        Aluno aluno = alunoRepository.findById(id_aluno)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno nao encontrado"));
        Prova newProva = Prova.builder()
                            .aluno(aluno)
                            .nota(nota)
                            .build();
        return(provaRepository.save(newProva));
    }
}
