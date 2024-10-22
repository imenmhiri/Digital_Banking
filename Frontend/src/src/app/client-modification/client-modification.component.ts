import { Component, OnInit } from '@angular/core';
import { Client } from '../model/client';
import {  Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ClientServiceService } from '../service/client-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-client-modification',
  templateUrl: './client-modification.component.html',
  styleUrl: './client-modification.component.css'
})
export class ClientModificationComponent implements OnInit {
  clientId !: number ;
  client: Client = {
    id: 0,
    nom: '',
    prenom: '',
    email: ''
  };
  

  constructor(private clientService: ClientServiceService, private router: Router) {}
  ngOnInit(): void {
    // Get the client ID from the service
    this.clientId = Number(this.clientService.getClientId());
    // Charger les détails du client à partir de l'API
    this.clientService.ConsultClientById(this.clientId).subscribe((client: Client) => {
      this.client = client;
    });
  }

  modifierClient(): void {
    // Update the client details using the service
    this.clientService.modifierClient(this.client.id, this.client).subscribe(() => {
      // Redirection vers le composant liste après l'ajout
      this.router.navigate(['/listeClient']);
    });
  }
  annuler(): void {
    // Redirection vers la liste des clients
    this.router.navigate(['/listeClient']);
  }

  
}