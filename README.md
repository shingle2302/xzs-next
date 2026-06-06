# xzs-next — Mindskip Exam System (COLA DDD Refactoring)

[![License](https://img.shields.io/badge/license-Apache%202.0-blue)](LICENSE)

**xzs-next** is a deep refactoring of the [Mindskip Open-Source Exam System (xzs)](https://github.com/mindskip/xzs). The original xzs is a well-established Java + Vue exam system with a traditional three-layer architecture that suffered from module coupling and scattered domain logic as features grew.

xzs-next retains full feature parity while systematically refactoring the codebase using **COLA 4.0 Clean Architecture** and **Domain-Driven Design (DDD)** principles, aiming for better maintainability, testability, and code clarity.

> English | [中文](README.zh-CN.md)

---

## Refactoring Goals

| Dimension | Original (xzs) | Refactored (xzs-next) |
|-----------|---------------|----------------------|
| Architecture | Traditional 3-layer | COLA 4.0 Clean Architecture |
| Java | 8 | 17 |
| Spring Boot | 2.x | 3.2 |
| DI | @Autowired field injection | Constructor injection |
| ORM | MyBatis + XML | MyBatis-Plus 3.5 |
| Transaction | Scattered in Service | Unified in Application Service |
| Domain Logic | Dispersed in Service/Controller | Domain Service + Aggregate Root |
| Persistence | Mapper exposed to Service | Gateway interface isolation |
| Event-driven | None | ApplicationEvent async handling |
| Frontend | Vue 2 + Element UI | Vue 3 + Vite + TypeScript + Ant Design Vue 4 |
| Build | Maven (single module) | Maven (multi-module) |
| DataSource | Druid | HikariCP |

## Tech Stack

**Backend** — Java 17, Spring Boot 3.2, MyBatis-Plus 3.5, PostgreSQL, MapStruct, JWT

**Frontend** — Vue 3, Vite, TypeScript, Ant Design Vue 4, Pinia, ECharts, Axios

**Deployment** — Docker, Docker Compose, Nginx

## Module Layout

```
xzs-next/
├── xzs-adapter/        # Adapter layer — Controllers, DTOs, Assemblers
├── xzs-application/    # Application layer — App Services, Event Listeners
├── xzs-domain/         # Domain layer — Aggregates, Domain Services, Gateway interfaces
├── xzs-infrastructure/ # Infrastructure — MyBatis-Plus impl, Mappers, POs
├── xzs-common/         # Common — Utilities, Enums
├── xzs-start/          # Bootstrap — Spring Boot entry, config
├── xzs-web/            # Frontend
│   ├── admin/          #   Admin panel (Vue 3)
│   └── student/        #   Student portal (Vue 3)
└── sql/                # Database initialization scripts
```

## Quick Start

### Prerequisites
- JDK 17+
- Maven 3.8+
- PostgreSQL 14+
- Node.js 18+
- pnpm (recommended) or npm

### Backend

```bash
# 1. Create database and run init script
psql -U postgres -d xzs -f sql/init.sql

# 2. Edit config (database connection etc.)
#    vim xzs-start/src/main/resources/application-dev.yml

# 3. Build and run
mvn clean package -DskipTests
java -jar xzs-start/target/xzs-start-4.0.0.jar
```

### Frontend

```bash
# Admin panel
cd xzs-web/admin
pnpm install
pnpm run dev    # Dev mode, default port 8001

# Student portal
cd xzs-web/student
pnpm install
pnpm run dev    # Dev mode, default port 8002
```

### Docker

```bash
docker compose up -d
```

## API Docs

After backend starts: `http://localhost:8000/doc.html`

## Features

### Student
- Login / Register
- Task center (admin-assigned grade-level tasks, one attempt per user)
- Fixed exams (repetitive practice, self-grading)
- Timed exams (repetitive within time limit)
- Exam history and score review
- Wrong answer notebook
- Profile management
- Message center

### Admin
- Dashboard (statistics)
- User management (students / admins)
- Subject management
- Exam paper management (fixed / timed / task)
- Question management (single choice / multiple choice / true-false / fill-blank / short answer)
- Task management
- Message broadcasting
- Audit log

### WeChat Mini Program
- Login / Register (auto-bind WeChat account)
- Task center
- Exam
- Exam history
- Wrong answer notebook
- Profile

## License

[Apache License 2.0](LICENSE)
