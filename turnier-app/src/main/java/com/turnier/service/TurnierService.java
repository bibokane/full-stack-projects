package com.turnier.service;

import com.turnier.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TurnierService {
    
    @Autowired
    private MannschaftService mannschaftService;
    
    @Autowired
    private GruppeService gruppeService;
    
    @Autowired
    private SpielService spielService;
    
    @Autowired
    private StatistikenService statistikenService;
    
    @Autowired
    private TurnierPhaseService turnierPhaseService;
    
    // Delegierungsmethoden für MannschaftService
    public Mannschaft createMannschaft(String name) {
        return mannschaftService.createMannschaft(name);
    }
    
    public List<Mannschaft> getAllMannschaften() {
        return mannschaftService.getAllMannschaften();
    }
    
    public void deleteMannschaft(Long id) {
        mannschaftService.deleteMannschaft(id);
    }
    
    // Delegierungsmethoden für GruppeService
    public void erstelleGruppen() {
        long anzahlMannschaften = mannschaftService.getAnzahlMannschaften();
        gruppeService.erstelleGruppen(anzahlMannschaften);
    }
    
    public List<Gruppe> getAllGruppen() {
        return gruppeService.getAllGruppen();
    }
    
    public List<Mannschaft> getGruppentabelle(Long gruppenId) {
        return gruppeService.getGruppentabelle(gruppenId);
    }
    
    public List<Spiel> getGruppenspiele(Long gruppenId) {
        return gruppeService.getGruppenspiele(gruppenId);
    }
    
    // Delegierungsmethoden für SpielService
    public List<Spiel> getSpieleByTyp(Spiel.SpielTyp spielTyp) {
        return spielService.getSpieleByTyp(spielTyp);
    }
    
    public List<Spiel> getAktuelleRunde() {
        return spielService.getAktuelleRunde();
    }
    
    public Spiel updateSpielErgebnis(Long spielId, Integer tore1, Integer tore2) {
        Spiel spiel = spielService.updateSpielErgebnis(spielId, tore1, tore2);
        
        // Aktualisiere Mannschaftsstatistiken
        statistikenService.aktualisiereMannschaftsstatistiken(spiel);
        
        // Für Gruppenspiele: Prüfe ob Gruppe abgeschlossen ist
        if (spiel.getSpielTyp() == Spiel.SpielTyp.GRUPPENSPIEL) {
            gruppeService.prüfeGruppenphaseAbgeschlossen();
        }
        
        return spiel;
    }
    
    public Mannschaft getGewinner(Spiel spiel) {
        return spielService.getGewinner(spiel);
    }
    
    // Delegierungsmethoden für TurnierPhaseService
    public void erstelleGruppenspiele() {
        turnierPhaseService.erstelleGruppenspiele();
    }
    
    public void erstelleNächsteRunde() {
        turnierPhaseService.erstelleNächsteRunde();
    }
    
    public void starteKOPHase() {
        turnierPhaseService.starteKOPHase();
    }
    
    public Map<String, Object> getTurnierStatus() {
        Map<String, Object> status = new HashMap<>();
        
        // Hole Finale
        List<Spiel> finale = spielService.getSpieleByTyp(Spiel.SpielTyp.FINALE);
        if (!finale.isEmpty() && finale.get(0).isIstBeendet()) {
            Mannschaft sieger = spielService.getGewinner(finale.get(0));
            status.put("sieger", sieger.getName());
            status.put("istBeendet", true);
        } else {
            status.put("sieger", null);
            status.put("istBeendet", false);
        }
        
        return status;
    }
}
