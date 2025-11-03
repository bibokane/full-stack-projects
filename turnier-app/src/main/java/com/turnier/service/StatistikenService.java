package com.turnier.service;

import com.turnier.entity.Mannschaft;
import com.turnier.entity.Spiel;
import com.turnier.repository.MannschaftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StatistikenService {
    
    @Autowired
    private MannschaftRepository mannschaftRepository;
    
    public void aktualisiereMannschaftsstatistiken(Spiel spiel) {
        if (spiel.getMannschaft1() == null || spiel.getMannschaft2() == null) {
            throw new RuntimeException("Mannschaften im Spiel nicht gefunden!");
        }
        
        if (spiel.getToreMannschaft1() == null || spiel.getToreMannschaft2() == null) {
            return; // Spiel ist noch nicht beendet
        }
        
        Mannschaft mannschaft1 = mannschaftRepository.findById(spiel.getMannschaft1().getId())
            .orElseThrow(() -> new RuntimeException("Mannschaft 1 nicht gefunden!"));
        Mannschaft mannschaft2 = mannschaftRepository.findById(spiel.getMannschaft2().getId())
            .orElseThrow(() -> new RuntimeException("Mannschaft 2 nicht gefunden!"));
        
        int tore1 = spiel.getToreMannschaft1();
        int tore2 = spiel.getToreMannschaft2();
        
        // Aktualisiere Tore
        mannschaft1.setToreGeschossen(mannschaft1.getToreGeschossen() + tore1);
        mannschaft1.setToreErhalten(mannschaft1.getToreErhalten() + tore2);
        mannschaft2.setToreGeschossen(mannschaft2.getToreGeschossen() + tore2);
        mannschaft2.setToreErhalten(mannschaft2.getToreErhalten() + tore1);
        
        // Aktualisiere Tordifferenz
        mannschaft1.setTordifferenz(mannschaft1.getToreGeschossen() - mannschaft1.getToreErhalten());
        mannschaft2.setTordifferenz(mannschaft2.getToreGeschossen() - mannschaft2.getToreErhalten());
        
        // Aktualisiere Spiele
        mannschaft1.setSpieleGespielt(mannschaft1.getSpieleGespielt() + 1);
        mannschaft2.setSpieleGespielt(mannschaft2.getSpieleGespielt() + 1);
        
        // Aktualisiere Punkte (nur für Gruppenspiele)
        if (spiel.getSpielTyp() == Spiel.SpielTyp.GRUPPENSPIEL) {
            updatePunkte(mannschaft1, tore1, tore2);
            updatePunkte(mannschaft2, tore2, tore1);
        }
        
        mannschaftRepository.save(mannschaft1);
        mannschaftRepository.save(mannschaft2);
    }
    
    public void updatePunkte(Mannschaft mannschaft, int eigeneTore, int gegnerTore) {
        if (eigeneTore > gegnerTore) {
            mannschaft.setPunkte(mannschaft.getPunkte() + 3);
        } else if (eigeneTore == gegnerTore) {
            mannschaft.setPunkte(mannschaft.getPunkte() + 1);
        }
    }
}
