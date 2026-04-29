package testes_relacionamentos_docker.demo.servicestests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import testes_relacionamentos_docker.demo.model.entities.Author;
import testes_relacionamentos_docker.demo.model.entities.Book;
import testes_relacionamentos_docker.demo.model.entities.dtos.BookDto;
import testes_relacionamentos_docker.demo.model.entities.enums.GenderEnum;
import testes_relacionamentos_docker.demo.repositories.AuthorRepository;
import testes_relacionamentos_docker.demo.repositories.BookRepository;
import testes_relacionamentos_docker.demo.services.BookService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    private Author author;
    private Book book;
    private BookDto dto;

    @BeforeEach
    void setUp() {
        author = new Author();
        author.setId(1L);
        author.setName("Machado de Assis");

        book = new Book();
        book.setId(1L);
        book.setTitle("Dom Casmurro");
        book.setGender(GenderEnum.DRAMA);
        book.setRelease(LocalDate.of(1899, 1, 15));
        book.setAuthor(author);

        dto = new BookDto();
        dto.setTitle("Dom Casmurro");
        dto.setGender(GenderEnum.DRAMA);
        dto.setRelease(LocalDate.of(1899, 1, 15));
        dto.setAuthorId(1L);
    }

    @Test
    void deveSalvarLivroComSucesso() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book resultado = bookService.salva(dto);

        assertNotNull(resultado);
        assertEquals("Dom Casmurro", resultado.getTitle());
        assertEquals(1L, resultado.getAuthor().getId());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void deveLancarExcecaoAoSalvarLivroComAutorInexistente() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.salva(dto));
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void deveBuscarTodosOsLivros() {
        when(bookRepository.findAll()).thenReturn(List.of(book));

        List<Book> resultado = bookService.buscaTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
    }

    @Test
    void deveAtualizarLivroComSucesso() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book resultado = bookService.atualiza(1L, dto);

        assertNotNull(resultado);
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    void deveDeletarLivroComSucesso() {
        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleta(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}