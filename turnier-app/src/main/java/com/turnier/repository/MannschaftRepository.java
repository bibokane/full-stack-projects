package com.turnier.repository;

import com.turnier.entity.Mannschaft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MannschaftRepository extends JpaRepository<Mannschaft, Long> {
    
    List<Mannschaft> findByGruppenIdOrderByPunkteDescToreGeschossenDesc(Long gruppenId);
    
    @Query("SELECT m FROM Mannschaft m WHERE m.gruppenId = :gruppenId ORDER BY m.punkte DESC, (m.toreGeschossen - m.toreErhalten) DESC, m.toreGeschossen DESC")
    List<Mannschaft> findGruppentabelle(Long gruppenId);
    
    long count();
}
