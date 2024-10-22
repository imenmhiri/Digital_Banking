import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Client} from '../model/client'
import { CompteBancaire } from '../model/compte-bancaire';
@Injectable({
  providedIn: 'root'
})
export class ClientServiceService {
  private clientId: number | undefined;



  constructor(private http: HttpClient) { }

  // Afficher Client
 public AfficherClient(): Observable<Client[]> {
    return this.http.get<Client[]>("http://localhost:8080/clients");

  }

  

  // Suprimer

  public supprimerClient(clientId: number): Observable<void> {
    return this.http.delete<void>(`http://localhost:8080/clients/${clientId}`);
  }

  // modiffier un client

  
  
  modifierClient(clientId: number, client: Client): Observable<void> {
    return this.http.put<void>(`http://localhost:8080/clients/${clientId}`, client);
  }

  ajouterClient(client: Client): Observable<Client> {
    return this.http.post<Client>('http://localhost:8080/clients', client);
  }
  
  ConsultClientById(clientId: number): Observable<Client> {
    return this.http.get<Client>(`http://localhost:8080/clients/${clientId}`);
}

getClientId(): number | undefined {
  return this.clientId;
}
setClientId(id: number): void {
  this.clientId = id;
}
}
