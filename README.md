# Task Manager

Egy full-stack feladatkezelő alkalmazás: Spring Boot REST backend + Angular frontend,
JWT-alapú hitelesítéssel.
A full-stack task management application: Spring Boot REST backend + Angular frontend,
with JWT-based authentication.

Minden felhasználó regisztrálhat, bejelentkezhet, és a saját feladatait kezelheti
(létrehozás, listázás, részletek). A feladatok felhasználóhoz kötöttek — mindenki csak
a sajátjait látja.
Each user can register, log in, and manage their own tasks (create, list, view details).
Tasks are user-scoped — everyone sees only their own.

---

## Magyar

### Technológiák

- Java + Spring Boot (REST API)
- Spring Security + JWT (hitelesítés)
- Spring Data JPA
- PostgreSQL adatbázis
- Angular frontend
- Maven build

### Funkciók

- **Regisztráció** – új felhasználói fiók létrehozása.
- **Bejelentkezés** – JWT token generálása, a böngésző `localStorage`-ában tárolva.
- **Feladatok listája** – a bejelentkezett felhasználó saját feladatai.
- **Feladat létrehozása** – új feladat felvétele.
- **Feladat részletei** – egy feladat teljes adatlapja.
- **Kijelentkezés** – a token törlése; a navbar bejelentkezési állapot szerint változik.

### Hitelesítés (JWT)

- Bejelentkezéskor a szerver egy aláírt JWT tokent állít elő, amely a felhasználó
  azonosítóját és a lejáratot tartalmazza (alapból 24 óra).
- A token a frontend `localStorage`-ában tárolódik.
- Egy Angular interceptor minden kéréshez hozzáadja a tokent az `Authorization` fejlécben.
- A backend oldalon egy `JwtAuthFilter` minden kérésnél ellenőrzi a token érvényességét
  (aláírás, lejárat), és beállítja a biztonsági kontextust.
- Az állapot **stateless** – a szerver nem tárol session-t, csak a token aláírását ellenőrzi.

### Projekt felépítése

```
backend/    Spring Boot REST API (Spring Security, JWT, JPA, PostgreSQL)
frontend/   Angular alkalmazás (AuthService, TaskService, JWT interceptor)
```

### Indítás

**Adatbázis:** hozz létre egy PostgreSQL adatbázist, és állítsd be a kapcsolatot az
`application.yml`-ben (URL, felhasználónév, jelszó). A JWT titkos kulcs és a lejárat
szintén itt konfigurálható:

```yaml
jwt:
  secret: "<hosszú, véletlenszerű kulcs, legalább 256 bit>"
  expiration: 86400000   # 24 óra milliszekundumban
```

**Backend:**
```bash
cd backend
mvn spring-boot:run
```

**Frontend:**
```bash
cd frontend
npm install
ng serve
```

Ezután a frontend a `http://localhost:4200`, a backend a `http://localhost:8080` címen érhető el.

### Megjegyzések

- A feladatok felhasználóhoz kötöttek – mindenki csak a saját feladatait éri el.
- A JWT titkos kulcsot éles környezetben ne tedd verziókövetésbe.

---

## English

### Tech stack

- Java + Spring Boot (REST API)
- Spring Security + JWT (authentication)
- Spring Data JPA
- PostgreSQL database
- Angular frontend
- Maven build

### Features

- **Register** – create a new user account.
- **Login** – generates a JWT token, stored in the browser's `localStorage`.
- **Task list** – the logged-in user's own tasks.
- **Create task** – add a new task.
- **Task details** – a full detail page for a task.
- **Logout** – clears the token; the navbar changes based on auth state.

### Authentication (JWT)

- On login the server issues a signed JWT token containing the user identity and an
  expiration (24 hours by default).
- The token is stored in the frontend's `localStorage`.
- An Angular interceptor attaches the token to every request in the `Authorization` header.
- On the backend a `JwtAuthFilter` validates the token (signature, expiration) on each
  request and sets up the security context.
- The design is **stateless** – the server keeps no session, only verifies the token signature.

### Project structure

```
backend/    Spring Boot REST API (Spring Security, JWT, JPA, PostgreSQL)
frontend/   Angular application (AuthService, TaskService, JWT interceptor)
```

### Running

**Database:** create a PostgreSQL database and configure the connection in
`application.yml` (URL, username, password). The JWT secret and expiration are also
configured here:

```yaml
jwt:
  secret: "<a long, random key, at least 256 bits>"
  expiration: 86400000   # 24 hours in milliseconds
```

**Backend:**
```bash
cd backend
mvn spring-boot:run
```

**Frontend:**
```bash
cd frontend
npm install
ng serve
```

The frontend runs at `http://localhost:4200` and the backend at `http://localhost:8080`.

### Notes

- Tasks are user-scoped – each user can only access their own tasks.
- Never commit the real JWT secret to version control in production.

---

Built as a full-stack practice project: Spring Boot REST, Spring Security with JWT,
an Angular frontend and a user-scoped task CRUD.
