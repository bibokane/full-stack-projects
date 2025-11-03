import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TurnierStatus } from '../../models/turnier-status.model';
import { Spiel } from '../../models/spiel.model';
import { TurnierService } from '../../services/turnier.service';

@Component({
  selector: 'app-finale',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <!-- Finale-Spiel anzeigen (immer wenn finale.length > 0) -->
    <div *ngIf="finale.length > 0" class="card">
      <div class="card-header">
        <h2 class="card-title">🏆 Finale</h2>
        <span class="status-badge status-winner">Finale</span>
      </div>
      <div class="game-list">
        <div *ngFor="let spiel of finale" class="game-card" style="border-left-color: #fbbf24;">
          <div class="game-header">
            <span class="game-type" style="background: #fbbf24;">Finale</span>
            <span class="status-badge" [ngClass]="{
              'status-pending': !spiel.istBeendet,
              'status-completed': spiel.istBeendet
            }">
              {{ spiel.istBeendet ? 'Beendet' : 'Ausstehend' }}
            </span>
          </div>

          <div class="game-teams">
            <div class="team-vs">
              <span class="team-name" style="font-size: 18px; font-weight: 600;">{{ spiel.mannschaft1.name }}</span>
              <span class="vs" style="font-size: 16px;">vs</span>
              <span class="team-name" style="font-size: 18px; font-weight: 600;">{{ spiel.mannschaft2.name }}</span>
            </div>
          </div>

          <div *ngIf="spiel.istBeendet" class="game-score">
            <span style="font-size: 48px; font-weight: 900; color: #ff5722; text-shadow: 3px 3px 6px rgba(0, 0, 0, 0.2); letter-spacing: 3px;">
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
              style="width: 80px; font-size: 18px; font-weight: 600;">
            <span style="font-size: 24px; font-weight: 500; color: #a0aec0;">:</span>
            <input
              type="number"
              class="score-input"
              [(ngModel)]="spiel.toreMannschaft2"
              placeholder="0"
              min="0"
              style="width: 80px; font-size: 18px; font-weight: 600;">
          </div>

          <div class="game-actions">
            <button
              *ngIf="!spiel.istBeendet"
              class="btn btn-success"
              (click)="updateSpiel(spiel)"
              [disabled]="spiel.toreMannschaft1 === undefined || spiel.toreMannschaft2 === undefined"
              style="padding: 16px 32px; font-size: 16px; font-weight: 600;">
              Ergebnis speichern
            </button>
            <button
              *ngIf="spiel.istBeendet"
              class="btn btn-primary"
              (click)="resetSpiel(spiel)"
              style="padding: 16px 32px; font-size: 16px; font-weight: 600;">
              Ergebnis ändern
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Sieger-Karte zusätzlich anzeigen, wenn Turnier beendet ist -->
    <div *ngIf="turnierStatus?.istBeendet && finale.length > 0 && finale[0].istBeendet" class="winner-card" style="margin-top: 32px;">
      <div class="winner-trophy">🏆</div>
      <h1 class="winner-title">🎉 Turnier beendet! 🎉</h1>
      <h2 class="winner-name">{{ turnierStatus?.sieger }}</h2>
      <p style="font-size: 20px; opacity: 0.95; font-weight: 600; text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2);">Herzlichen Glückwunsch zum Sieg! 🏆</p>
    </div>
  `,
  styles: []
})
export class FinaleComponent {
  @Input() turnierStatus: TurnierStatus | null = null;
  @Input() finale: Spiel[] = [];
  @Output() spielUpdated = new EventEmitter<void>();

  constructor(private turnierService: TurnierService) {}

  updateSpiel(spiel: Spiel) {
    const tore1 = spiel.toreMannschaft1 !== undefined && spiel.toreMannschaft1 !== null ? Number(spiel.toreMannschaft1) : 0;
    const tore2 = spiel.toreMannschaft2 !== undefined && spiel.toreMannschaft2 !== null ? Number(spiel.toreMannschaft2) : 0;
    
    if (isNaN(tore1) || isNaN(tore2) || tore1 < 0 || tore2 < 0) {
      alert('Bitte geben Sie gültige Tore ein (0 oder höher)!');
      return;
    }
    
    console.log('Sende Finale-Ergebnis:', { spielId: spiel.id, tore1, tore2 });
    
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
}
