package project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.entity.Prova;
import project.service.ProvaService;

import java.math.BigDecimal;

@RestController
@RequestMapping("provas")
@RequiredArgsConstructor
@Slf4j
public class ProvaController {
    private final ProvaService provaService;

    @PostMapping(path = "/{id}")
    public ResponseEntity<Prova> save(@PathVariable Long id, @RequestParam BigDecimal nota){
        return ResponseEntity.ok(provaService.save(id, nota));
    }




}
