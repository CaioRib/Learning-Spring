package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.entity.Turma;
import project.repository.TurmaRepository;
import project.request.TurmaPostRequestBody;
import project.request.TurmaPutResquestBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TurmaService {
    private final TurmaRepository turmaRepository;

    public Turma findById(Long id){
        return turmaRepository.findById(id)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Turma nao encontrada."));
    }

    public List<Turma> findAll(){
        return turmaRepository.findAll();
    }

    public Turma save(TurmaPostRequestBody turmaPostRequestBody){
        Map<String, BigDecimal> priceMap = new HashMap<>();
        priceMap.put("1Sl", BigDecimal.valueOf(100));
        priceMap.put("2Sl", BigDecimal.valueOf(150));
        priceMap.put("3Sl", BigDecimal.valueOf(200));
        priceMap.put("4Sl", BigDecimal.valueOf(250));

        String turmaNome = turmaPostRequestBody.getNome();

        // Mapeamento manual
        Turma newTurma = Turma.builder()
            .id(turmaPostRequestBody.getId())
            .nome(turmaPostRequestBody.getNome())
            .mensalidade(priceMap.containsKey(turmaNome) ? priceMap.get(turmaNome) : BigDecimal.valueOf(20))
            .build();

        return turmaRepository.save(newTurma);
    }

    public void delete(Long id){
        turmaRepository.delete(findById(id));
    }

    public void replace(TurmaPutResquestBody turmaPutResquestBody) {
        Turma oldTurma = findById(turmaPutResquestBody.getId());
        Turma newturma = Turma.builder()
                            .id(oldTurma.getId())
                            .nome(turmaPutResquestBody.getNome())
                            .build();

        turmaRepository.delete(oldTurma);
        turmaRepository.save(newturma);

    }
}
