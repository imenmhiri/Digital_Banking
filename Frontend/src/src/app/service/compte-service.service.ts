import { HttpClient,HttpHeaders,HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CompteBancaire } from '../model/compte-bancaire';

@Injectable({
  providedIn: 'root'
})
export class CompteServiceService {
  private apiUrl = 'http://localhost:8080/comptesbancaires';
  constructor( private http: HttpClient) { }
  // Afficher Comptes
  public AfficherCompte(): Observable<CompteBancaire[]> {
    return this.http.get<CompteBancaire[]>("http://localhost:8080/comptebancaire");
  
  }

  
  // Suprimer

  public supprimerCompte(compteBancaireId: number): Observable<void> {
    return this.http.delete<void>(`http://localhost:8080/comptebancaire/${compteBancaireId}`);

  }

  // Debiter

  Debiter(compteBancaireId: number, montant: number): Observable<CompteBancaire> {
    return this.http.post<CompteBancaire>(`http://localhost:8080/comptebancaire/debit/${compteBancaireId}/${montant}`, {});
  }
  Crediter(compteBancaireId: number, montant: number): Observable<CompteBancaire> {
    return this.http.post<CompteBancaire>(`http://localhost:8080/comptebancaire/credit/${compteBancaireId}/${montant}`, {});
  }
// ajouter 
ajouterCompte(solde: number, clientId: number, compteSpecific: number, type: string): Observable<void> {
  return this.http.post<void>('/api/comptesbancaires', { solde, clientId, compteSpecific, type });
}

enregistrerCompte(solde: number, clientId: number, compteSpecific: number, type: string) {
  const url = `${this.apiUrl}/comptesbancaires`;
  return this.http.post(url, { solde, clientId, compteSpecific, type });
}

createBankAccount(solde: number, clientId: number, compteSpecific: number, type: string): Observable<any> {
  const headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  const params = new HttpParams()
    .set('solde', solde.toString())
    .set('clientId', clientId.toString())
    .set('compteSpecific', compteSpecific.toString())
    .set('type', type);

  return this.http.post<any>(this.apiUrl, {}, { headers, params });
}
  
}
