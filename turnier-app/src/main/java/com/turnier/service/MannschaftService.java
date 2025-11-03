package com.turnier.service;

import com.turnier.entity.Mannschaft;
import com.turnier.exception.TurnierException;
import com.turnier.repository.MannschaftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MannschaftService {
    
    private static final int MAX_MANNSCHAFTEN = 16;
    
    @Autowired
    private MannschaftRepository mannschaftRepository;
    
    public Mannschaft createMannschaft(String name) {
        validateMannschaftsName(name);
        checkMaxAnzahl();
        checkDuplicateName(name);
        
        Mannschaft mannschaft = new Mannschaft(name.trim());
        return mannschaftRepository.save(mannschaft);
    }
    
    public List<Mannschaft> getAllMannschaften() {
        return mannschaftRepository.findAll();
    }
    
    public Mannschaft getMannschaftById(Long id) {
        return mannschaftRepository.findById(id)
            .orElseThrow(() -> new com.turnier.exception.MannschaftNotFoundException(id));
    }
    
    public void deleteMannschaft(Long id) {
        if (!mannschaftRepository.existsById(id)) {
            throw new com.turnier.exception.MannschaftNotFoundException(id);
        }
        mannschaftRepository.deleteById(id);
    }
    
    public long getAnzahlMannschaften() {
        return mannschaftRepository.count();
    }
    
    public boolean isValidAnzahlFuerGruppen() {
        return mannschaftRepository.count() == MAX_MANNSCHAFTEN;
    }
    
    public void save(Mannschaft mannschaft) {
        mannschaftRepository.save(mannschaft);
    }
    
    private void validateMannschaftsName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new TurnierException("Mannschaftsname darf nicht leer sein!");
        }
    }
    
    private void checkMaxAnzahl() {
        if (mannschaftRepository.count() >= MAX_MANNSCHAFTEN) {
            throw new TurnierException("Maximale Anzahl von " + MAX_MANNSCHAFTEN + " Mannschaften erreicht!");
        }
    }
    
    private void checkDuplicateName(String name) {
        List<Mannschaft> alleMannschaften = mannschaftRepository.findAll();
        for (Mannschaft m : alleMannschaften) {
            if (m.getName().trim().equalsIgnoreCase(name.trim())) {
                throw new TurnierException("Eine Mannschaft mit dem Namen '" + name + "' existiert bereits!");
            }
        }
    }
}
