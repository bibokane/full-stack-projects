# Full-Stack-Projekte Portfolio

Dieses Repository enthält meine Full-Stack-Projekte, die ich im Rahmen meiner praktischen Softwareentwicklung mit Spring Boot, React und modernen Cloud-Technologien umgesetzt habe. Ziel dieser Anwendungen ist es, praxisnahe Szenarien (z. B. Jobportal, Bibliotheksverwaltung) mit Clean Code, moderner Architektur und realistischen Technologien umzusetzen.

## 📋 Projektübersicht

### 🎯 Job Portal (Spring Boot MVC)
Eine vollständige Jobbörse-Webanwendung mit serverseitigem Rendering, die moderne Spring Boot-Technologien nutzt.

### 📚 Library Management System (React + Spring Boot)
Eine moderne Full-Stack-Bibliotheksverwaltungsanwendung mit React-Frontend und Spring Boot-Backend.

### 🏆 Turnier App (Angular + Spring Boot)
Eine vollständige Full-Stack Turnier-App mit Angular-Frontend und Spring Boot-Backend für 16 Mannschaften mit Gruppenphase und K.O.-System.

---

## 🚀 Projekt 1: Job Portal

### Beschreibung
Das Job Portal ist eine vollständige CRUD-Webanwendung, die als Jobbörse fungiert und sowohl für Jobsuchende als auch für Recruiter entwickelt wurde. Die Anwendung demonstriert moderne Java-Entwicklungspraktiken mit Spring Boot und serverseitigem Rendering.

### ✨ Hauptfunktionen
- **Benutzerverwaltung**: Separate Anmeldung für Jobsuchende und Recruiter
- **Jobsuche**: Globale Suchfunktion mit Filterung nach Position und Standort
- **Jobverwaltung**: Vollständiges CRUD-System für Stellenausschreibungen
- **Profilmanagement**: Individuelle Profile für beide Benutzertypen
- **Sicherheit**: Authentifizierung und Autorisierung mit Spring Security
- **Responsive Design**: Mobile-optimierte Benutzeroberfläche

### 🛠️ Technologie-Stack
- **Backend**: Spring Boot 3.5.3, Java 21
- **Web Framework**: Spring MVC
- **Template Engine**: Thymeleaf
- **Sicherheit**: Spring Security 6
- **Datenbank**: MySQL mit Spring Data JPA & Hibernate 6
- **Frontend**: Bootstrap 5.3.7, jQuery 3.7.1, Font Awesome 6.7.2
- **Build Tool**: Maven
- **Validierung**: Spring Boot Validation

### 📁 Projektstruktur
```
jobportal/
├── src/main/java/com/bibokane/jobportal/
│   ├── controller/          # REST-Controller
│   ├── service/            # Geschäftslogik
│   ├── repository/         # Datenzugriff
│   ├── entity/             # JPA-Entitäten
│   ├── config/             # Konfigurationsklassen
│   └── JobportalApplication.java
├── src/main/resources/
│   ├── templates/          # Thymeleaf-Templates
│   ├── static/            # Statische Ressourcen
│   └── application.properties
└── pom.xml
```

### 🚀 Installation & Ausführung

#### Voraussetzungen
- Java 21 oder höher
- Maven 3.6+
- MySQL 8.0+

#### Setup
1. **Datenbank einrichten**:
   ```sql
   CREATE DATABASE jobportal;
   CREATE USER 'jobportal'@'localhost' IDENTIFIED BY 'jobportal';
   GRANT ALL PRIVILEGES ON jobportal.* TO 'jobportal'@'localhost';
   ```

2. **Projekt starten**:
   ```bash
   cd jobportal
   mvn clean install
   mvn spring-boot:run
   ```

3. **Anwendung aufrufen**: `http://localhost:8080`

---

## 📚 Projekt 2: Library Management System

