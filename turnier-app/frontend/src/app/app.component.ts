import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { TurnierService } from './services/turnier.service';
import { TurnierStatus } from './models/turnier-status.model';
import { MannschaftenComponent } from './components/mannschaften/mannschaften.component';
import { GruppenComponent } from './components/gruppen/gruppen.component';
import { SpieleComponent } from './components/spiele/spiele.component';
import { FinaleComponent } from './components/finale/finale.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    MannschaftenComponent,
    GruppenComponent,
    SpieleComponent,
    FinaleComponent
  ],
  template: `
    <div class="container">
      <header class="card">
        <div class="card-header">
          <h1 class="card-title">🏆 Turnier App</h1>
          <div class="status-badge" [ngClass]="{
            'status-pending': !turnierStatus?.istBeendet,
            'status-completed': turnierStatus?.istBeendet
          }">
            {{ turnierStatus?.istBeendet ? 'Beendet' : 'Laufend' }}
          </div>
        </div>
        <p style="font-size: 16px; color: #4a5568; margin-top: 12px;">Verwalten Sie Ihr 16-Mannschaften-Turnier mit Gruppenphase und K.O.-System</p>
      </header>

      <div class="grid grid-2">
        <!-- Mannschaften -->
        <div class="card">
          <div class="card-header">
            <h2 class="card-title">Mannschaften</h2>
            <span class="status-badge status-pending">
              {{ mannschaften.length }}/16
            </span>
          </div>
          <app-mannschaften 
            [mannschaften]="mannschaften"
            (mannschaftAdded)="onMannschaftAdded()"
            (mannschaftDeleted)="onMannschaftDeleted()">
          </app-mannschaften>
        </div>

        <!-- Gruppen -->
        <div class="card">
          <div class="card-header">
            <h2 class="card-title">Gruppen</h2>
            <button 
              class="btn btn-primary" 
              (click)="erstelleGruppen()"
              [disabled]="mannschaften.length !== 16">
              Gruppen erstellen
            </button>
          </div>
          <app-gruppen 
            [gruppen]="gruppen"
            [gruppentabellen]="gruppentabellen">
          </app-gruppen>
        </div>
      </div>

      <!-- Gruppenphase starten Button -->
      <div *ngIf="gruppen.length > 0 && spiele.length === 0 && viertelfinale.length === 0" class="card">
        <div class="card-header">
          <h2 class="card-title">Spiele</h2>
          <button 
            class="btn btn-success" 
            (click)="erstelleGruppenspiele()">
            Gruppenphase starten
          </button>
        </div>
      </div>

      <!-- Gruppenspiele -->
      <div *ngIf="spiele.length > 0" class="card">
        <div class="card-header">
          <h2 class="card-title">Gruppenspiele</h2>
        </div>
        <app-spiele 
          [spiele]="spiele"
          (spielUpdated)="onSpielUpdated()">
        </app-spiele>
      </div>

      <!-- Viertelfinale -->
      <div *ngIf="viertelfinale.length > 0" class="card">
        <div class="card-header">
          <h2 class="card-title">Viertelfinale</h2>
        </div>
        <app-spiele 
          [spiele]="viertelfinale"
          (spielUpdated)="onSpielUpdated()">
        </app-spiele>
      </div>

      <!-- Halbfinale -->
      <div *ngIf="halbfinale.length > 0" class="card">
        <div class="card-header">
          <h2 class="card-title">Halbfinale</h2>
        </div>
        <app-spiele 
          [spiele]="halbfinale"
          (spielUpdated)="onSpielUpdated()">
        </app-spiele>
      </div>

      <!-- Finale -->
      <app-finale 
        [turnierStatus]="turnierStatus"
        [finale]="finale"
        (spielUpdated)="onSpielUpdated()">
      </app-finale>
    </div>
  `,
  styles: []
})
export class AppComponent implements OnInit {
  mannschaften: any[] = [];
  gruppen: any[] = [];
  gruppentabellen: { [key: number]: any[] } = {};
  spiele: any[] = [];
  viertelfinale: any[] = [];
  halbfinale: any[] = [];
  finale: any[] = [];
  turnierStatus: TurnierStatus | null = null;

  constructor(private turnierService: TurnierService) {}

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.loadMannschaften();
    this.loadGruppen();
    this.loadSpiele();
    this.loadViertelfinale();
    this.loadHalbfinale();
    this.loadFinale();
    this.loadTurnierStatus();
  }

  loadMannschaften() {
    this.turnierService.getAllMannschaften().subscribe({
      next: (mannschaften) => {
        this.mannschaften = mannschaften;
        this.loadGruppentabellen();
      },
      error: (error) => console.error('Fehler beim Laden der Mannschaften:', error)
    });
  }

  loadGruppen() {
    this.turnierService.getAllGruppen().subscribe({
      next: (gruppen) => {
        this.gruppen = gruppen;
        this.loadGruppentabellen();
      },
      error: (error) => console.error('Fehler beim Laden der Gruppen:', error)
    });
  }

  loadGruppentabellen() {
    if (this.gruppen.length > 0) {
      this.gruppen.forEach(gruppe => {
        this.turnierService.getGruppentabelle(gruppe.id).subscribe({
          next: (tabelle) => {
            this.gruppentabellen[gruppe.id] = tabelle;
          },
          error: (error) => console.error('Fehler beim Laden der Gruppentabelle:', error)
        });
      });
    }
  }

  loadSpiele() {
    this.turnierService.getGruppenspiele().subscribe({
      next: (spiele) => {
        this.spiele = spiele;
      },
      error: (error) => console.error('Fehler beim Laden der Gruppenspiele:', error)
    });
  }

  loadViertelfinale() {
    this.turnierService.getViertelfinale().subscribe({
      next: (spiele) => {
        this.viertelfinale = spiele;
      },
      error: (error) => console.error('Fehler beim Laden der Viertelfinale:', error)
    });
  }

  loadHalbfinale() {
    this.turnierService.getHalbfinale().subscribe({
      next: (spiele) => {
        this.halbfinale = spiele;
      },
      error: (error) => console.error('Fehler beim Laden der Halbfinale:', error)
    });
  }

  loadFinale() {
    this.turnierService.getFinale().subscribe({
      next: (finale) => {
        this.finale = finale;
      },
      error: (error) => console.error('Fehler beim Laden des Finales:', error)
    });
  }

  loadTurnierStatus() {
    this.turnierService.getTurnierStatus().subscribe({
      next: (status) => {
        this.turnierStatus = status;
      },
      error: (error) => console.error('Fehler beim Laden des Turnierstatus:', error)
    });
  }

  onMannschaftAdded() {
    this.loadMannschaften();
  }

  onMannschaftDeleted() {
    this.loadMannschaften();
  }

  onSpielUpdated() {
    this.loadData();
  }

  erstelleGruppen() {
    this.turnierService.erstelleGruppen().subscribe({
      next: () => {
        this.loadData();
        alert('Gruppen erfolgreich erstellt!');
      },
      error: (error) => {
        console.error('Fehler beim Erstellen der Gruppen:', error);
        alert('Fehler beim Erstellen der Gruppen!');
      }
    });
  }

  erstelleGruppenspiele() {
    this.turnierService.erstelleGruppenspiele().subscribe({
      next: () => {
        this.loadData();
        alert('Gruppenspiele erfolgreich erstellt!');
      },
      error: (error) => {
        console.error('Fehler beim Erstellen der Gruppenspiele:', error);
        alert('Fehler beim Erstellen der Gruppenspiele!');
      }
    });
  }
}
