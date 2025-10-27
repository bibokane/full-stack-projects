# 📚 Library Management System

Eine moderne Full-Stack-Bibliotheksverwaltungsanwendung, entwickelt mit React (Frontend) und Spring Boot (Backend). Die Anwendung demonstriert die Integration von Single-Page-Applications mit RESTful APIs und moderne Authentifizierung mit OAuth2/JWT.

## 📋 Projektübersicht

Das **Library Management System** ist eine vollständige digitale Bibliothekslösung, die sowohl für Bibliotheksmitarbeiter als auch für Benutzer entwickelt wurde. Die Anwendung nutzt eine moderne Microservices-Architektur mit separatem Frontend und Backend.

## ✨ Hauptfunktionen

### 📖 **Buchverwaltung**
- **Vollständige CRUD-Operationen** für Bücher
- **Kategorisierung** und **Suchfunktionen**
- **Bestandsverwaltung** mit verfügbaren Exemplaren
- **Buchdetails** mit Beschreibungen und Bildern
- **Automatische Bestandsaktualisierung** bei Ausleihen

### 👥 **Benutzerverwaltung**
- **OAuth2/JWT-Authentifizierung** mit Auth0
- **Rollenbasierte Zugriffskontrolle** (Admin/Benutzer)
- **Sichere API-Zugriffe** mit Token-basierter Authentifizierung
- **Benutzerprofile** und **Ausleihhistorie**

### 🔍 **Such- und Filterfunktionen**
- **Erweiterte Buchsuche** mit verschiedenen Kriterien
- **Kategorie-Filter** für einfache Navigation
- **Suchvorschläge** und **Auto-Complete**
- **Responsive Suchoberfläche**

### 📚 **Ausleihsystem**
- **Buchausleihe** mit automatischer Verfügbarkeitsprüfung
- **Ausleihhistorie** für Benutzer
- **Rückgabe-System** mit automatischer Bestandsaktualisierung
- **Ausleihbeschränkungen** und **Fristen**

### ⭐ **Review-System**
- **Buchbewertungen** und **Kommentare**
- **Sterne-Bewertungssystem**
- **Review-Historie** und **Moderation**

### 🛠️ **Admin-Funktionen**
- **Buchverwaltung** (Hinzufügen, Bearbeiten, Löschen)
- **Bestandsverwaltung** (Mengen anpassen)
- **Nachrichtenverwaltung** für Benutzer
- **Statistiken** und **Berichte**

## 🛠️ Technologie-Stack

### **Frontend (React)**
- **React 19.1.0** - UI-Framework
- **TypeScript 4.9.5** - Typsichere Entwicklung
- **React Router DOM 5.3.4** - Client-side Routing
- **Auth0 React SDK 2.4.0** - OAuth2-Authentifizierung
- **Bootstrap** - CSS-Framework (über CDN)
- **Axios** - HTTP-Client für API-Kommunikation

### **Backend (Spring Boot)**
- **Spring Boot 3.5.3** - Hauptframework
- **Java 21** - Programmiersprache
- **Spring Security** - Sicherheitsframework
- **OAuth2 Resource Server** - JWT-Token-Verarbeitung
- **Spring Data JPA** - Datenzugriff
- **Spring Data REST** - Automatische REST-APIs
- **MySQL** - Datenbank
- **Lombok 1.18.30** - Code-Generierung

### **Tools & Build**
- **Maven** - Backend Dependency Management
- **npm** - Frontend Package Management
- **Create React App** - Frontend Build Tool

## 📁 Projektstruktur

