package com.turnier.repository;

import com.turnier.entity.Gruppe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GruppeRepository extends JpaRepository<Gruppe, Long> {
}
