import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListClientComponent } from './list-client/list-client.component';
import { AppComponent } from './app.component';
import { ClientModificationComponent } from './client-modification/client-modification.component';
import { AddClientComponent } from './add-client/add-client.component';
import { InfoComponent } from './info/info.component';
import { CompteBancaireComponent } from './compte-bancaire/compte-bancaire.component';
import { AddCompteComponent } from './add-compte/add-compte.component';



const routes: Routes = [
  {path:"listeClient", component: ListClientComponent},
  {path:"home", component: AppComponent},
  {path:"modifieClient", component: ClientModificationComponent},
  {path:"addClient", component: AddClientComponent},
  {path:"info", component: InfoComponent},
  {path:"CompteBancaire", component: CompteBancaireComponent},
  {path:"addCompte", component: AddCompteComponent}


  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
