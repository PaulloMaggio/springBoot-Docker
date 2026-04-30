# Aplicação Spring Boot com Docker e Implementação de Testes Unitários

Esta aplicação é um ecossistema completo de backend desenvolvido para demonstrar o domínio em tecnologias Java modernas. O projeto foca na gestão de 
uma biblioteca (Livros e Autores), explorando desde a infraestrutura automatizada com Docker até a garantia de qualidade com testes unitários rigorosos.

## 🚀 Objetivos do Projeto
- **Persistência Avançada:** Implementação de relacionamentos complexos entre entidades (Many-to-One e One-to-Many) utilizando Spring Data JPA.
- **Arquitetura de Camadas:** Separação clara de responsabilidades entre Entidades, DTOs, Repositories, Services e Controllers.
- **Infraestrutura como Código:** Uso de Docker e Docker Compose para garantir um ambiente de desenvolvimento idêntico à produção.
- **Qualidade de Software:** Cobertura de testes unitários na camada de serviço para garantir a integridade das regras de negócio.

## 🛠 Tecnologias Utilizadas
- **Linguagem:** Java 25 (LTS)
- **Framework:** Spring Boot 4.0.6
- **Banco de Dados:** PostgreSQL (Containerizado)
- **Ferramentas de Teste:** JUnit 5 e Mockito
- **Gerenciamento de Dependências:** Maven
- **Containerização:** Docker e Docker Compose

## 🐳 Configuração do Ambiente (Docker)
A aplicação utiliza o Docker para gerenciar o banco de dados PostgreSQL, eliminando a necessidade de instalações locais e conflitos de configuração.

Para rodar o banco de dados:
```bash
docker-compose up -d

🧪 Testes Unitários e Qualidade
A camada de serviço (Service Layer) foi 100% blindada com testes utilizando Mockito e JUnit 5. Os testes garantem que:

Novos registros sejam salvos corretamente após validações.

Relacionamentos entre Livros e Autores sejam validados antes da persistência.

Exceções personalizadas sejam lançadas quando IDs não forem encontrados.

A integridade dos Enums (Gêneros Literários) seja mantida como String no banco de dados.

Para executar todos os testes da aplicação:

Bash
mvn test

📋 Principais Funcionalidades
Gestão de Autores: Cadastro completo com validação de data de nascimento e nacionalidade.

Gestão de Livros: Vinculação dinâmica de livros a autores existentes através de authorId.

Tratamento de Dados: Uso de DTOs para proteção da camada de domínio e formatação de datas no padrão brasileiro (dd/MM/yyyy).

Persistência Resiliente: Uso da anotação @Enumerated(EnumType.STRING) para garantir que gêneros literários sejam salvos de forma legível e extensível.

📖 Endpoints Principais
POST /author: Cria um novo autor.

GET /author: Lista autores e seus respectivos livros.

POST /book: Cria um livro vinculado a um autor pelo ID.

GET /book: Lista todos os livros com os detalhes completos do autor aninhado.

Desenvolvido por Paulo Fernando da Silva Magio Desenvolvedor Full Stack em constante evolução.