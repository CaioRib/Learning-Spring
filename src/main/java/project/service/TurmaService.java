package project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.entity.Aluno;
import project.entity.Turma;
import project.repository.TurmaRepository;
import project.request.TurmaPostRequestBody;
import project.request.TurmaPutResquestBody;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
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

        turmaRepository.save(newturma);

    }

    public Map<String, List<Aluno>> groupGenerator(Long id, Long n) {
        List<Aluno> alunos = findById(id).getAlunos();
        Collections.shuffle(alunos);

        Map<String, List<Aluno>> groups = new HashMap<>();

        int salaSize = alunos.size();
        int groupSize = (int) Math.floor(salaSize / n);
        int overflow = (int) (salaSize % n);

//        int i , j;
//        for(i = 0; i < n; i++){
//            List<Aluno> group = new ArrayList<>();
//            j = 0;
//            for(Iterator<Aluno> it = alunos.iterator(); j < groupSize + ((i >= n - overflow) ? 1 : 0); j++){
//                group.add(it.next());
//                it.remove();
//            }
//
//            groups.put("Grupo " + (i + 1), group);
//        }

        int fromIndex = 0;
        int toIndex = 0;

        for(int i = 0; i < n; i++) {
            toIndex += groupSize + ((i >= n - overflow) ? 1 : 0);
            groups.put("Grupo " + (i + 1), alunos.subList(fromIndex, toIndex));
            fromIndex = toIndex;
        }

        return groups;

    }
}
