# ProjetoFilmes - Backend

Este é o backend do ProjetoFilmes, uma API em Spring Boot que permite:

- Buscar filmes populares e detalhes via TMDb
- Favoritar filmes
- Avaliar filmes
- Retornar dados personalizados por usuário

## Tecnologias utilizadas

- Java 17+
- Spring Boot (Web, JPA, Validation)
- Hibernate
- Lombok
- TMDb API (via RestTemplate)
- Banco de dados: PostgreSQL
- Maven

## Estrutura do Projeto

```
src/
├── client/              # Cliente para consumir a API da TMDb
├── controller/          # Endpoints REST
├── dto/                 # Objetos de transferência de dados (DTOs)
├── models/              # Entidades JPA
├── repository/          # Interfaces de persistência
├── services/            # Regras de negócio
```

## Como rodar o projeto

### Pré-requisitos

- Java 17+
- Maven
- Ide. ex: IntelliJ

### Passos

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/ProjetoFilmes.git
```

2. Acesse o projeto:

```bash
cd ProjetoFilmes
```

3. Execute o backend:

```bash
./mvnw spring-boot:run
```

4. A API estará disponível em:

```
http://localhost:8080
```

## Configurar chave da TMDb

Este projeto utiliza a API da [The Movie Database (TMDb)](https://www.themoviedb.org/).

Adicione sua chave no arquivo `TmdbClient.java`:

```java
private final String TMDB_API_KEY = "SUA_CHAVE_AQUI";
```

## Principais Endpoints

### Filmes (via TMDb)

- `GET /api/filmes/populares`
- `GET /api/filmes/{id}`

### Favoritos

- `POST /api/favoritos?usuarioId=1&filmeId=123`
- `DELETE /api/favoritos?usuarioId=1&filmeId=123`
- `GET /api/favoritos/{usuarioId}` → Retorna lista de filmes favoritos com dados completos da TMDb
- `GET /api/{usuarioId}/dados` → Retorna favoritos e avaliações do usuário com dados completos

### Avaliações

- `POST /api/avaliacoes`
- `GET /api/avaliacoes/filme/{filmeId}` 
- `DELETE /api/avaliacoes/{id}`

## Observações

- O projeto utiliza `@Builder`, `@Data`, `@AllArgsConstructor` e `@NoArgsConstructor` do Lombok. Certifique-se de que sua IDE está com o plugin do Lombok habilitado.
- A API da TMDb é consumida diretamente pelo backend através do `RestTemplate`.
- Você pode alterar a configuração do banco de dados em `application.properties`.

## Autor

Desenvolvido por Leonardo Cogo, Lucas Cadaval e Dieferson Silveira
