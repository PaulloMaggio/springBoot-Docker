package testes_relacionamentos_docker.demo.model.entities;

import jakarta.persistence.*;
import testes_relacionamentos_docker.demo.model.entities.enums.GenderEnum;
import java.time.LocalDate;

@Entity
@Table(name = "book_tb")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column(nullable = false)
    private LocalDate release;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book() {}

    public Book(Long id, String title, GenderEnum gender, LocalDate release, Author author) {
        this.id = id;
        this.title = title;
        this.gender = gender;
        this.release = release;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", gender=" + gender +
                ", release=" + release +
                ", author=" + (author != null ? author.getId() : "null") +
                '}';
    }
}