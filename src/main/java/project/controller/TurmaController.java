package project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.entity.Aluno;
import project.entity.Turma;
import project.request.TurmaPostRequestBody;
import project.request.TurmaPutResquestBody;
import project.service.TurmaService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("turmas")
@RequiredArgsConstructor
@Slf4j
public class TurmaController {
    private final TurmaService turmaService;

    //Apenas para debug
    @GetMapping(path="all")
    public ResponseEntity<List<Turma>> findAll(){
        log.info("findAll Turma get request");
        return ResponseEntity.ok(turmaService.findAll());
    }

    @GetMapping(path="criarGrupos/{id}")
    public ResponseEntity<Map<String,List<Aluno>>> groupGenerator(@PathVariable Long id, @RequestParam Long n){
        log.info("groupGenerator Turma get request");
        return ResponseEntity.ok(turmaService.groupGenerator(id, n));
    }

    @PostMapping
    public ResponseEntity<Turma> save(@RequestBody TurmaPostRequestBody turmaPostRequestBody){
        log.info("save Turma get request");
        return new ResponseEntity<>(turmaService.save(turmaPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody TurmaPutResquestBody turmaPutResquestBody){
        log.info("replace Turma put request");
        turmaService.replace(turmaPutResquestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
