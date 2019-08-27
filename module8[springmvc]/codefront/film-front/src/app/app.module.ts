import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FilmRepositoryService } from './services/film-repository.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from "@angular/common/http";
import { FilmListeComponent } from './components/film-liste/film-liste.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { FilmEditComponent } from './components/film-edit/film-edit.component';
import { FormsModule } from "@angular/forms";
import { PaginationModule } from 'ngx-bootstrap/pagination';

@NgModule({
  declarations: [
    AppComponent,
    FilmListeComponent,
    NavBarComponent,
    FilmEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    PaginationModule.forRoot()
  ],
  providers: [
    FilmRepositoryService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
