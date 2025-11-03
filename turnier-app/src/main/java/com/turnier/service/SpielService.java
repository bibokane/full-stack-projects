package com.turnier.service;

import com.turnier.entity.Mannschaft;
import com.turnier.entity.Spiel;
import java.util.ArrayList;
import com.turnier.exception.SpielNotFoundException;
import com.turnier.exception.TurnierException;
import com.turnier.repository.SpielRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SpielService {
    
    @Autowired
    private SpielRepository spielRepository;
    
    public List<Spiel> getSpieleByTyp(Spiel.SpielTyp spielTyp) {
        return spielRepository.findBySpielTypOrderByRundeAsc(spielTyp);
    }
    
    public List<Spiel> getAktuelleRunde() {
        List<Spiel> alleSpiele = spielRepository.findAll();
        if (alleSpiele.isEmpty()) {
            return new ArrayList<>();
        }
        
        // Finde die höchste Runde (egal ob beendet oder nicht)
        int höchsteRunde = alleSpiele.stream()
            .mapToInt(Spiel::getRunde)
            .max()
            .orElse(0);
        
        return spielRepository.findByRundeOrderByIdAsc(höchsteRunde);
    }
    
    public Spiel updateSpielErgebnis(Long spielId, Integer tore1, Integer tore2) {
        Spiel spiel = spielRepository.findById(spielId)
            .orElseThrow(() -> new SpielNotFoundException(spielId));
        
        if (spiel.getMannschaft1() == null || spiel.getMannschaft2() == null) {
            throw new TurnierException("Mannschaften im Spiel nicht gefunden!");
        }
        
        if (tore1 == null || tore2 == null) {
            throw new TurnierException("Tore dürfen nicht null sein!");
        }
        
        if (tore1 < 0 || tore2 < 0) {
            throw new TurnierException("Tore dürfen nicht negativ sein!");
        }
        
        spiel.setToreMannschaft1(tore1);
        spiel.setToreMannschaft2(tore2);
        spiel.setIstBeendet(true);
        
        return spielRepository.save(spiel);
    }
    
    public Mannschaft getGewinner(Spiel spiel) {
        if (spiel.getToreMannschaft1() == null || spiel.getToreMannschaft2() == null) {
            throw new RuntimeException("Spiel ist noch nicht beendet!");
        }
        
        if (spiel.getToreMannschaft1() > spiel.getToreMannschaft2()) {
            return spiel.getMannschaft1();
        } else if (spiel.getToreMannschaft2() > spiel.getToreMannschaft1()) {
            return spiel.getMannschaft2();
        } else {
            throw new TurnierException("Unentschieden in einem K.O.-Spiel ist nicht erlaubt!");
        }
    }
    
    public boolean sindAlleSpieleBeendet(List<Spiel> spiele) {
        return spiele.stream().allMatch(Spiel::isIstBeendet);
    }
    
    public Spiel getSpielById(Long id) {
        return spielRepository.findById(id)
            .orElseThrow(() -> new SpielNotFoundException(id));
    }
}
