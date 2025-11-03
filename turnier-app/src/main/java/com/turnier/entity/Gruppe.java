package com.turnier.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "gruppen")
public class Gruppe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "ist_abgeschlossen")
    private boolean istAbgeschlossen = false;
    
    // Konstruktoren
    public Gruppe() {}
    
    public Gruppe(String name) {
        this.name = name;
    }
    
    // Getter und Setter
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean getIstAbgeschlossen() {
        return istAbgeschlossen;
    }
    
    public void setIstAbgeschlossen(boolean istAbgeschlossen) {
        this.istAbgeschlossen = istAbgeschlossen;
    }
}
