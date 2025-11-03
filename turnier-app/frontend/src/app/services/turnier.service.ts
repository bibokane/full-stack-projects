import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Mannschaft } from '../models/mannschaft.model';
import { Gruppe } from '../models/gruppe.model';
import { Spiel } from '../models/spiel.model';
import { TurnierStatus } from '../models/turnier-status.model';

@Injectable({
  providedIn: 'root'
})
export class TurnierService {
  private apiUrl = 'http://localhost:8081/api/turnier';

  constructor(private http: HttpClient) { }

  // Mannschaften
  createMannschaft(name: string): Observable<Mannschaft> {
    return this.http.post<Mannschaft>(`${this.apiUrl}/mannschaften`, { name });
  }

  getAllMannschaften(): Observable<Mannschaft[]> {
    return this.http.get<Mannschaft[]>(`${this.apiUrl}/mannschaften`);
  }

  deleteMannschaft(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/mannschaften/${id}`);
  }

  // Gruppen
  erstelleGruppen(): Observable<any> {
    return this.http.post(`${this.apiUrl}/gruppen/erstellen`, {});
  }

  getAllGruppen(): Observable<Gruppe[]> {
    return this.http.get<Gruppe[]>(`${this.apiUrl}/gruppen`);
  }

  getGruppentabelle(gruppenId: number): Observable<Mannschaft[]> {
    return this.http.get<Mannschaft[]>(`${this.apiUrl}/gruppen/${gruppenId}/tabelle`);
  }

  getGruppenspieleById(gruppenId: number): Observable<Spiel[]> {
    return this.http.get<Spiel[]>(`${this.apiUrl}/gruppen/${gruppenId}/spiele`);
  }

  erstelleGruppenspiele(): Observable<any> {
    return this.http.post(`${this.apiUrl}/gruppenspiele/erstellen`, {});
  }

  // Spiele
  getGruppenspiele(): Observable<Spiel[]> {
    return this.http.get<Spiel[]>(`${this.apiUrl}/spiele/gruppenspiele`);
  }

  getViertelfinale(): Observable<Spiel[]> {
    return this.http.get<Spiel[]>(`${this.apiUrl}/spiele/viertelfinale`);
  }

  getHalbfinale(): Observable<Spiel[]> {
    return this.http.get<Spiel[]>(`${this.apiUrl}/spiele/halbfinale`);
  }

  getFinale(): Observable<Spiel[]> {
    return this.http.get<Spiel[]>(`${this.apiUrl}/spiele/finale`);
  }

  getAktuelleRunde(): Observable<Spiel[]> {
    return this.http.get<Spiel[]>(`${this.apiUrl}/spiele/aktuelle-runde`);
  }

  updateSpielErgebnis(spielId: number, tore1: number, tore2: number): Observable<Spiel> {
    return this.http.put<Spiel>(`${this.apiUrl}/spiele/${spielId}/ergebnis`, { tore1, tore2 });
  }

  // Turnier
  erstelleNaechsteRunde(): Observable<any> {
    return this.http.post(`${this.apiUrl}/naechste-runde`, {});
  }

  getTurnierStatus(): Observable<TurnierStatus> {
    return this.http.get<TurnierStatus>(`${this.apiUrl}/status`);
  }

  resetTurnier(): Observable<any> {
    return this.http.post(`${this.apiUrl}/reset`, {});
  }
}





