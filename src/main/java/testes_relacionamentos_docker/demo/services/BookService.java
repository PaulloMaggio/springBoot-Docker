package testes_relacionamentos_docker.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testes_relacionamentos_docker.demo.model.entities.Author;
import testes_relacionamentos_docker.demo.model.entities.Book;
import testes_relacionamentos_docker.demo.model.entities.dtos.BookDto;
import testes_relacionamentos_docker.demo.repositories.AuthorRepository;
import testes_relacionamentos_docker.demo.repositories.BookRepository;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book salva(BookDto dto){
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setGender(dto.getGender());
        book.setRelease(dto.getRelease());


        if (dto.getAuthorId() != null) {
            Author author = authorRepository.findById(dto.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
            book.setAuthor(author);
        }

        return repository.save(book);
    }

    public List<Book> buscaTodos() {
        List<Book> list = repository.findAll();
        return list;
    }

    public Book buscaPorId(Long id) {
        Book book = new Book();
        return book = repository.findById(id).
            orElseThrow(() -> new RuntimeException("Id não encontrado: " + id));
    }

    public Book atualiza(Long id, BookDto dto) {
        Book book = buscaPorId(id);
        book.setTitle(dto.getTitle());
        book.setGender(dto.getGender());
        book.setRelease(dto.getRelease());

        if (dto.getAuthorId() != null) {
            Author author = authorRepository.findById(dto.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
            book.setAuthor(author);
        }

        return repository.save(book);
    }

    public void deleta(Long id) {
        repository.deleteById(id);
    }
}
