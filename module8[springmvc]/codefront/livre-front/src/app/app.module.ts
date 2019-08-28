import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PaginationModule } from 'ngx-bootstrap/pagination';
import { LivreListeComponent } from './components/livre-liste/livre-liste.component';
import { LivreEditComponent } from './components/livre-edit/livre-edit.component';
import { AuteurListeComponent } from './components/auteur-liste/auteur-liste.component';
import { AuteurEditComponent } from './components/auteur-edit/auteur-edit.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
@NgModule({
  declarations: [
    AppComponent,
    LivreListeComponent,
    LivreEditComponent,
    AuteurListeComponent,
    AuteurEditComponent,
    NavBarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    PaginationModule.forRoot(),
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
