package project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.entity.Aluno;
import project.request.AlunoPostResquestBody;
import project.request.AlunoPutResquestBody;
import project.service.AlunoService;

import java.util.List;

@RestController
@RequestMapping("alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {
    private final AlunoService alunoService;

    @GetMapping(path = "{/id}")
    public ResponseEntity<Aluno> findById(@PathVariable long id){
        log.info("findById get request");
        return ResponseEntity.ok(alunoService.findById(id));
    }

    //Apenas para debug
    @GetMapping(path = "all")
    public ResponseEntity<List<Aluno>> findAll() {
        log.info("findAll get request");
        return ResponseEntity.ok(alunoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody AlunoPostResquestBody aluno){
        log.info("save post request");
        return new ResponseEntity<>(alunoService.save(aluno), HttpStatus.CREATED);
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        log.info("delete delete request");
        alunoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AlunoPutResquestBody alunoPutResquestBody){
        log.info("replace put request");
        alunoService.replace(alunoPutResquestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
