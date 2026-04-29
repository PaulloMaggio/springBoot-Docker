package testes_relacionamentos_docker.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testes_relacionamentos_docker.demo.model.entities.Book;
import testes_relacionamentos_docker.demo.model.entities.dtos.BookDto;
import testes_relacionamentos_docker.demo.services.BookService;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping
    public ResponseEntity<Book> cria(@RequestBody BookDto dto) {
        Book book = service.salva(dto);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> listaTodos() {
        List<Book> list = service.buscaTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> listaPorId(@PathVariable Long id) {
        Book book = service.buscaPorId(id);
        return ResponseEntity.ok().body(book);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Book> atualiza(@PathVariable Long id, @RequestBody BookDto dto){
        Book book = service.atualiza(id, dto);
        return ResponseEntity.ok().body(book);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleta(@PathVariable Long id) {
        service.deleta(id);
        return ResponseEntity.noContent().build();
    }
 }






