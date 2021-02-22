package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.entity.Aluno;
import project.repository.AlunoRepository;
import project.service.AlunoService;

import java.util.List;

@RestController
@RequestMapping("alunos")
@RequiredArgsConstructor
public class AlunoController {
    private final AlunoService alunoService;

    @GetMapping(path = "{/id}")
    public ResponseEntity<Aluno> findById(@PathVariable long id){
        return ResponseEntity.ok(alunoService.findById(id));
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<Aluno>> findAll(){
        return ResponseEntity.ok(alunoService.findAll());
    }

    @GetMapping(path = "teste")
    public ResponseEntity <String> teste(){
        return ResponseEntity.ok("Foi");
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody Aluno aluno){
        return new ResponseEntity<>(alunoService.save(aluno), HttpStatus.CREATED);
    }
}
