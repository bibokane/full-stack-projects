import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TurnierService } from '../../services/turnier.service';
import { Spiel } from '../../models/spiel.model';

@Component({
  selector: 'app-spiele',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div *ngIf="spiele.length === 0" class="text-center" style="padding: 40px; color: #a0aec0;">
      <p>Noch keine Spiele verfügbar</p>
      <p style="font-size: 14px; margin-top: 8px;">Starten Sie die Gruppenphase, um Spiele zu erstellen.</p>
    </div>

    <div *ngIf="spiele.length > 0" class="game-list">
      <div *ngFor="let spiel of spiele" class="game-card">
        <div class="game-header">
          <span class="game-type">{{ getSpielTypText(spiel.spielTyp) }}</span>
          <span class="status-badge" [ngClass]="{
            'status-pending': !spiel.istBeendet,
            'status-completed': spiel.istBeendet
          }">
            {{ spiel.istBeendet ? 'Beendet' : 'Ausstehend' }}
          </span>
        </div>

        <div class="game-teams">
          <div class="team-vs">
            <span class="team-name">{{ spiel.mannschaft1.name }}</span>
            <span class="vs">vs</span>
            <span class="team-name">{{ spiel.mannschaft2.name }}</span>
          </div>
        </div>

        <div *ngIf="spiel.istBeendet" class="game-score">
          <span style="font-size: 32px; font-weight: 800; color: #ff5722; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1); letter-spacing: 2px;">
            {{ spiel.toreMannschaft1 }} : {{ spiel.toreMannschaft2 }}
          </span>
        </div>

        <div *ngIf="!spiel.istBeendet" class="game-score">
          <input
            type="number"
            class="score-input"
            [(ngModel)]="spiel.toreMannschaft1"
            placeholder="0"
            min="0"
            value="0">
          <span style="font-size: 18px; font-weight: 500; color: #a0aec0;">:</span>
          <input
            type="number"
            class="score-input"
            [(ngModel)]="spiel.toreMannschaft2"
            placeholder="0"
            min="0"
            value="0">
        </div>

        <div class="game-actions">
          <button
            *ngIf="!spiel.istBeendet"
            class="btn btn-success"
            (click)="updateSpiel(spiel)"
            [disabled]="spiel.toreMannschaft1 === undefined || spiel.toreMannschaft2 === undefined">
            Ergebnis speichern
          </button>
          <button
            *ngIf="spiel.istBeendet"
            class="btn btn-primary"
            (click)="resetSpiel(spiel)">
            Ergebnis ändern
          </button>
        </div>
      </div>
    </div>

    <div *ngIf="spiele.length > 0 && alleSpieleBeendet() && !istFinale()" class="text-center" style="padding: 28px; background: linear-gradient(135deg, rgba(255, 87, 34, 0.1) 0%, rgba(233, 30, 99, 0.1) 100%); border-radius: 20px; margin-top: 24px; border: 2px solid rgba(255, 87, 34, 0.3); box-shadow: 0 12px 30px rgba(255, 87, 34, 0.2); animation: fadeIn 0.6s ease-out;">
      <p style="color: #e64a19; font-weight: 700; font-size: 18px; margin-bottom: 16px;">✅ Alle Spiele dieser Runde sind beendet!</p>
      <button class="btn btn-primary" (click)="erstelleNaechsteRunde()" style="margin-top: 12px; padding: 16px 32px; font-size: 16px;">
        🎯 Nächste Runde erstellen
      </button>
    </div>
  `,
  styles: []
})
export class SpieleComponent {
  @Input() spiele: Spiel[] = [];
  @Output() spielUpdated = new EventEmitter<void>();

  constructor(private turnierService: TurnierService) {}

  getSpielTypText(spielTyp: string): string {
    switch (spielTyp) {
      case 'GRUPPENSPIEL': return 'Gruppenspiel';
      case 'VIERTELFINALE': return 'Viertelfinale';
      case 'HALBFINALE': return 'Halbfinale';
      case 'FINALE': return 'Finale';
      default: return spielTyp;
    }
  }

  updateSpiel(spiel: Spiel) {
    const tore1 = spiel.toreMannschaft1 !== undefined && spiel.toreMannschaft1 !== null ? Number(spiel.toreMannschaft1) : 0;
    const tore2 = spiel.toreMannschaft2 !== undefined && spiel.toreMannschaft2 !== null ? Number(spiel.toreMannschaft2) : 0;
    
    if (isNaN(tore1) || isNaN(tore2) || tore1 < 0 || tore2 < 0) {
      alert('Bitte geben Sie gültige Tore ein (0 oder höher)!');
      return;
    }
    
    console.log('Sende Ergebnis:', { spielId: spiel.id, tore1, tore2 });
    
    this.turnierService.updateSpielErgebnis(spiel.id, tore1, tore2).subscribe({
      next: () => {
        this.spielUpdated.emit();
        alert('Ergebnis erfolgreich gespeichert!');
      },
      error: (error) => {
        console.error('Fehler beim Aktualisieren des Spiels:', error);
        console.error('Error details:', error);
        alert('Fehler beim Speichern des Ergebnisses! Bitte prüfen Sie die Konsole.');
      }
    });
  }

  resetSpiel(spiel: Spiel) {
    spiel.istBeendet = false;
    spiel.toreMannschaft1 = undefined;
    spiel.toreMannschaft2 = undefined;
  }

  alleSpieleBeendet(): boolean {
    return this.spiele.every(spiel => spiel.istBeendet);
  }

  istFinale(): boolean {
    return this.spiele.length > 0 && this.spiele.some(spiel => spiel.spielTyp === 'FINALE');
  }

  erstelleNaechsteRunde() {
    this.turnierService.erstelleNaechsteRunde().subscribe({
      next: () => {
        this.spielUpdated.emit();
        alert('Nächste Runde erfolgreich erstellt!');
      },
      error: (error) => {
        console.error('Fehler beim Erstellen der nächsten Runde:', error);
        alert('Fehler beim Erstellen der nächsten Runde!');
      }
    });
  }
}
