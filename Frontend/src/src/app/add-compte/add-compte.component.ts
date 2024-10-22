import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CompteBancaire } from '../model/compte-bancaire';
import { CompteServiceService } from '../service/compte-service.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-add-compte',
  templateUrl: './add-compte.component.html',
  styleUrl: './add-compte.component.css'
})
export class AddCompteComponent {
  nouveauCompte: CompteBancaire = {
    id: 0,
    solde: 0, // Initialisez le solde à 0 ou à toute autre valeur par défaut
    etat: '', // Initialisez l'état à une chaîne vide ou à toute autre valeur par défaut
    client: { id: 0, nom: '', prenom: '', email: '' }, // Initialisez le client à un nouvel objet client ou à toute autre valeur par défaut
    decouvert: 0, // Initialisez le découvert à 0 ou à toute autre valeur par défaut
    tauxInteret: 0 // Initialisez le taux d'intérêt à 0 ou à toute autre valeur par défaut
    ,
    datedecreation: new Date()
  };
  typeCompte: string = ""
  constructor(private compteService: CompteServiceService, private router: Router,  public dialogRef: MatDialogRef<AddCompteComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}

    enregistrerCompte(): void {
      const nouveauCompte = {
        clientId: this.nouveauCompte.client.id,
        solde: this.nouveauCompte.solde,
        compteSpecific: this.nouveauCompte.decouvert,
        type: this.typeCompte
      };
      this.dialogRef.close(nouveauCompte);
    }
  
  onNoClick(): void {
    this.dialogRef.close();
  }
  
}
