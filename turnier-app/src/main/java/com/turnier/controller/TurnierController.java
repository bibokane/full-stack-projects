package com.turnier.controller;

import com.turnier.entity.Gruppe;
import com.turnier.entity.Mannschaft;
import com.turnier.entity.Spiel;
import com.turnier.exception.TurnierException;
import com.turnier.service.TurnierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/turnier")
@CrossOrigin(origins = "http://localhost:4200")
public class TurnierController {
    
    @Autowired
    private TurnierService turnierService;
    
    // Mannschaften
    @PostMapping("/mannschaften")
    public ResponseEntity<?> createMannschaft(@RequestBody Map<String, String> request) {
        try {
            String name = request.get("name");
            if (name == null || name.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Mannschaftsname darf nicht leer sein!");
            }
            
            Mannschaft mannschaft = turnierService.createMannschaft(name);
            return ResponseEntity.ok(mannschaft);
        } catch (TurnierException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fehler beim Erstellen der Mannschaft: " + e.getMessage());
        }
    }
    
    @GetMapping("/mannschaften")
    public ResponseEntity<List<Mannschaft>> getAllMannschaften() {
        return ResponseEntity.ok(turnierService.getAllMannschaften());
    }
    
    @DeleteMapping("/mannschaften/{id}")
    public ResponseEntity<?> deleteMannschaft(@PathVariable Long id) {
        try {
            turnierService.deleteMannschaft(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Fehler beim Löschen der Mannschaft: " + e.getMessage());
        }
    }
    
    // Gruppen
    @PostMapping("/gruppen/erstellen")
    public ResponseEntity<?> erstelleGruppen() {
        try {
            turnierService.erstelleGruppen();
            return ResponseEntity.ok().build();
        } catch (TurnierException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fehler beim Erstellen der Gruppen: " + e.getMessage());
        }
    }
    
    @GetMapping("/gruppen")
    public ResponseEntity<List<Gruppe>> getAllGruppen() {
        return ResponseEntity.ok(turnierService.getAllGruppen());
    }
    
    @GetMapping("/gruppen/{id}/tabelle")
    public ResponseEntity<List<Mannschaft>> getGruppentabelle(@PathVariable Long id) {
        return ResponseEntity.ok(turnierService.getGruppentabelle(id));
    }
    
    @GetMapping("/gruppen/{id}/spiele")
    public ResponseEntity<List<Spiel>> getGruppenspieleById(@PathVariable Long id) {
        return ResponseEntity.ok(turnierService.getGruppenspiele(id));
    }
    
    // Spiele
    @PostMapping("/gruppenspiele/erstellen")
    public ResponseEntity<?> erstelleGruppenspiele() {
        try {
            turnierService.erstelleGruppenspiele();
            return ResponseEntity.ok().build();
        } catch (TurnierException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fehler beim Erstellen der Gruppenspiele: " + e.getMessage());
        }
    }
    
    @GetMapping("/spiele/gruppenspiele")
    public ResponseEntity<List<Spiel>> getGruppenspiele() {
        return ResponseEntity.ok(turnierService.getSpieleByTyp(Spiel.SpielTyp.GRUPPENSPIEL));
    }
    
    @GetMapping("/spiele/viertelfinale")
    public ResponseEntity<List<Spiel>> getViertelfinale() {
        return ResponseEntity.ok(turnierService.getSpieleByTyp(Spiel.SpielTyp.VIERTELFINALE));
    }
    
    @GetMapping("/spiele/halbfinale")
    public ResponseEntity<List<Spiel>> getHalbfinale() {
        return ResponseEntity.ok(turnierService.getSpieleByTyp(Spiel.SpielTyp.HALBFINALE));
    }
    
    @GetMapping("/spiele/finale")
    public ResponseEntity<List<Spiel>> getFinale() {
        return ResponseEntity.ok(turnierService.getSpieleByTyp(Spiel.SpielTyp.FINALE));
    }
    
    @GetMapping("/spiele/aktuelle-runde")
    public ResponseEntity<List<Spiel>> getAktuelleRunde() {
        return ResponseEntity.ok(turnierService.getAktuelleRunde());
    }
    
    @PutMapping("/spiele/{id}/ergebnis")
    public ResponseEntity<?> updateSpielErgebnis(
            @PathVariable Long id,
            @RequestBody Map<String, Object> request) {
        try {
            Object tore1Obj = request.get("tore1");
            Object tore2Obj = request.get("tore2");
            
            if (tore1Obj == null || tore2Obj == null) {
                return ResponseEntity.badRequest().body("Tore dürfen nicht null sein!");
            }
            
            Integer tore1 = null;
            Integer tore2 = null;
            
            try {
                if (tore1Obj instanceof Integer) {
                    tore1 = (Integer) tore1Obj;
                } else if (tore1Obj instanceof Number) {
                    tore1 = ((Number) tore1Obj).intValue();
                } else if (tore1Obj instanceof String) {
                    tore1 = Integer.parseInt((String) tore1Obj);
                } else {
                    return ResponseEntity.badRequest().body("Ungültiger Wert für tore1: " + tore1Obj);
                }
                
                if (tore2Obj instanceof Integer) {
                    tore2 = (Integer) tore2Obj;
                } else if (tore2Obj instanceof Number) {
                    tore2 = ((Number) tore2Obj).intValue();
                } else if (tore2Obj instanceof String) {
                    tore2 = Integer.parseInt((String) tore2Obj);
                } else {
                    return ResponseEntity.badRequest().body("Ungültiger Wert für tore2: " + tore2Obj);
                }
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body("Tore müssen gültige Zahlen sein!");
            }
            
            if (tore1 < 0 || tore2 < 0) {
                return ResponseEntity.badRequest().body("Tore dürfen nicht negativ sein!");
            }
            
            Spiel spiel = turnierService.updateSpielErgebnis(id, tore1, tore2);
            return ResponseEntity.ok(spiel);
        } catch (TurnierException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fehler beim Aktualisieren des Spielergebnisses: " + e.getMessage());
        }
    }
    
    // Turnier
    @PostMapping("/naechste-runde")
    public ResponseEntity<?> erstelleNaechsteRunde() {
        try {
            turnierService.erstelleNächsteRunde();
            return ResponseEntity.ok().build();
        } catch (TurnierException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fehler beim Erstellen der nächsten Runde: " + e.getMessage());
        }
    }
    
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getTurnierStatus() {
        Map<String, Object> status = turnierService.getTurnierStatus();
        return ResponseEntity.ok(status);
    }
    
    @PostMapping("/reset")
    public ResponseEntity<?> resetTurnier() {
        // Reset-Logik würde hier implementiert werden
        return ResponseEntity.ok().build();
    }
}
