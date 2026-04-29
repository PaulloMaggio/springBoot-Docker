package testes_relacionamentos_docker.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import testes_relacionamentos_docker.demo.model.entities.Author;
import testes_relacionamentos_docker.demo.model.entities.dtos.AuthorDto;
import testes_relacionamentos_docker.demo.services.AuthorService;

import java.util.List;

@Controller
@RequestMapping(value = "/author")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @PostMapping
    public ResponseEntity<Author> cria(@RequestBody AuthorDto dto) {
        Author author = service.salva(dto);
        return ResponseEntity.ok().body(author);
    }

    @GetMapping
    public ResponseEntity<List<Author>> listaTodos() {
        List<Author> lista = service.buscaTodos();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Author> listaPorId(@PathVariable Long id) {
        Author author = service.buscaPorId(id);
        return  ResponseEntity.ok().body(author);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Author> atualiza(@PathVariable Long id, @RequestBody AuthorDto dto) {
        Author author = service.atualiza(id, dto);
        return ResponseEntity.ok().body(author);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleta(@PathVariable Long id) {
        service.deleta(id);
        return ResponseEntity.noContent().build();
    }
}