### Beschreibung
Eine moderne Full-Stack-Bibliotheksverwaltungsanwendung, die React für das Frontend und Spring Boot für das Backend nutzt. Die Anwendung demonstriert die Integration von Single-Page-Applications (SPA) mit RESTful APIs.

### ✨ Hauptfunktionen
- **Buchverwaltung**: Vollständige CRUD-Operationen für Bücher
- **Benutzerauthentifizierung**: OAuth2/JWT-basierte Authentifizierung mit Auth0
- **Rollenbasierte Zugriffskontrolle**: Admin- und Benutzerrollen
- **Buchsuche**: Erweiterte Suchfunktionen mit Filterung
- **Ausleihsystem**: Buchausleihe und -rückgabe
- **Review-System**: Bewertungen und Kommentare für Bücher
- **Responsive UI**: Mobile-first Design mit Bootstrap
- **RESTful API**: Vollständige API für Frontend-Integration

### 🛠️ Technologie-Stack

#### Frontend
- **Framework**: React 19.1.0 mit TypeScript 4.9.5
- **Routing**: React Router DOM 5.3.4
- **Authentifizierung**: Auth0 React SDK 2.4.0
- **UI Framework**: Bootstrap (über CDN)
- **Build Tool**: Create React App
- **HTTP Client**: Axios (implizit)

#### Backend
- **Framework**: Spring Boot 3.5.3, Java 21
- **Sicherheit**: Spring Security mit OAuth2 Resource Server
- **Datenbank**: MySQL mit Spring Data JPA
- **API**: Spring Data REST für automatische REST-Endpoints
- **Code Generation**: Lombok 1.18.30
- **Build Tool**: Maven

### 📁 Projektstruktur
```
library-app/
├── frontend/react-library/
│   ├── src/
│   │   ├── layouts/           # React-Komponenten
│   │   ├── Auth/              # Authentifizierung
│   │   ├── lib/               # Konfiguration
│   │   └── models/            # TypeScript-Modelle
│   ├── public/
│   └── package.json
└── backend/spring-boot-library/
    ├── src/main/java/com/kaneverse/spring_boot_library/
    │   ├── controller/        # REST-Controller
    │   ├── entity/           # JPA-Entitäten
    │   ├── repository/       # Datenzugriff
    │   └── SpringBootLibraryApplication.java
    └── pom.xml
```

### 🚀 Installation & Ausführung

#### Voraussetzungen
- Java 21 oder höher
- Node.js 16+ und npm
- Maven 3.6+
- MySQL 8.0+

#### Backend Setup
1. **Datenbank einrichten**:
   ```sql
   CREATE DATABASE library_db;
   CREATE USER 'library_user'@'localhost' IDENTIFIED BY 'library_password';
   GRANT ALL PRIVILEGES ON library_db.* TO 'library_user'@'localhost';
   ```

2. **Backend starten**:
   ```bash
   cd library-app/backend/spring-boot-library
   mvn clean install
   mvn spring-boot:run
   ```

#### Frontend Setup
1. **Dependencies installieren**:
   ```bash
   cd library-app/frontend/react-library
   npm install
   ```

2. **Frontend starten**:
   ```bash
   npm start
   ```

3. **Anwendung aufrufen**: `http://localhost:3000`

---

## 🏆 Projekt 3: Turnier App

### Beschreibung
Eine vollständige Full-Stack Turnier-App mit Angular-Frontend und Spring Boot-Backend für 16 Mannschaften. Die Anwendung verwaltet ein komplettes Turnier mit Gruppenphase (4 Gruppen à 4 Mannschaften) und K.O.-System (Viertelfinale, Halbfinale, Finale). Die App demonstriert die Integration von Angular SPAs mit RESTful Spring Boot APIs.

