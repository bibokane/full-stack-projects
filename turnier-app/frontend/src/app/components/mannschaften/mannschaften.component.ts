import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TurnierService } from '../../services/turnier.service';
import { Mannschaft } from '../../models/mannschaft.model';

@Component({
  selector: 'app-mannschaften',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="form-group">
      <label class="form-label" for="mannschaftName">Mannschaft hinzufügen:</label>
      <div style="display: flex; gap: 12px;">
        <input
          id="mannschaftName"
          type="text"
          class="form-input"
          [(ngModel)]="neueMannschaft"
          placeholder="Mannschaftsname eingeben..."
          (keyup.enter)="addMannschaft()"
          [disabled]="mannschaften.length >= 16">
        <button
          class="btn btn-primary"
          (click)="addMannschaft()"
          [disabled]="!neueMannschaft || !neueMannschaft.trim() || mannschaften.length >= 16">
          Hinzufügen
        </button>
      </div>
    </div>

    <div *ngIf="mannschaften.length > 0" class="team-list">
      <div *ngFor="let mannschaft of mannschaften" class="team-item">
        <span class="team-name">{{ mannschaft.name }}</span>
        <button
          class="team-remove"
          (click)="removeMannschaft(mannschaft.id)"
          [disabled]="mannschaften.length <= 16">
          ✕
        </button>
      </div>
    </div>

    <div *ngIf="mannschaften.length === 0" class="text-center" style="padding: 40px; color: #a0aec0;">
      <p>Noch keine Mannschaften hinzugefügt</p>
    </div>

    <div *ngIf="mannschaften.length === 16" class="text-center" style="padding: 24px; background: linear-gradient(135deg, rgba(76, 175, 80, 0.1) 0%, rgba(46, 125, 50, 0.1) 100%); border-radius: 16px; margin-top: 20px; border: 2px solid rgba(76, 175, 80, 0.3); box-shadow: 0 8px 20px rgba(76, 175, 80, 0.2);">
      <p style="color: #2e7d32; font-weight: 700; font-size: 16px;">✅ Alle 16 Mannschaften registriert! Sie können jetzt die Gruppen erstellen.</p>
    </div>
  `,
  styles: []
})
export class MannschaftenComponent {
  @Input() mannschaften: Mannschaft[] = [];
  @Output() mannschaftAdded = new EventEmitter<void>();
  @Output() mannschaftDeleted = new EventEmitter<void>();

  neueMannschaft = '';

  constructor(private turnierService: TurnierService) {}

  addMannschaft() {
    if (!this.neueMannschaft || !this.neueMannschaft.trim()) {
      alert('Bitte geben Sie einen Mannschaftsnamen ein!');
      return;
    }
    
    if (this.mannschaften.length >= 16) {
      alert('Maximale Anzahl von 16 Mannschaften erreicht!');
      return;
    }
    
    // Prüfe, ob die Mannschaft bereits existiert (case-insensitive)
    const mannschaftExistiert = this.mannschaften.some(
      m => m.name.trim().toLowerCase() === this.neueMannschaft.trim().toLowerCase()
    );
    
    if (mannschaftExistiert) {
      alert('Eine Mannschaft mit diesem Namen existiert bereits!');
      return;
    }
    
    this.turnierService.createMannschaft(this.neueMannschaft.trim()).subscribe({
      next: () => {
        this.neueMannschaft = '';
        this.mannschaftAdded.emit();
      },
      error: (error) => {
        console.error('Fehler beim Hinzufügen der Mannschaft:', error);
        
        let errorMessage = 'Fehler beim Hinzufügen der Mannschaft!';
        
        // Netzwerkfehler
        if (error.status === 0 || error.message?.includes('Failed to fetch') || error.message?.includes('Network')) {
          errorMessage = 'Verbindungsfehler: Backend nicht erreichbar! Bitte stellen Sie sicher, dass das Backend läuft (Port 8081).';
        }
        // HTTP-Fehler vom Backend
        else if (error.error) {
          if (typeof error.error === 'string') {
            errorMessage = error.error;
          } else if (error.error.message) {
            errorMessage = error.error.message;
          } else if (error.error.error) {
            errorMessage = error.error.error;
          }
        }
        // Status-Code ohne Body
        else if (error.status === 400) {
          errorMessage = 'Ungültige Eingabe! Bitte prüfen Sie den Mannschaftsnamen.';
        } else if (error.status === 500) {
          errorMessage = 'Serverfehler beim Erstellen der Mannschaft!';
        }
        
        alert(errorMessage);
      }
    });
  }

  removeMannschaft(id: number) {
    this.turnierService.deleteMannschaft(id).subscribe({
      next: () => {
        this.mannschaftDeleted.emit();
      },
      error: (error) => {
        console.error('Fehler beim Löschen der Mannschaft:', error);
        alert('Fehler beim Löschen der Mannschaft!');
      }
    });
  }
}



