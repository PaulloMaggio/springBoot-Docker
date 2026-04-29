package testes_relacionamentos_docker.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testes_relacionamentos_docker.demo.model.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {};
