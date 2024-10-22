import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { ListClientComponent } from './list-client/list-client.component';
import { ClientServiceService } from './service/client-service.service';
import { CompteServiceService } from './service/compte-service.service';
import { ClientModificationComponent } from './client-modification/client-modification.component';


import { FormsModule } from '@angular/forms'; 
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AddClientComponent } from './add-client/add-client.component';
import { InfoComponent } from './info/info.component';
import { CompteBancaireComponent } from './compte-bancaire/compte-bancaire.component';
import { AddCompteComponent } from './add-compte/add-compte.component';
import {
  MatDialog,
  MatDialogRef,
  MatDialogTitle,
  MatDialogContent,
  MatDialogActions,
  MatDialogClose,
} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { CompteModificationComponent } from './compte-modification/compte-modification.component';


@NgModule({
  declarations: [
    AppComponent,
    ListClientComponent,
    ClientModificationComponent,
    AddClientComponent,
    InfoComponent,
    CompteBancaireComponent,
    AddCompteComponent,
    CompteModificationComponent,
    
    

  ],
  imports: [
    BrowserModule,
    FormsModule,
    NgbModule,
    BrowserAnimationsModule,
    MatListModule,
    MatSidenavModule,
    AppRoutingModule,
    MatSlideToggleModule,
    MatToolbarModule, 
    MatButtonModule, 
    MatIconModule,
    HttpClientModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    MatDialogClose,
    
  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync(),
    ClientServiceService,
    NgbActiveModal,
    CompteServiceService
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