```
library-app/
├── frontend/react-library/
│   ├── public/
│   │   ├── index.html
│   │   ├── favicon.ico
│   │   └── manifest.json
│   ├── src/
│   │   ├── layouts/                    # React-Komponenten
│   │   │   ├── HomePage/              # Startseite
│   │   │   │   ├── components/
│   │   │   │   │   ├── Carousel.tsx
│   │   │   │   │   ├── ExploreTopBooks.tsx
│   │   │   │   │   ├── Heros.tsx
│   │   │   │   │   └── LibraryServices.tsx
│   │   │   │   └── HomePage.tsx
│   │   │   ├── SearchBooksPage/       # Buchsuche
│   │   │   ├── BookCheckoutPage/      # Buchausleihe
│   │   │   │   ├── components/
│   │   │   │   └── ReviewListPage/
│   │   │   ├── ShelfPage/             # Benutzer-Bereich
│   │   │   ├── MessagesPage/          # Nachrichten
│   │   │   ├── ManageLibraryPage/     # Admin-Bereich
│   │   │   │   ├── components/
│   │   │   │   │   ├── AddNewBook.tsx
│   │   │   │   │   ├── AdminMessages.tsx
│   │   │   │   │   └── ChangeQuantityOfBooks.tsx
│   │   │   │   └── ManageLibraryPage.tsx
│   │   │   └── NavbarAndFooter/       # Navigation
│   │   │       ├── Navbar.tsx
│   │   │       └── Footer.tsx
│   │   ├── Auth/                      # Authentifizierung
│   │   │   └── LoginPage.jsx
│   │   ├── lib/                       # Konfiguration
│   │   │   └── authOConfig.ts
│   │   ├── models/                    # TypeScript-Modelle
│   │   │   ├── Book.ts
│   │   │   ├── Review.ts
│   │   │   ├── ShelfCurrentLoansResponse.ts
│   │   │   └── ShelfCurrentLoansResponse.ts
│   │   ├── App.tsx                    # Hauptkomponente
│   │   ├── App.css
│   │   ├── index.tsx
│   │   └── index.css
│   ├── package.json
│   └── tsconfig.json
└── backend/spring-boot-library/
    ├── src/main/java/com/kaneverse/spring_boot_library/
│   ├── controller/                # REST-Controller
│   │   ├── AdminController.java
│   │   ├── BookController.java
│   │   ├── MessagesController.java
│   │   └── ReviewController.java
│   ├── service/                   # Geschäftslogik
│   │   ├── AdminService.java
│   │   ├── BookService.java
│   │   ├── MessagesService.java
│   │   └── ReviewService.java
│   ├── dao/                       # Repository (Datenzugriff)
│   │   ├── BookRepository.java
│   │   ├── CheckoutRepository.java
│   │   ├── HistoryRepository.java
│   │   ├── MessageRepository.java
│   │   └── ReviewRepository.java
│   ├── entity/                    # JPA-Entitäten
│   │   ├── Book.java
│   │   ├── Checkout.java
│   │   ├── History.java
│   │   ├── Message.java
│   │   └── Review.java
│   ├── config/                    # Konfiguration
│   │   ├── MyDataRestConfig.java
│   │   └── SecurityConfiguration.java
│   ├── requestmodels/             # Request-Modelle
│   │   ├── AddBookRequest.java
│   │   ├── AdminQuestionRequest.java
│   │   └── ReviewRequest.java
│   ├── responsemodels/            # Response-Modelle
│   │   └── ShelfCurrentLoansResponse.java
│   └── utils/                     # Utility-Klassen
│       └── ExtractJWT.java
    │   └── SpringBootLibraryApplication.java
    ├── src/main/resources/
    │   └── application.properties
    └── pom.xml
```

## 🚀 Installation & Ausführung

### **Voraussetzungen**
- **Java 21** oder höher
- **Node.js 16+** und **npm**
- **Maven 3.6+**
- **MySQL 8.0+**
- **Auth0-Konto** (für Authentifizierung)

### **Backend Setup**

#### 1. **Datenbank einrichten**
```sql
-- MySQL-Datenbank erstellen
CREATE DATABASE library_db;

-- Benutzer erstellen
CREATE USER 'library_user'@'localhost' IDENTIFIED BY 'library_password';

-- Berechtigungen vergeben
GRANT ALL PRIVILEGES ON library_db.* TO 'library_user'@'localhost';
FLUSH PRIVILEGES;
```

#### 2. **Backend konfigurieren**
```bash
cd library-app/backend/spring-boot-library
```

#### 3. **Dependencies installieren und starten**
```bash
mvn clean install
mvn spring-boot:run
```

**Backend läuft auf**: `http://localhost:8080`

### **Frontend Setup**

#### 1. **Auth0 konfigurieren**
1. Auth0-Dashboard öffnen
2. Neue Anwendung erstellen (Single Page Application)
3. Domain und Client ID in `src/lib/authOConfig.ts` eintragen

#### 2. **Frontend starten**
```bash
cd library-app/frontend/react-library
npm install
npm start
```

**Frontend läuft auf**: `http://localhost:3000`

## 🔧 Konfiguration

### **Backend-Konfiguration**
```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=library_user
spring.datasource.password=library_password
spring.jpa.hibernate.ddl-auto=update
```

### **Frontend-Konfiguration**
```typescript
// src/lib/authOConfig.ts
export const auth0Config = {
  domain: 'your-auth0-domain.auth0.com',
  clientId: 'your-client-id',
  redirectUri: 'http://localhost:3000',
  audience: 'your-api-identifier',
  scope: 'openid profile email'
};
```

## 📊 Datenmodell

### **Hauptentitäten**
- **Book**: Bücher mit Titel, Autor, Beschreibung, Kategorie
- **Checkout**: Aktive Ausleihen mit Benutzer und Datum
- **History**: Ausleihhistorie für Statistiken
- **Review**: Buchbewertungen und Kommentare

### **Beziehungen**
- Ein Book kann mehrere Checkouts haben
- Ein Book kann mehrere Reviews haben
- Ein User kann mehrere Checkouts haben
- Ein User kann mehrere Reviews schreiben

## 🔐 Sicherheit

