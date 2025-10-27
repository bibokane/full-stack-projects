# 🎯 Job Portal - HotDevJobs.com

Eine Jobbörse-Webanwendung, entwickelt mit Spring Boot und Thymeleaf. Die Anwendung bietet eine Plattform für Jobsuchende und Recruiter, um Stellenausschreibungen zu durchsuchen und zu verwalten.

## 📋 Projektübersicht

**HotDevJobs.com** ist eine Jobbörse-Webanwendung, die moderne Java-Technologien nutzt. Die Anwendung implementiert serverseitiges Rendering mit Thymeleaf und bietet Such- und Filterfunktionen für Stellenausschreibungen.

## ✨ Hauptfunktionen

### 🔍 **Jobsuche & -verwaltung**
- **Globale Jobsuche** mit Filterung nach Position und Standort
- **Erweiterte Suchfilter**: Vollzeit, Teilzeit
- **Remote-Optionen**: Remote-Only, Office-Only, Partial-Remote
- **Zeitbasierte Filter**: Heute, 7 Tage, 30 Tage
- **Jobdetails** mit Beschreibungen und Anforderungen

### 👥 **Benutzerverwaltung**
- **Zwei Benutzertypen**: Jobsuchende und Recruiter
- **Sichere Authentifizierung** mit Spring Security
- **Benutzerregistrierung** mit Rollenauswahl
- **Rollenbasierte Navigation** und Zugriffskontrolle

### 📝 **Stellenausschreibungen**
- **Job-Posting** für Recruiter
- **Job-Bearbeitung** und -Verwaltung
- **Jobsuche** für Jobsuchende
- **Bewerbungsfunktionen** (Grundfunktionalität)

### 🎨 **Benutzeroberfläche**
- **Responsive Design** mit Bootstrap 5
- **Thymeleaf-Templates** für serverseitiges Rendering
- **Rollenbasierte Navigation** (unterschiedliche Menüs für Jobsuchende/Recruiter)
- **Moderne UI-Komponenten** mit Font Awesome Icons

## 🛠️ Technologie-Stack

### **Backend**
- **Spring Boot 3.5.3** - Hauptframework
- **Java 21** - Programmiersprache
- **Spring MVC** - Web-Framework
- **Spring Security 6** - Authentifizierung & Autorisierung
- **Spring Data JPA** - Datenzugriff
- **Hibernate 6** - ORM-Framework
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
│   ├── controller/              # Web-Controller
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
│   │   ├── JobSeekerProfile.java
│   │   ├── JobSeekerSave.java
│   │   ├── JobCompany.java
│   │   ├── JobLocation.java
│   │   ├── RecruiterProfile.java
│   │   ├── Skills.java
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
- **Users**: Benutzerverwaltung (Email, Passwort, Benutzertyp)
- **UsersType**: Benutzertypen (Job Seeker, Recruiter)
- **JobPostActivity**: Stellenausschreibungen (Titel, Beschreibung, Typ, Remote, Gehalt)
- **JobLocation**: Jobstandorte (Stadt, Bundesland, Land)
- **JobCompany**: Unternehmen
- **JobSeekerProfile**: Profil von Jobsuchenden (Name, Standort, Skills, Lebenslauf)
- **RecruiterProfile**: Profil von Recruitern
- **JobSeekerApply**: Bewerbungen
- **JobSeekerSave**: Gespeicherte Jobs
- **Skills**: Fähigkeiten von Jobsuchenden

### **Beziehungen**
- Ein User hat einen UsersType (Job Seeker oder Recruiter)
- Ein User kann ein JobSeekerProfile oder RecruiterProfile haben
- Ein JobPostActivity gehört zu einem User (Recruiter) und hat eine JobLocation und JobCompany
- Ein JobSeeker kann sich auf mehrere Jobs bewerben (JobSeekerApply)
- Ein JobSeeker kann mehrere Jobs speichern (JobSeekerSave)

## 🔐 Sicherheit

### **Spring Security Konfiguration**
- **Authentifizierung**: Formular-basierte Anmeldung
- **Autorisierung**: Rollenbasierte Zugriffskontrolle
- **Sessions**: HTTP-Session-Management
- **CSRF-Schutz**: Deaktiviert (für Entwicklung)
- **Password-Encoding**: BCrypt

### **Benutzerrollen**
- **"Job Seeker"**: Jobsuchende
- **"Recruiter"**: Recruiter

## 🎨 Benutzeroberfläche

### **Seiten-Übersicht**
- **Startseite** (`/`): Jobsuche und Navigation
- **Anmeldung** (`/login`): Benutzeranmeldung
- **Registrierung** (`/register`): Benutzerregistrierung mit Rollenauswahl
- **Dashboard** (`/dashboard/`): Hauptarbeitsbereich mit rollenbasierter Navigation
- **Jobsuche** (`/global-search/`): Erweiterte Jobsuche mit Filtern
- **Job hinzufügen** (`/dashboard/add`): Stellenausschreibung erstellen (nur Recruiter)
- **Job bearbeiten** (`/dashboard/edit/{id}`): Job bearbeiten (nur Recruiter)
- **Profil** (`/job-seeker-profile/`, `/recruiter-profile/`): Profil verwalten

### **Rollenbasierte Navigation**
- **Job Seeker**: Jobsuche, Gespeicherte Jobs, Profil bearbeiten
- **Recruiter**: Job posten, Eigene Jobs anzeigen, Profil bearbeiten

### **Responsive Design**
- **Mobile-first** Ansatz
- **Bootstrap 5** Grid-System
- **Flexible Layouts** für alle Bildschirmgrößen
- **Touch-optimierte** Bedienelemente

## 📈 Performance & Monitoring

### **Entwicklungstools**
- **Spring Boot DevTools**: Hot-Reload
- **H2 Console**: Datenbank-Debugging (optional)

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
- `POST /register/new` - Registrierung verarbeiten
- `GET /dashboard/` - Dashboard mit Jobsuche
- `GET /global-search/` - Erweiterte Jobsuche
- `GET /dashboard/add` - Job hinzufügen (nur Recruiter)
- `POST /dashboard/addNew` - Job speichern
- `GET /dashboard/edit/{id}` - Job bearbeiten
- `GET /logout` - Abmeldung

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


