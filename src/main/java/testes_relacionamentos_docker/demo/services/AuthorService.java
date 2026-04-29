package testes_relacionamentos_docker.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testes_relacionamentos_docker.demo.model.entities.Author;
import testes_relacionamentos_docker.demo.model.entities.dtos.AuthorDto;
import testes_relacionamentos_docker.demo.repositories.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    public Author salva(AuthorDto dto){
        Author author = new Author();
        author.setName(dto.getName());
        author.setBirthDate(dto.getBirthDate());
        author.setNationality(dto.getNationality());

        return repository.save(author);
    }

    public List<Author> buscaTodos() {
        List<Author> lista = repository.findAll();
        return lista;
    }

    public Author buscaPorId(Long id) {
        Author author = new Author();
        return author = repository.findById(id)
             .orElseThrow(() -> new RuntimeException("Id não encontrado: " + id));
    }

    public Author atualiza(Long id, AuthorDto dto) {
        Author author = buscaPorId(id);
        author.setName(dto.getName());
        author.setBirthDate(dto.getBirthDate());
        author.setNationality(dto.getNationality());
        return repository.save(author);
    }

    public void deleta(Long id) {
        repository.deleteById(id);
    }
    }


