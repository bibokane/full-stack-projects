import { Mannschaft } from './mannschaft.model';

export interface Spiel {
  id: number;
  mannschaft1: Mannschaft;
  mannschaft2: Mannschaft;
  toreMannschaft1?: number;
  toreMannschaft2?: number;
  spielTyp: 'GRUPPENSPIEL' | 'VIERTELFINALE' | 'HALBFINALE' | 'FINALE';
  runde: number;
  istBeendet: boolean;
}





