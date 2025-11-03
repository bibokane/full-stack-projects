# 🏆 Turnier App - 16 Mannschaften

Eine vollständige Full-Stack Turnier-App mit Spring Boot und Angular für 16 Mannschaften.

## ✨ Funktionen

- **16 Mannschaften registrieren** - Einfache Eingabe und Verwaltung
- **4 Gruppen à 4 Mannschaften** - Automatische Verteilung
- **Gruppenphase** - Jeder gegen jeden in jeder Gruppe
- **K.O.-Phase** - Viertelfinale, Halbfinale, Finale
- **Ergebnisse eingeben** - Tore für jedes Spiel eingeben
- **Automatische Weiterleitung** - Sieger kommen automatisch weiter
- **Schönes Frontend** - Moderne, responsive UI

## 🚀 Installation & Start

### Backend (Spring Boot)

```bash
cd turnier-app
mvn clean install
mvn spring-boot:run
```

Backend läuft auf: **http://localhost:8081**

### Frontend (Angular)

```bash
cd turnier-app/frontend
npm install
npm start
```

Frontend läuft auf: **http://localhost:4200**

## 🎮 Verwendung

### 1. Mannschaften registrieren
- Öffnen Sie http://localhost:4200
- Geben Sie 16 Mannschaftsnamen ein
- Klicken Sie "Hinzufügen" für jede Mannschaft

### 2. Gruppen erstellen
- Wenn alle 16 Mannschaften registriert sind
- Klicken Sie "Gruppen erstellen"
- 4 Gruppen (A, B, C, D) werden automatisch erstellt

### 3. Gruppenphase starten
- Klicken Sie "Gruppenphase starten"
- Alle Gruppenspiele werden erstellt (24 Spiele insgesamt)

### 4. Ergebnisse eingeben
- Geben Sie für jedes Spiel die Tore ein
- Klicken Sie "Ergebnis speichern"
- Die Gruppentabelle wird automatisch aktualisiert

### 5. K.O.-Phase
- Nach allen Gruppenspielen wird automatisch die K.O.-Phase gestartet
- Viertelfinale: A1 vs B2, B1 vs A2, C1 vs D2, D1 vs C2
- Halbfinale: Sieger der Viertelfinalspiele
- Finale: Die beiden Halbfinal-Sieger

### 6. Finale
- Nach dem Finale wird der Sieger automatisch angezeigt
- Herzlichen Glückwunsch zum Sieg! 🏆

## 📋 API Endpoints

### Mannschaften
- `POST /api/turnier/mannschaften` - Mannschaft erstellen
- `GET /api/turnier/mannschaften` - Alle Mannschaften abrufen
- `DELETE /api/turnier/mannschaften/{id}` - Mannschaft löschen

### Gruppen
- `POST /api/turnier/gruppen/erstellen` - Gruppen erstellen
- `GET /api/turnier/gruppen` - Alle Gruppen abrufen
- `GET /api/turnier/gruppen/{id}/tabelle` - Gruppentabelle abrufen
- `GET /api/turnier/gruppen/{id}/spiele` - Gruppenspiele abrufen

### Spiele
- `POST /api/turnier/gruppenspiele/erstellen` - Gruppenspiele erstellen
- `GET /api/turnier/spiele/gruppenspiele` - Alle Gruppenspiele abrufen
- `GET /api/turnier/spiele/viertelfinale` - Viertelfinalspiele abrufen
- `GET /api/turnier/spiele/halbfinale` - Halbfinalspiele abrufen
- `GET /api/turnier/spiele/finale` - Finalspiele abrufen
- `GET /api/turnier/spiele/aktuelle-runde` - Aktuelle Runde abrufen
- `PUT /api/turnier/spiele/{id}/ergebnis` - Spielergebnis aktualisieren

### Turnier
- `POST /api/turnier/naechste-runde` - Nächste Runde erstellen
- `GET /api/turnier/status` - Turnierstatus abrufen
- `POST /api/turnier/reset` - Turnier zurücksetzen

## 🔧 Technologien

### Backend
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database (In-Memory)
- Maven

### Frontend
- Angular 17
- TypeScript
- SCSS
- Standalone Components
- RxJS

## 📝 Problembehandlung

### Backend startet nicht
```bash
# Stoppen Sie alle Java-Prozesse
taskkill /F /IM java.exe

# Starten Sie das Backend neu
mvn spring-boot:run
```

### Frontend zeigt Fehler
```bash
# Öffnen Sie ein neues Terminal im Frontend-Ordner
cd frontend
npm install
npm start
```

### Port bereits belegt
Das Backend verwendet Port 8081, das Frontend Port 4200.
Falls einer belegt ist, stoppen Sie den Prozess oder ändern Sie den Port in `application.properties` bzw. Angular-Konfiguration.

## 🎯 Turnier-Ablauf

1. **Gruppenphase** (4 Gruppen à 4 Mannschaften)
   - Jeder gegen jeden (6 Spiele pro Gruppe)
   - 3 Punkte für Sieg, 1 Punkt für Unentschieden

2. **Viertelfinale** (8 Mannschaften)
   - A1 vs B2
   - B1 vs A2
   - C1 vs D2
   - D1 vs C2

3. **Halbfinale** (4 Mannschaften)
   - Sieger VF1 vs Sieger VF2
   - Sieger VF3 vs Sieger VF4

4. **Finale** (2 Mannschaften)
   - Sieger HF1 vs Sieger HF2

5. **Sieger** 🏆
   - Der Gewinner wird angezeigt und gefeiert!

## 📄 Lizenz

Dieses Projekt wurde für Bildungszwecke erstellt.

## 🎉 Viel Spaß mit Ihrer Turnier App!

