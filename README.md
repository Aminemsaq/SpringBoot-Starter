# 42 Spring Starter

A small reusable starter for 42 projects using:

- Java 21
- Spring Boot
- Maven
- PostgreSQL
- React + Vite
- Node 24
- Docker Compose
- Make

## Quick start

```bash
chmod +x setup.sh
make setup
source /goinfre/$USER/42-java-env.sh
make up
```

Services:

- Frontend: http://localhost:5173
- Backend: http://localhost:8080
- Health: http://localhost:8080/api/health
- PostgreSQL: localhost:5432

## Local development

Backend:

```bash
make db
make backend
```

Frontend:

```bash
make frontend
```

## Useful commands

```bash
make up
make down
make restart
make logs
make ps
make build
make test
make clean
make fclean
```

## Exercise philosophy

This project intentionally contains almost no business logic.

Recommended progression:

1. Controller
2. Service
3. Repository
4. Entity
5. DTO
6. Validation
7. Exception handling
8. Pagination
9. Authentication
10. Authorization
11. Unit tests
12. Integration tests

Copy this folder whenever you start a new backend/frontend exercise.
