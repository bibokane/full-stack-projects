# 🎯 Job Portal - HotDevJobs.com

Eine vollständige Jobbörse-Webanwendung, entwickelt mit Spring Boot und modernen Java-Technologien. Die Anwendung bietet eine Plattform für Jobsuchende und Recruiter, um Stellenausschreibungen zu verwalten und zu durchsuchen.

## 📋 Projektübersicht

**HotDevJobs.com** ist eine professionelle Jobbörse, die moderne Web-Technologien nutzt, um eine nahtlose Erfahrung für beide Benutzergruppen zu bieten. Die Anwendung implementiert serverseitiges Rendering mit Thymeleaf und bietet umfassende Such- und Filterfunktionen mit verschiedenen Jobtypen und Remote-Optionen.

## ✨ Hauptfunktionen

### 🔍 **Jobsuche & -verwaltung**
- **Globale Jobsuche** mit Filterung nach Position und Standort
- **Erweiterte Suchfilter**: Vollzeit, Teilzeit
- **Jobdetails** mit vollständigen Beschreibungen und Anforderungen

### 👥 **Benutzerverwaltung**
- **Duale Benutzertypen**: Jobsuchende und Recruiter
- **Sichere Authentifizierung** mit Spring Security
- **Profilmanagement** für beide Benutzergruppen
- **Rollenbasierte Zugriffskontrolle**

### 📝 **Stellenausschreibungen**
- **CRUD-Operationen** für Stellenausschreibungen
- **Job-Posting** mit detaillierten Beschreibungen
- **Bewerbungsmanagement** für Jobsuchende
- **Gespeicherte Jobs** für spätere Bewerbungen

### 🎨 **Benutzeroberfläche**
- **Responsive Design** mit Bootstrap 5
- **Mobile-optimierte** Benutzeroberfläche
- **Intuitive Navigation** und Benutzerführung
- **Moderne UI-Komponenten** mit Font Awesome Icons

## 🛠️ Technologie-Stack

### **Backend**
- **Spring Boot 3.5.3** - Hauptframework
- **Java 21** - Programmiersprache
- **Spring MVC** - Web-Framework
- **Spring Security 6** - Authentifizierung & Autorisierung
- **Spring Data JPA** - Datenzugriff
- **Hibernate 6** - ORM-Framework
- **Thymeleaf** - Template Engine
- **MySQL** - Datenbank

### **Frontend**
- **Thymeleaf** - Template Engine für serverseitiges Rendering
- **Bootstrap 5.3.7** - CSS-Framework
- **jQuery 3.7.1** - JavaScript-Bibliothek
- **Font Awesome 6.7.2** - Icon-Bibliothek
- **Responsive Design** - Mobile-first Ansatz

### **Tools & Build**
- **Maven** - Dependency Management & Build Tool
- **Spring Boot DevTools** - Entwicklungstools
- **WebJars** - Frontend-Dependencies

## 📁 Projektstruktur

```
jobportal/
├── src/main/java/com/bibokane/jobportal/
│   ├── controller/              # REST-Controller
│   │   ├── HomeController.java
│   │   ├── JobPostActivityController.java
│   │   ├── JobSeekerApplyController.java
│   │   ├── JobSeekerProfileController.java
│   │   ├── JobSeekerSaveController.java
│   │   ├── RecruiterProfileController.java
│   │   └── UsersController.java
│   ├── service/                 # Geschäftslogik
│   │   ├── JobPostActivityService.java
│   │   ├── JobSeekerApplyService.java
│   │   ├── JobSeekerProfileService.java
│   │   ├── JobSeekerSaveService.java
│   │   ├── RecruiterProfileService.java
│   │   ├── UsersService.java
│   │   └── UsersTypeService.java
│   ├── repository/              # Datenzugriff
│   │   ├── JobPostActivityRepository.java
│   │   ├── JobSeekerApplyRepository.java
│   │   ├── JobSeekerProfileRepository.java
│   │   ├── JobSeekerSaveRepository.java
│   │   └── UsersTypeRepository.java
│   ├── entity/                  # JPA-Entitäten
│   │   ├── JobPostActivity.java
│   │   ├── JobSeekerApply.java
│   │   ├── JobSeekerSave.java
│   │   ├── RecruiterProfile.java
│   │   ├── Users.java
│   │   └── UsersType.java
│   ├── config/                  # Konfiguration
│   │   ├── WebSecurityConfig.java
│   │   ├── MvcConfig.java
│   │   └── CustomAuthenticationSuccessHandler.java
│   ├── util/                    # Utility-Klassen
│   │   ├── FileUploadUtil.java
│   │   └── FileDownloadUtil.java
│   └── JobportalApplication.java
├── src/main/resources/
│   ├── templates/               # Thymeleaf-Templates
│   │   ├── index.html          # Startseite
│   │   ├── login.html          # Anmeldung
│   │   ├── register.html       # Registrierung
│   │   ├── dashboard.html      # Dashboard
│   │   ├── global-search.html  # Jobsuche
│   │   ├── add-jobs.html       # Job hinzufügen
│   │   ├── job-details.html    # Jobdetails
│   │   ├── job-seeker-profile.html
│   │   ├── recruiter_profile.html
│   │   └── saved-jobs.html
│   ├── static/                  # Statische Ressourcen
│   │   ├── css/                # Stylesheets
│   │   ├── js/                 # JavaScript
│   │   ├── assets/             # Bilder & Icons
│   │   └── fonts/              # Schriftarten
│   └── application.properties  # Konfiguration
└── pom.xml                     # Maven-Konfiguration
```

