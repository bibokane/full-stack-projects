package com.turnier.service;

import com.turnier.entity.Gruppe;
import com.turnier.entity.Mannschaft;
import com.turnier.entity.Spiel;
import com.turnier.exception.TurnierException;
import com.turnier.repository.GruppeRepository;
import com.turnier.repository.MannschaftRepository;
import com.turnier.repository.SpielRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GruppeService {
    
    private static final int ANZAHL_GRUPPEN = 4;
    private static final int MANNSCHAFTEN_PRO_GRUPPE = 4;
    
    @Autowired
    private GruppeRepository gruppeRepository;
    
    @Autowired
    private MannschaftRepository mannschaftRepository;
    
    @Autowired
    private SpielRepository spielRepository;
    
    public List<Gruppe> getAllGruppen() {
        return gruppeRepository.findAll();
    }
    
    public List<Mannschaft> getGruppentabelle(Long gruppenId) {
        return mannschaftRepository.findGruppentabelle(gruppenId);
    }
    
    public List<Spiel> getGruppenspiele(Long gruppenId) {
        List<Mannschaft> mannschaftenInGruppe = mannschaftRepository.findByGruppenIdOrderByPunkteDescToreGeschossenDesc(gruppenId);
        List<Spiel> gruppenspiele = new ArrayList<>();
        
        for (Spiel spiel : spielRepository.findAll()) {
            if (spiel.getSpielTyp() == Spiel.SpielTyp.GRUPPENSPIEL &&
                mannschaftenInGruppe.contains(spiel.getMannschaft1()) &&
                mannschaftenInGruppe.contains(spiel.getMannschaft2())) {
                gruppenspiele.add(spiel);
            }
        }
        
        return gruppenspiele;
    }
    
    public void erstelleGruppen(long anzahlMannschaften) {
        if (anzahlMannschaften != ANZAHL_GRUPPEN * MANNSCHAFTEN_PRO_GRUPPE) {
            throw new TurnierException("Es müssen genau " + (ANZAHL_GRUPPEN * MANNSCHAFTEN_PRO_GRUPPE) + " Mannschaften vorhanden sein!");
        }
        
        // Lösche bestehende Gruppen und Spiele
        gruppeRepository.deleteAll();
        spielRepository.deleteAll();
        
        // Erstelle 4 Gruppen (A-D)
        List<Gruppe> gruppen = new ArrayList<>();
        for (int i = 0; i < ANZAHL_GRUPPEN; i++) {
            Gruppe gruppe = new Gruppe(String.valueOf((char) ('A' + i)));
            gruppen.add(gruppeRepository.save(gruppe));
        }
        
        // Verteile Mannschaften auf Gruppen
        List<Mannschaft> mannschaften = mannschaftRepository.findAll();
        for (int i = 0; i < mannschaften.size(); i++) {
            Mannschaft mannschaft = mannschaften.get(i);
            int gruppenIndex = i / MANNSCHAFTEN_PRO_GRUPPE;
            mannschaft.setGruppenId(gruppen.get(gruppenIndex).getId());
            mannschaftRepository.save(mannschaft);
        }
        
        // Erstelle Gruppenspiele
        erstelleGruppenspiele(gruppen);
    }
    
    public void erstelleGruppenspiele() {
        // Lösche bestehende Gruppenspiele
        List<Spiel> alleSpiele = spielRepository.findAll();
        for (Spiel spiel : alleSpiele) {
            if (spiel.getSpielTyp() == Spiel.SpielTyp.GRUPPENSPIEL) {
                spielRepository.delete(spiel);
            }
        }
        
        List<Gruppe> gruppen = gruppeRepository.findAll();
        erstelleGruppenspiele(gruppen);
    }
    
    private void erstelleGruppenspiele(List<Gruppe> gruppen) {
        for (Gruppe gruppe : gruppen) {
            List<Mannschaft> mannschaftenInGruppe = mannschaftRepository.findByGruppenIdOrderByPunkteDescToreGeschossenDesc(gruppe.getId());
            
            // Erstelle alle Gruppenspiele (Jeder gegen Jeden)
            for (int i = 0; i < mannschaftenInGruppe.size(); i++) {
                for (int j = i + 1; j < mannschaftenInGruppe.size(); j++) {
                    Spiel spiel = new Spiel(
                        mannschaftenInGruppe.get(i),
                        mannschaftenInGruppe.get(j),
                        Spiel.SpielTyp.GRUPPENSPIEL,
                        1
                    );
                    spielRepository.save(spiel);
                }
            }
        }
    }
    
    public void markiereGruppeAlsAbgeschlossen(Long gruppenId) {
        Gruppe gruppe = gruppeRepository.findById(gruppenId)
            .orElseThrow(() -> new TurnierException("Gruppe nicht gefunden! ID: " + gruppenId));
        gruppe.setIstAbgeschlossen(true);
        gruppeRepository.save(gruppe);
    }
    
    public boolean sindAlleGruppenAbgeschlossen() {
        List<Gruppe> gruppen = gruppeRepository.findAll();
        if (gruppen.isEmpty()) {
            return false;
        }
        return gruppen.stream().allMatch(Gruppe::getIstAbgeschlossen);
    }
    
    public void prüfeGruppenphaseAbgeschlossen() {
        List<Gruppe> gruppen = gruppeRepository.findAll();
        
        // Prüfe für jede Gruppe, ob alle Spiele beendet sind
        for (Gruppe gruppe : gruppen) {
            List<Spiel> gruppenspiele = getGruppenspiele(gruppe.getId());
            boolean alleSpieleBeendet = gruppenspiele.stream().allMatch(Spiel::isIstBeendet);
            
            if (alleSpieleBeendet && !gruppe.getIstAbgeschlossen()) {
                markiereGruppeAlsAbgeschlossen(gruppe.getId());
            }
        }
    }
}