### ✨ Hauptfunktionen
- **Mannschaftsverwaltung**: Registrierung und Verwaltung von 16 Mannschaften
- **Gruppenphase**: Automatische Verteilung in 4 Gruppen, jeder gegen jeden
- **K.O.-Phase**: Automatische Weiterleitung der Sieger durch Viertelfinale, Halbfinale und Finale
- **Ergebnisverwaltung**: Eingabe von Spielergebnissen mit automatischer Tabellenaktualisierung
- **Statistik-System**: Automatische Berechnung von Punkten, Toren und Tabellenpositionen
- **Moderne UI**: Responsive, farbenfrohe Benutzeroberfläche mit Animationen
- **RESTful API**: Vollständige API für alle Turnier-Operationen

### 🛠️ Technologie-Stack

#### Frontend
- **Framework**: Angular 17 mit TypeScript
- **Architektur**: Standalone Components
- **Styling**: SCSS mit modernem Design-System
- **HTTP Client**: Angular HttpClient mit RxJS
- **Build Tool**: Angular CLI

#### Backend
- **Framework**: Spring Boot 3.2.0, Java 17
- **Persistenz**: Spring Data JPA mit H2 In-Memory Database
- **API**: RESTful Web Services
- **Architektur**: Service-basierte Architektur (MannschaftService, GruppeService, SpielService, etc.)
- **Validierung**: Custom Exceptions und umfassende Fehlerbehandlung
- **Build Tool**: Maven

### 📁 Projektstruktur
```
turnier-app/
├── src/main/java/com/turnier/
│   ├── controller/          # REST-Controller
│   ├── service/            # Geschäftslogik (mehrere Services)
│   ├── repository/         # Datenzugriff
│   ├── entity/             # JPA-Entitäten
│   ├── exception/          # Custom Exceptions
│   └── TurnierAppApplication.java
├── src/main/resources/
│   └── application.properties
├── frontend/
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/    # Angular-Komponenten
│   │   │   ├── services/       # Angular-Services
│   │   │   └── models/         # TypeScript-Modelle
│   │   ├── styles.scss        # Globale Styles
│   │   └── index.html
│   └── package.json
└── pom.xml
```

### 🚀 Installation & Ausführung

#### Voraussetzungen
- Java 17 oder höher
- Node.js 16+ und npm
- Maven 3.6+

#### Backend Setup
1. **Projekt starten**:
   ```bash
   cd turnier-app
   mvn clean install
   mvn spring-boot:run
   ```

2. **Backend läuft auf**: `http://localhost:8081`

#### Frontend Setup
1. **Dependencies installieren**:
   ```bash
   cd turnier-app/frontend
   npm install
   ```

2. **Frontend starten**:
   ```bash
   npm start
   ```

3. **Anwendung aufrufen**: `http://localhost:4200`

### 🎮 Verwendung

1. **Mannschaften registrieren**: Fügen Sie 16 Mannschaften hinzu
2. **Gruppen erstellen**: Klicken Sie "Gruppen erstellen" (automatische Verteilung in 4 Gruppen)
3. **Gruppenphase starten**: Erstellt alle Gruppenspiele (24 Spiele)
4. **Ergebnisse eingeben**: Geben Sie für jedes Spiel die Tore ein
5. **K.O.-Phase**: Nach Abschluss der Gruppenphase automatisch Viertelfinale → Halbfinale → Finale
6. **Sieger**: Der Turniersieger wird automatisch angezeigt

### 📋 API Endpoints

- `POST /api/turnier/mannschaften` - Mannschaft erstellen
- `GET /api/turnier/mannschaften` - Alle Mannschaften abrufen
- `POST /api/turnier/gruppen/erstellen` - Gruppen erstellen
- `POST /api/turnier/gruppenspiele/erstellen` - Gruppenspiele erstellen
- `PUT /api/turnier/spiele/{id}/ergebnis` - Spielergebnis aktualisieren
- `POST /api/turnier/naechste-runde` - Nächste Runde erstellen
- `GET /api/turnier/status` - Turnierstatus abrufen

---

## 🔧 Entwicklungsumgebung

### Empfohlene Tools
- **IDE**: IntelliJ IDEA oder Visual Studio Code
- **Datenbank**: MySQL Workbench
- **API Testing**: Postman
- **Version Control**: Git
- **Build Tools**: Maven, npm

