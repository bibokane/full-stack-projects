package com.turnier.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "spiele")
public class Spiel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mannschaft1_id")
    private Mannschaft mannschaft1;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mannschaft2_id")
    private Mannschaft mannschaft2;
    
    @Column(name = "tore_mannschaft1")
    private Integer toreMannschaft1;
    
    @Column(name = "tore_mannschaft2")
    private Integer toreMannschaft2;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "spiel_typ")
    private SpielTyp spielTyp;
    
    @Column(name = "runde")
    private int runde;
    
    @Column(name = "ist_beendet")
    private boolean istBeendet = false;
    
    // Enum für Spieltypen
    public enum SpielTyp {
        GRUPPENSPIEL,
        VIERTELFINALE,
        HALBFINALE,
        FINALE
    }
    
    // Konstruktoren
    public Spiel() {}
    
    public Spiel(Mannschaft mannschaft1, Mannschaft mannschaft2, SpielTyp spielTyp, int runde) {
        this.mannschaft1 = mannschaft1;
        this.mannschaft2 = mannschaft2;
        this.spielTyp = spielTyp;
        this.runde = runde;
    }
    
    // Getter und Setter
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Mannschaft getMannschaft1() {
        return mannschaft1;
    }
    
    public void setMannschaft1(Mannschaft mannschaft1) {
        this.mannschaft1 = mannschaft1;
    }
    
    public Mannschaft getMannschaft2() {
        return mannschaft2;
    }
    
    public void setMannschaft2(Mannschaft mannschaft2) {
        this.mannschaft2 = mannschaft2;
    }
    
    public Integer getToreMannschaft1() {
        return toreMannschaft1;
    }
    
    public void setToreMannschaft1(Integer toreMannschaft1) {
        this.toreMannschaft1 = toreMannschaft1;
    }
    
    public Integer getToreMannschaft2() {
        return toreMannschaft2;
    }
    
    public void setToreMannschaft2(Integer toreMannschaft2) {
        this.toreMannschaft2 = toreMannschaft2;
    }
    
    public SpielTyp getSpielTyp() {
        return spielTyp;
    }
    
    public void setSpielTyp(SpielTyp spielTyp) {
        this.spielTyp = spielTyp;
    }
    
    public int getRunde() {
        return runde;
    }
    
    public void setRunde(int runde) {
        this.runde = runde;
    }
    
    public boolean isIstBeendet() {
        return istBeendet;
    }
    
    public void setIstBeendet(boolean istBeendet) {
        this.istBeendet = istBeendet;
    }
}
