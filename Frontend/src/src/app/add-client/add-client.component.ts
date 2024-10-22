import { Component } from '@angular/core';
import { Client } from '../model/client';
import { ClientServiceService } from '../service/client-service.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrl: './add-client.component.css'
})
export class AddClientComponent {
  nouveauClient: Client = { id: 0, nom: '', prenom: '', email: '' };

  constructor(private clientService: ClientServiceService, private router: Router) {}

  ajouterClient(): void {
    this.clientService.ajouterClient(this.nouveauClient).subscribe(() => {
      // Redirection vers le composant liste après l'ajout
      this.router.navigate(['/listeClient']);
    });
  }
  annuler(): void {
    // Redirection vers la liste des clients
    this.router.navigate(['/listeClient']);
  }
}
