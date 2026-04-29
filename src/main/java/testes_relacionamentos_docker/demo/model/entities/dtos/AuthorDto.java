package testes_relacionamentos_docker.demo.model.entities.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import testes_relacionamentos_docker.demo.model.entities.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthorDto {
    String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate birthDate;
    String nationality;

    public List<Book> list = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
