package com.turnier.service;

import com.turnier.entity.Gruppe;
import com.turnier.entity.Mannschaft;
import com.turnier.entity.Spiel;
import com.turnier.exception.TurnierException;
import com.turnier.repository.MannschaftRepository;
import com.turnier.repository.SpielRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TurnierPhaseService {
    
    @Autowired
    private SpielRepository spielRepository;
    
    @Autowired
    private MannschaftRepository mannschaftRepository;
    
    @Autowired
    private GruppeService gruppeService;
    
    @Autowired
    private SpielService spielService;
    
    @Autowired
    private StatistikenService statistikenService;
    
    public void erstelleGruppenspiele() {
        gruppeService.erstelleGruppenspiele();
    }
    
    public void starteKOPHase() {
        if (spielRepository.findBySpielTypOrderByRundeAsc(Spiel.SpielTyp.VIERTELFINALE).size() > 0) {
            throw new TurnierException("K.O.-Phase wurde bereits gestartet!");
        }
        
        if (!gruppeService.sindAlleGruppenAbgeschlossen()) {
            throw new TurnierException("Nicht alle Gruppen sind abgeschlossen!");
        }
        
        erstelleViertelfinalspiele();
    }
    
    public void erstelleViertelfinalspiele() {
        // Prüfe, ob Viertelfinale bereits existieren
        if (spielRepository.findBySpielTypOrderByRundeAsc(Spiel.SpielTyp.VIERTELFINALE).size() > 0) {
            return; // Bereits erstellt
        }
        
        List<Gruppe> gruppen = gruppeService.getAllGruppen();
        if (gruppen.size() != 4) {
            throw new TurnierException("Es müssen genau 4 Gruppen vorhanden sein!");
        }
        
        // Hole Gruppenerste und -zweite
        Mannschaft a1 = getGruppenerste(gruppen.get(0).getId());
        Mannschaft a2 = getGruppenzweite(gruppen.get(0).getId());
        Mannschaft b1 = getGruppenerste(gruppen.get(1).getId());
        Mannschaft b2 = getGruppenzweite(gruppen.get(1).getId());
        Mannschaft c1 = getGruppenerste(gruppen.get(2).getId());
        Mannschaft c2 = getGruppenzweite(gruppen.get(2).getId());
        Mannschaft d1 = getGruppenerste(gruppen.get(3).getId());
        Mannschaft d2 = getGruppenzweite(gruppen.get(3).getId());
        
        // Erstelle Viertelfinalspiele: A1 vs B2, B1 vs A2, C1 vs D2, D1 vs C2
        List<Spiel> viertelfinalspiele = new ArrayList<>();
        viertelfinalspiele.add(new Spiel(a1, b2, Spiel.SpielTyp.VIERTELFINALE, 2));
        viertelfinalspiele.add(new Spiel(b1, a2, Spiel.SpielTyp.VIERTELFINALE, 2));
        viertelfinalspiele.add(new Spiel(c1, d2, Spiel.SpielTyp.VIERTELFINALE, 2));
        viertelfinalspiele.add(new Spiel(d1, c2, Spiel.SpielTyp.VIERTELFINALE, 2));
        
        spielRepository.saveAll(viertelfinalspiele);
    }
    
    public void erstelleHalbfinalspiele() {
        // Prüfe, ob Halbfinale bereits existieren
        if (spielRepository.findBySpielTypOrderByRundeAsc(Spiel.SpielTyp.HALBFINALE).size() > 0) {
            return; // Bereits erstellt
        }
        
        List<Spiel> viertelfinalspiele = spielRepository.findBySpielTypOrderByRundeAsc(Spiel.SpielTyp.VIERTELFINALE);
        if (viertelfinalspiele.size() != 4) {
            throw new TurnierException("Es müssen genau 4 Viertelfinalspiele vorhanden sein!");
        }
        
        // Prüfe, ob alle Viertelfinalspiele beendet sind
        for (Spiel spiel : viertelfinalspiele) {
            if (!spiel.isIstBeendet()) {
                throw new TurnierException("Nicht alle Viertelfinalspiele sind beendet!");
            }
        }
        
        // Hole Gewinner der Viertelfinalspiele
        Mannschaft sieger1 = spielService.getGewinner(viertelfinalspiele.get(0));
        Mannschaft sieger2 = spielService.getGewinner(viertelfinalspiele.get(1));
        Mannschaft sieger3 = spielService.getGewinner(viertelfinalspiele.get(2));
        Mannschaft sieger4 = spielService.getGewinner(viertelfinalspiele.get(3));
        
        // Erstelle Halbfinalspiele: Sieger VF1 vs Sieger VF2, Sieger VF3 vs Sieger VF4
        List<Spiel> halbfinalspiele = new ArrayList<>();
        halbfinalspiele.add(new Spiel(sieger1, sieger2, Spiel.SpielTyp.HALBFINALE, 3));
        halbfinalspiele.add(new Spiel(sieger3, sieger4, Spiel.SpielTyp.HALBFINALE, 3));
        
        spielRepository.saveAll(halbfinalspiele);
    }
    
    public void erstelleFinale() {
        // Prüfe, ob Finale bereits existiert
        if (spielRepository.findBySpielTypOrderByRundeAsc(Spiel.SpielTyp.FINALE).size() > 0) {
            return; // Bereits erstellt
        }
        
        List<Spiel> halbfinalspiele = spielRepository.findBySpielTypOrderByRundeAsc(Spiel.SpielTyp.HALBFINALE);
        if (halbfinalspiele.size() != 2) {
            throw new TurnierException("Es müssen genau 2 Halbfinalspiele vorhanden sein!");
        }
        
        // Prüfe, ob alle Halbfinalspiele beendet sind
        for (Spiel spiel : halbfinalspiele) {
            if (!spiel.isIstBeendet()) {
                throw new TurnierException("Nicht alle Halbfinalspiele sind beendet!");
            }
        }
        
        // Hole Gewinner der Halbfinalspiele
        Mannschaft sieger1 = spielService.getGewinner(halbfinalspiele.get(0));
        Mannschaft sieger2 = spielService.getGewinner(halbfinalspiele.get(1));
        
        // Erstelle Finale
        Spiel finale = new Spiel(sieger1, sieger2, Spiel.SpielTyp.FINALE, 4);
        spielRepository.save(finale);
    }
    
    public void erstelleNächsteRunde() {
        // Prüfe, welche Phase als nächstes kommt
        List<Spiel> gruppenspiele = spielRepository.findBySpielTypOrderByRundeAsc(Spiel.SpielTyp.GRUPPENSPIEL);
        List<Spiel> viertelfinalspiele = spielRepository.findBySpielTypOrderByRundeAsc(Spiel.SpielTyp.VIERTELFINALE);
        List<Spiel> halbfinalspiele = spielRepository.findBySpielTypOrderByRundeAsc(Spiel.SpielTyp.HALBFINALE);
        List<Spiel> finale = spielRepository.findBySpielTypOrderByRundeAsc(Spiel.SpielTyp.FINALE);
        
        // Wenn keine Gruppenspiele existieren, erstelle sie
        if (gruppenspiele.isEmpty()) {
            gruppeService.erstelleGruppenspiele();
            return;
        }
        
        // Wenn alle Gruppenspiele beendet sind, aber keine K.O.-Phase existiert
        boolean alleGruppenspieleBeendet = gruppenspiele.stream().allMatch(Spiel::isIstBeendet);
        if (alleGruppenspieleBeendet && viertelfinalspiele.isEmpty()) {
            gruppeService.prüfeGruppenphaseAbgeschlossen();
            if (gruppeService.sindAlleGruppenAbgeschlossen()) {
                starteKOPHase();
            }
            return;
        }
        
        // Wenn alle Viertelfinalspiele beendet sind, aber keine Halbfinalspiele existieren
        if (!viertelfinalspiele.isEmpty()) {
            boolean alleViertelfinalspieleBeendet = viertelfinalspiele.stream().allMatch(Spiel::isIstBeendet);
            if (alleViertelfinalspieleBeendet && halbfinalspiele.isEmpty()) {
                erstelleHalbfinalspiele();
                return;
            }
        }
        
        // Wenn alle Halbfinalspiele beendet sind, aber kein Finale existiert
        if (!halbfinalspiele.isEmpty()) {
            boolean alleHalbfinalspieleBeendet = halbfinalspiele.stream().allMatch(Spiel::isIstBeendet);
            if (alleHalbfinalspieleBeendet && finale.isEmpty()) {
                erstelleFinale();
                return;
            }
        }
        
        throw new TurnierException("Nächste Runde kann nicht erstellt werden. Bitte prüfen Sie den Turnierstatus!");
    }
    
    private Mannschaft getGruppenerste(Long gruppenId) {
        List<Mannschaft> mannschaften = gruppeService.getGruppentabelle(gruppenId);
        if (mannschaften.isEmpty()) {
            throw new TurnierException("Keine Mannschaften in Gruppe " + gruppenId + " gefunden!");
        }
        return mannschaften.get(0);
    }
    
    private Mannschaft getGruppenzweite(Long gruppenId) {
        List<Mannschaft> mannschaften = gruppeService.getGruppentabelle(gruppenId);
        if (mannschaften.size() < 2) {
            throw new TurnierException("Nicht genug Mannschaften in Gruppe " + gruppenId + " gefunden!");
        }
        return mannschaften.get(1);
    }
}
