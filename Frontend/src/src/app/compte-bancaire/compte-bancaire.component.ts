import { Component, OnInit } from '@angular/core';
import { CompteBancaire } from '../model/compte-bancaire';
import { ClientServiceService } from '../service/client-service.service';
import { Router } from '@angular/router';
import { CompteServiceService } from '../service/compte-service.service';
import { MatDialog } from '@angular/material/dialog';
import { AddCompteComponent } from '../add-compte/add-compte.component';
import { CompteModificationComponent } from '../compte-modification/compte-modification.component';
@Component({
  selector: 'app-compte-bancaire',
  templateUrl: './compte-bancaire.component.html',
  styleUrl: './compte-bancaire.component.css'
})
export class CompteBancaireComponent implements OnInit {

compte !: CompteBancaire[]
montantDebite: number | undefined; 


    constructor(private router: Router, private compteservice : CompteServiceService, public dialog : MatDialog) {  }
   
    // Afficher compte
    ngOnInit(): void {
      this.compteservice.AfficherCompte().subscribe((compte:CompteBancaire[]) => this.compte=compte);  }


      // supression
supprimerCompte(ccompteId: number): void {
  if (confirm('Êtes-vous sûr de vouloir supprimer ce client ?')) {
    this.compteservice.supprimerCompte(ccompteId).subscribe(
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

// saisire montant pour Debit
openDebitDialog(compteId: number): void {
    const dialogRef = this.dialog.open(CompteModificationComponent, {
      width: '250px',
      data: { montant: 0 } // Initialisez le montant à 0 ou à toute autre valeur par défaut
    });

    dialogRef.afterClosed().subscribe((result: any) => {
      console.log('Montant débité : ', result);
      // Ici, vous pouvez traiter le montant débité résultant de la boîte de dialogue
      this.montantDebite = result;
      this.DebiterCompte(compteId,result);
    });
  }

// montant à saisir pour credit
openCreditDialog(compteId: number): void {
  const dialogRef = this.dialog.open(CompteModificationComponent, {
    width: '250px',
    data: { montant: 0 } // Initialisez le montant à 0 ou à toute autre valeur par défaut
  });

  dialogRef.afterClosed().subscribe((result: any) => {
    console.log('Montant créditer : ', result);
    // Ici, vous pouvez traiter le montant débité résultant de la boîte de dialogue
    this.montantDebite = result;
    this.CrediterCompte(compteId,result);
  });
}
// Debiter

DebiterCompte(ccompteId: number, montant: number): void {
 
    this.compteservice.Debiter(ccompteId,montant).subscribe(
      () => {
        
        console.log('Le client a Debiter avec succès.');
        this.ngOnInit();
      }
    );
  }
// crediter
  CrediterCompte(ccompteId: number, montant: number): void {
 
    this.compteservice.Crediter(ccompteId,montant).subscribe(
      () => {
        
        console.log('Le client a Debiter avec succès.');
        this.ngOnInit();
      }
    );
  }

  openAjoutCompteDialog(): void {
    const dialogRef = this.dialog.open(AddCompteComponent, {
      width: '1000px', // Spécifiez la largeur du dialogue selon vos besoins
      height: '500px'
    });
  
    dialogRef.afterClosed().subscribe(nouveauCompte => {
      // Vérifiez si un nouveau compte a été saisi avant de procéder
      if (nouveauCompte) {
        // Ajoutez le nouveau compte en utilisant le service
        console.log(nouveauCompte)
        this.compteservice.createBankAccount(nouveauCompte.solde, nouveauCompte.clientId, nouveauCompte.compteSpecific, nouveauCompte.type)
          .subscribe(() => {
            console.log('Compte ajouté avec succès !');
            // Traitez ici la réponse de l'API si nécessaire
            this.ngOnInit();
          });
      }
    });
  }
  
}

  
  
 
  
 



    