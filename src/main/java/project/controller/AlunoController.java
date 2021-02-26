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
import java.util.Map;

@RestController
@RequestMapping("alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {
    private final AlunoService alunoService;


    @GetMapping(path = "/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable long id) {
        log.info("findById Aluno get request");
        return ResponseEntity.ok(alunoService.findById(id));
    }

    //Apenas para debug
    @GetMapping(path = "all")
    public ResponseEntity<List<Aluno>> findAll() {
        log.info("findAll Aluno get request");
        return ResponseEntity.ok(alunoService.findAll());
    }

    @GetMapping(path="media/{id}")
    public  ResponseEntity<String> meanGrade(@PathVariable Long id){
        log.info("meanGrade Aluno get request");
        return ResponseEntity.ok(alunoService.meanGrade(id));
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody AlunoPostResquestBody aluno){
        log.info("save Aluno post request");
        return new ResponseEntity<>(alunoService.save(aluno), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        log.info("delete Aluno delete request");
        alunoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AlunoPutResquestBody alunoPutResquestBody){
        log.info("replace Aluno put request");
        alunoService.replace(alunoPutResquestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="/matricular/{id_aluno}")
    public ResponseEntity<Void> register(@PathVariable Long id_aluno, @RequestParam Long turma){
        log.info("registration Aluno put request");
        alunoService.register(id_aluno, turma);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
