package testes_relacionamentos_docker.demo.servicestests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import testes_relacionamentos_docker.demo.model.entities.Author;
import testes_relacionamentos_docker.demo.model.entities.dtos.AuthorDto;
import testes_relacionamentos_docker.demo.repositories.AuthorRepository;
import testes_relacionamentos_docker.demo.services.AuthorService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository repository;

    @InjectMocks
    private AuthorService service;

    private Author author;
    private AuthorDto dto;

    @BeforeEach
    void setUp() {
        author = new Author();
        author.setId(1L);
        author.setName("Machado de Assis");
        author.setBirthDate(LocalDate.of(1839, 6, 21));
        author.setNationality("Brasileiro");

        dto = new AuthorDto();
        dto.setName("Machado de Assis");
        dto.setBirthDate(LocalDate.of(1839, 6, 21));
        dto.setNationality("Brasileiro");
    }

    @Test
    void deveSalvarAutorComSucesso() {
        when(repository.save(any(Author.class))).thenReturn(author);

        Author resultado = service.salva(dto);

        assertNotNull(resultado);
        assertEquals(author.getName(), resultado.getName());
        verify(repository, times(1)).save(any(Author.class));
    }

    @Test
    void deveBuscarTodosOsAutores() {
        when(repository.findAll()).thenReturn(List.of(author));

        List<Author> resultado = service.buscaTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void deveBuscarAutorPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(author));

        Author resultado = service.buscaPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void deveLancarExcecaoQuandoAutorNaoEncontrado() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.buscaPorId(2L));
    }

    @Test
    void deveDeletarAutorComSucesso() {
        doNothing().when(repository).deleteById(1L);

        service.deleta(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}