## 🚀 Installation & Ausführung

### **Voraussetzungen**
- **Java 21** oder höher
- **Maven 3.6+**
- **MySQL 8.0+**
- **IDE** (IntelliJ IDEA, Eclipse, VS Code)

### **Setup-Schritte**

#### 1. **Datenbank einrichten**
```sql
-- MySQL-Datenbank erstellen
CREATE DATABASE jobportal;

-- Benutzer erstellen
CREATE USER 'jobportal'@'localhost' IDENTIFIED BY 'jobportal';

-- Berechtigungen vergeben
GRANT ALL PRIVILEGES ON jobportal.* TO 'jobportal'@'localhost';
FLUSH PRIVILEGES;
```

#### 2. **Projekt klonen und konfigurieren**
```bash
# Projekt herunterladen
git clone <repository-url>
cd jobportal

# Dependencies installieren
mvn clean install
```

#### 3. **Datenbankverbindung prüfen**
Überprüfen Sie die `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobportal
spring.datasource.username=jobportal
spring.datasource.password=jobportal
```

#### 4. **Anwendung starten**
```bash
# Mit Maven
mvn spring-boot:run

# Oder JAR-Datei erstellen und ausführen
mvn clean package
java -jar target/jobportal-0.0.1-SNAPSHOT.jar
```

#### 5. **Anwendung aufrufen**
- **URL**: `http://localhost:8080`
- **Standard-Port**: 8080

## 🔧 Konfiguration

### **Datenbank-Konfiguration**
```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobportal
spring.datasource.username=jobportal
spring.datasource.password=jobportal
spring.jpa.hibernate.ddl-auto=update
```

### **Datei-Upload-Konfiguration**
```properties
spring.servlet.multipart.enabled=true
spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=10MB
```

## 📊 Datenmodell

### **Hauptentitäten**
- **Users**: Benutzerverwaltung (Jobsuchende & Recruiter)
- **JobPostActivity**: Stellenausschreibungen
- **JobSeekerApply**: Bewerbungen
- **JobSeekerSave**: Gespeicherte Jobs
- **RecruiterProfile**: Recruiter-Profile
- **JobSeekerProfile**: Jobsuchende-Profile

### **Beziehungen**
- Ein User kann mehrere JobPostActivity erstellen (Recruiter)
- Ein User kann sich auf mehrere Jobs bewerben (Jobsuchende)
- Ein User kann mehrere Jobs speichern (Jobsuchende)

## 🔐 Sicherheit

### **Spring Security Konfiguration**
- **Authentifizierung**: Formular-basierte Anmeldung
- **Autorisierung**: Rollenbasierte Zugriffskontrolle
- **Sessions**: HTTP-Session-Management
- **CSRF-Schutz**: Aktiviert
- **Password-Encoding**: BCrypt

### **Benutzerrollen**
- **ROLE_JOBSEEKER**: Jobsuchende
- **ROLE_RECRUITER**: Recruiter

## 🎨 Benutzeroberfläche

### **Seiten-Übersicht**
- **Startseite** (`/`): Jobsuche und Navigation
- **Anmeldung** (`/login`): Benutzeranmeldung
- **Registrierung** (`/register`): Benutzerregistrierung
- **Dashboard** (`/dashboard/`): Hauptarbeitsbereich
- **Jobsuche** (`/global-search/`): Erweiterte Jobsuche
- **Job hinzufügen** (`/dashboard/add`): Stellenausschreibung erstellen
- **Profil** (`/profile`): Benutzerprofil verwalten

### **Responsive Design**
- **Mobile-first** Ansatz
- **Bootstrap 5** Grid-System
- **Flexible Layouts** für alle Bildschirmgrößen
- **Touch-optimierte** Bedienelemente


## 📈 Performance & Monitoring

### **Entwicklungstools**
- **Spring Boot DevTools**: Hot-Reload
- **H2 Console**: Datenbank-Debugging (optional)
- **Actuator**: Health-Checks und Metriken

### **Optimierungen**
- **JPA Lazy Loading**: Optimierte Datenbankabfragen
- **Connection Pooling**: Effiziente Datenbankverbindungen
- **Static Resource Caching**: Optimierte Ladezeiten

## 🚀 Deployment

### **JAR-Datei erstellen**
```bash
mvn clean package
```

### **Docker (optional)**
```dockerfile
FROM openjdk:21-jdk-slim
COPY target/jobportal-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 🔧 Entwicklung

### **Code-Struktur**
- **MVC-Pattern**: Klare Trennung von Controller, Service, Repository
- **Dependency Injection**: Spring IoC Container
- **Configuration Classes**: Zentrale Konfiguration
- **Utility Classes**: Wiederverwendbare Hilfsfunktionen

### **Best Practices**
- **RESTful URLs**: Semantische URL-Struktur
- **Exception Handling**: Zentrale Fehlerbehandlung
- **Validation**: Eingabevalidierung mit Bean Validation
- **Logging**: Strukturiertes Logging

## 📝 API-Dokumentation

### **Hauptendpunkte**
- `GET /` - Startseite
- `GET /login` - Anmeldung
- `POST /login` - Anmeldung verarbeiten
- `GET /register` - Registrierung
- `POST /register` - Registrierung verarbeiten
- `GET /dashboard/` - Dashboard
- `GET /global-search/` - Jobsuche
- `POST /dashboard/addNew` - Job hinzufügen
- `GET /dashboard/edit/{id}` - Job bearbeiten

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

---


