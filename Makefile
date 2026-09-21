PROJECT := 42-starter

.PHONY: help setup up down restart logs ps backend frontend build test clean fclean db

help:
	@echo "42 Spring Starter"
	@echo ""
	@echo "  make setup      First-time local setup"
	@echo "  make up         Start backend + frontend + PostgreSQL"
	@echo "  make down       Stop containers"
	@echo "  make restart    Restart containers"
	@echo "  make logs       Follow all logs"
	@echo "  make ps         Show containers"
	@echo "  make backend    Run Spring Boot locally"
	@echo "  make frontend   Run React locally"
	@echo "  make build      Build backend"
	@echo "  make test       Run backend tests"
	@echo "  make clean      Clean build files"
	@echo "  make fclean     Stop and remove containers/volumes"

setup:
	@chmod +x setup.sh 2>/dev/null || true
	@./setup.sh

up:
	docker compose up -d --build

down:
	docker compose down

restart:
	docker compose restart

logs:
	docker compose logs -f

ps:
	docker compose ps

db:
	docker compose up -d db

backend:
	cd backend && ./mvnw spring-boot:run

frontend:
	cd frontend && npm install && npm run dev

build:
	cd backend && ./mvnw clean package

test:
	cd backend && ./mvnw test

clean:
	rm -rf backend/target frontend/dist

fclean:
	docker compose down -v --remove-orphans
	rm -rf backend/target frontend/dist
