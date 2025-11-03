import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Gruppe } from '../../models/gruppe.model';
import { Mannschaft } from '../../models/mannschaft.model';

@Component({
  selector: 'app-gruppen',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div *ngIf="gruppen.length === 0" class="text-center" style="padding: 40px; color: #a0aec0;">
      <p>Noch keine Gruppen erstellt</p>
      <p style="font-size: 14px; margin-top: 8px;">Erstellen Sie zuerst 16 Mannschaften, dann können Sie die Gruppen bilden.</p>
    </div>

    <div *ngIf="gruppen.length > 0" class="group-container">
      <div *ngFor="let gruppe of gruppen" class="group-card">
        <div class="group-header">
          Gruppe {{ gruppe.name }}
        </div>
        <div *ngIf="gruppentabellen[gruppe.id] && gruppentabellen[gruppe.id].length > 0">
          <table class="group-table">
            <thead>
              <tr>
                <th>Platz</th>
                <th>Mannschaft</th>
                <th>Pkt</th>
                <th>Tore</th>
                <th>Diff</th>
              </tr>
            </thead>
            <tbody>
              <tr *ngFor="let mannschaft of gruppentabellen[gruppe.id]; let i = index">
                <td>{{ i + 1 }}</td>
                <td>{{ mannschaft.name }}</td>
                <td>{{ mannschaft.punkte }}</td>
                <td>{{ mannschaft.toreGeschossen }}:{{ mannschaft.toreErhalten }}</td>
                <td>{{ mannschaft.tordifferenz > 0 ? '+' : '' }}{{ mannschaft.tordifferenz }}</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div *ngIf="!gruppentabellen[gruppe.id] || gruppentabellen[gruppe.id].length === 0" 
             class="text-center" style="padding: 20px; color: #a0aec0;">
          <p>Keine Mannschaften in dieser Gruppe</p>
        </div>
      </div>
    </div>
  `,
  styles: []
})
export class GruppenComponent {
  @Input() gruppen: Gruppe[] = [];
  @Input() gruppentabellen: { [key: number]: Mannschaft[] } = {};
}





