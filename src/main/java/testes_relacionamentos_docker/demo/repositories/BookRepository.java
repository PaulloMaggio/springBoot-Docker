package testes_relacionamentos_docker.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testes_relacionamentos_docker.demo.model.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}
