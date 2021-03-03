package project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import project.entity.Aluno;
import project.request.AlunoPostResquestBody;
import project.request.AlunoPutResquestBody;
import project.response.AlunoGetResponseBody;
import project.service.AlunoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {
    private final AlunoService alunoService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<AlunoGetResponseBody> findById(@PathVariable long id) {
        log.info("findById Aluno get request");
        return ResponseEntity.ok(alunoService.findById(id));
    }

    //Apenas para debug
    @GetMapping(path = "all")
    public ResponseEntity<List<Aluno>> findAll() {
        log.info("findAll Aluno get request");
        return ResponseEntity.ok(alunoService.findAll());
    }

    @GetMapping(path="/{id}/media")
    public  ResponseEntity<String> meanGrade(@PathVariable Long id){
        log.info("meanGrade Aluno get request");
        return ResponseEntity.ok(alunoService.meanGrade(id));
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody AlunoPostResquestBody aluno){
        log.info("save Aluno post request");
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("").buildAndExpand(alunoService.save(aluno)).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "{id}/trancar")
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

    @PutMapping(path="/{id}/matricular")
    public ResponseEntity<Void> register(@PathVariable Long id, @RequestParam Long turma){
        log.info("registration Aluno put request");
        alunoService.register(id, turma);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