### Projektkonfiguration
- **Java Version**: 21
- **Spring Boot Version**: 3.5.3
- **React Version**: 19.1.0
- **Node.js Version**: 16+

---

## 📊 Architektur-Übersicht

### Job Portal (MVC-Pattern)
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Thymeleaf     │    │   Spring MVC    │    │   Spring Data   │
│   Templates     │◄──►│   Controllers   │◄──►│   JPA/Hibernate │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         │                       │                       ▼
         │                       │              ┌─────────────────┐
         │                       │              │     MySQL       │
         │                       │              │   Database      │
         │                       │              └─────────────────┘
         │                       │
         ▼                       ▼
┌─────────────────┐    ┌─────────────────┐
│   Bootstrap     │    │  Spring Security│
│   Frontend      │    │  Authentication │
└─────────────────┘    └─────────────────┘
```

### Library App (SPA + REST API)
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   React SPA     │    │   Spring Boot   │    │   Spring Data   │
│   Frontend      │◄──►│   REST API      │◄──►│   JPA/Hibernate │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         │                       │                       ▼
         │                       │              ┌─────────────────┐
         │                       │              │     MySQL       │
         │                       │              │   Database      │
         │                       │              └─────────────────┘
         │                       │
         ▼                       ▼
┌─────────────────┐    ┌─────────────────┐
│   Auth0 OAuth2  │    │  Spring Security│
│   Authentication│    │  OAuth2 Resource│
└─────────────────┘    └─────────────────┘
```

### Turnier App (Angular SPA + REST API)
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Angular SPA   │    │   Spring Boot   │    │   Spring Data   │
│   Frontend      │◄──►│   REST API      │◄──►│   JPA/Hibernate │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         │                       │                       ▼
         │                       │              ┌─────────────────┐
         │                       │              │   H2 In-Memory │
         │                       │              │   Database      │
         │                       │              └─────────────────┘
         │                       │
         ▼                       ▼
┌─────────────────┐    ┌─────────────────┐
│   RxJS          │    │  Service Layer  │
│   Observables   │    │  (Multiple)      │
└─────────────────┘    └─────────────────┘
```

---
### Job Portal
- ✅ **Spring Boot MVC**: Serverseitiges Rendering mit Thymeleaf
- ✅ **Spring Security**: Authentifizierung und Autorisierung
- ✅ **JPA/Hibernate**: Objektrelationale Abbildung
- ✅ **CRUD-Operationen**: Vollständige Datenverwaltung
- ✅ **Formulare & Validierung**: Benutzereingaben verarbeiten
- ✅ **Responsive Design**: Mobile-optimierte UI

### Library Management System
- ✅ **React SPA**: Moderne Frontend-Entwicklung
- ✅ **TypeScript**: Typsichere JavaScript-Entwicklung
- ✅ **RESTful APIs**: Backend-Frontend-Kommunikation
- ✅ **OAuth2/JWT**: Moderne Authentifizierung
- ✅ **Rollenbasierte Sicherheit**: Granulare Zugriffskontrolle
- ✅ **Component-basierte Architektur**: Wiederverwendbare UI-Komponenten

### Turnier App
- ✅ **Angular SPA**: Moderne Frontend-Entwicklung mit Standalone Components
- ✅ **TypeScript**: Typsichere JavaScript-Entwicklung
- ✅ **RESTful APIs**: Backend-Frontend-Kommunikation
- ✅ **Service-basierte Architektur**: Modulare Backend-Struktur
- ✅ **In-Memory Database**: Schnelle Entwicklung mit H2
- ✅ **Modern UI**: Responsive Design mit Animationen und Gradients

## 👨‍💻 Autor

**Habib Kane**  
*Full-Stack Developer*

## 🤝 Beitragen
Beiträge sind willkommen! Bitte erstellen Sie einen Fork des Projekts und senden Sie einen Pull Request für größere Änderungen.
