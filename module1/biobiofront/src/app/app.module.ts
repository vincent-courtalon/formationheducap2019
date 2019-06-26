import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
// nécéssaire pour les requetes ajax vers le serveur
import { HttpClientModule } from "@angular/common/http";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListeProduitsComponent } from './components/liste-produits/liste-produits.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    ListeProduitsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule    // a ajouter aussi pour pouvoir l'utiliser
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
