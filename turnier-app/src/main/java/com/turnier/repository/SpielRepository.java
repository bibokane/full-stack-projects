package com.turnier.repository;

import com.turnier.entity.Spiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpielRepository extends JpaRepository<Spiel, Long> {
    
    List<Spiel> findBySpielTypOrderByRundeAsc(Spiel.SpielTyp spielTyp);
    
    List<Spiel> findByRundeOrderByIdAsc(int runde);
    
    List<Spiel> findByIstBeendetFalseOrderByRundeAsc();
}