### **OAuth2/JWT-Authentifizierung**
- **Auth0-Integration** für Benutzerauthentifizierung
- **JWT-Token** für API-Authentifizierung
- **Rollenbasierte Autorisierung** (Admin/Benutzer)
- **Sichere API-Endpunkte** mit Token-Validierung

### **Spring Security Konfiguration**
- **OAuth2 Resource Server** für JWT-Verarbeitung
- **CORS-Konfiguration** für Frontend-Integration
- **Sichere Endpunkte** mit Authentifizierung

## 🎨 Benutzeroberfläche

### **Seiten-Übersicht**
- **Startseite** (`/home`): Übersicht und Navigation
- **Buchsuche** (`/search`): Erweiterte Suchfunktionen
- **Buchdetails** (`/checkout/:bookId`): Ausleihe und Details
- **Reviews** (`/reviewlist/:bookId`): Bewertungen anzeigen
- **Mein Bereich** (`/shelf`): Ausleihen und Historie
- **Nachrichten** (`/messages`): Benutzer-Nachrichten
- **Admin-Bereich** (`/admin`): Bibliotheksverwaltung

### **Responsive Design**
- **Mobile-first** Ansatz
- **Bootstrap-Integration** für konsistentes Design
- **Touch-optimierte** Bedienelemente
- **Progressive Web App** Features


## 📈 Performance & Monitoring

### **Frontend-Optimierungen**
- **Code Splitting** mit React Router
- **Lazy Loading** für Komponenten
- **Bundle-Optimierung** mit Create React App
- **Caching-Strategien** für API-Aufrufe

### **Backend-Optimierungen**
- **JPA Lazy Loading** für optimierte Abfragen
- **Connection Pooling** für Datenbankverbindungen
- **REST-API-Caching** mit Spring Data REST
- **Pagination** für große Datensätze

## 🚀 Deployment

### **Frontend Build**
```bash
cd frontend/react-library
npm run build
```

### **Backend Build**
```bash
cd backend/spring-boot-library
mvn clean package
```

### **Docker-Container (optional)**
```dockerfile
# Frontend
FROM node:16-alpine
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
EXPOSE 3000
CMD ["npm", "start"]

# Backend
FROM openjdk:21-jdk-slim
COPY target/spring-boot-library-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 🔧 Entwicklung

### **Code-Struktur**
- **Component-basierte Architektur** (React)
- **Service-Layer-Pattern** (Spring Boot)
- **Repository-Pattern** (JPA)
- **RESTful API-Design**

### **Best Practices**
- **TypeScript** für typsichere Entwicklung
- **ESLint** für Code-Qualität
- **Responsive Design** mit Bootstrap
- **Error Boundaries** für Fehlerbehandlung
- **Loading States** für bessere UX

## 📝 API-Dokumentation

### **Hauptendpunkte**

#### **Buchverwaltung (Spring Data REST)**
- `GET /api/books` - Alle Bücher abrufen (automatisch generiert)
- `GET /api/books/{id}` - Buchdetails abrufen (automatisch generiert)

#### **Ausleihverwaltung**
- `PUT /api/books/secure/checkout?bookId={id}` - Buch ausleihen
- `PUT /api/books/secure/return?bookId={id}` - Buch zurückgeben
- `PUT /api/books/secure/renew/loan?bookId={id}` - Ausleihe verlängern
- `GET /api/books/secure/currentloans` - Aktuelle Ausleihen abrufen
- `GET /api/books/secure/currentloans/count` - Anzahl aktueller Ausleihen
- `GET /api/books/secure/ischeckedout/byuser?bookId={id}` - Prüfen ob Buch ausgeliehen

#### **Review-Verwaltung**
- `POST /api/reviews/secure` - Review erstellen
- `GET /api/reviews/secure/user/book?bookId={id}` - Prüfen ob User Review geschrieben hat

#### **Nachrichtenverwaltung**
- `POST /api/messages/secure/add/message` - Nachricht senden
- `PUT /api/messages/secure/admin/message` - Admin-Antwort senden

### **Admin-Endpunkte**
- `POST /api/admin/secure/add/book` - Buch hinzufügen
- `PUT /api/admin/secure/increase/book/quantity?bookId={id}` - Bestand erhöhen
- `PUT /api/admin/secure/decrease/book/quantity?bookId={id}` - Bestand verringern
- `DELETE /api/admin/secure/delete/book?bookId={id}` - Buch löschen

## 🤝 Beitragen

1. Fork des Projekts erstellen
2. Feature-Branch erstellen (`git checkout -b feature/AmazingFeature`)
3. Änderungen committen (`git commit -m 'Add some AmazingFeature'`)
4. Branch pushen (`git push origin feature/AmazingFeature`)
5. Pull Request erstellen

## 📄 Lizenz

Dieses Projekt dient zu Demonstrations- und Lernzwecken.

## 👨‍💻 Autor

**Habib Kane**  
*Full-Stack Developer*

