import { Component, OnInit } from '@angular/core';
import { Client } from '../model/client';
import { ClientServiceService } from '../service/client-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-client',
  templateUrl: './list-client.component.html',
  styleUrl: './list-client.component.css'
})
export class ListClientComponent implements OnInit {
client !: Client[]
selectedClientId: number | null = null;
selectedClient: Client | null = null;
  constructor(private clientservice : ClientServiceService,private router: Router) {  }
  ngOnInit(): void {
    this.clientservice.AfficherClient().subscribe((data: Client[]) => this.client=data);  }

    confirmSuppression(clientId: number): void {
      if (confirm('Êtes-vous sûr de vouloir supprimer ce client ?')) {
        this.supprimerClient(clientId);
      }
    }

    // supression
    supprimerClient(clientId: number): void {
      if (confirm('Êtes-vous sûr de vouloir supprimer ce client ?')) {
        this.clientservice.supprimerClient(clientId).subscribe(
          () => {
            // Recharge la liste des clients après la suppression
            this.ngOnInit();
            console.log('Le client a été supprimé avec succès.');
          },
          (error) => {
            console.error('Une erreur est survenue lors de la suppression du client :', error);
          }
        );
      }

      
}

modifierClient(client: Client): void {
  // Set the client ID in the service
  this.clientservice.setClientId(client.id);
  // Navigate to the modify component
  this.router.navigate(['/modifieClient']);
}

}
