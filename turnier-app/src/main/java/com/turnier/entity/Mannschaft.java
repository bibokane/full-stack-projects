package com.turnier.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mannschaften")
public class Mannschaft {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "gruppen_id")
    private Long gruppenId;
    
    private int punkte = 0;
    
    @Column(name = "tore_geschossen")
    private int toreGeschossen = 0;
    
    @Column(name = "tore_erhalten")
    private int toreErhalten = 0;
    
    @Column(name = "spiele_gespielt")
    private int spieleGespielt = 0;
    
    @Column(name = "ist_ausgeschieden")
    private boolean istAusgeschieden = false;
    
    @Column(name = "tordifferenz")
    private int tordifferenz = 0;
    
    // Konstruktoren
    public Mannschaft() {}
    
    public Mannschaft(String name) {
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
    
    public Long getGruppenId() {
        return gruppenId;
    }
    
    public void setGruppenId(Long gruppenId) {
        this.gruppenId = gruppenId;
    }
    
    public int getPunkte() {
        return punkte;
    }
    
    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }
    
    public int getToreGeschossen() {
        return toreGeschossen;
    }
    
    public void setToreGeschossen(int toreGeschossen) {
        this.toreGeschossen = toreGeschossen;
    }
    
    public int getToreErhalten() {
        return toreErhalten;
    }
    
    public void setToreErhalten(int toreErhalten) {
        this.toreErhalten = toreErhalten;
    }
    
    public int getSpieleGespielt() {
        return spieleGespielt;
    }
    
    public void setSpieleGespielt(int spieleGespielt) {
        this.spieleGespielt = spieleGespielt;
    }
    
    public boolean isIstAusgeschieden() {
        return istAusgeschieden;
    }
    
    public void setIstAusgeschieden(boolean istAusgeschieden) {
        this.istAusgeschieden = istAusgeschieden;
    }
    
    public int getTordifferenz() {
        return tordifferenz;
    }
    
    public void setTordifferenz(int tordifferenz) {
        this.tordifferenz = tordifferenz;
    }
}
