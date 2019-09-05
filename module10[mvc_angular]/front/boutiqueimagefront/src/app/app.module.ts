import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PaginationModule } from 'ngx-bootstrap/pagination';
import { ModalModule } from "ngx-bootstrap/modal";
import { ProgressbarModule } from "ngx-bootstrap/progressbar";
import { ProduitListeComponent } from './components/produit-liste/produit-liste.component';
import { ProduitEditComponent } from './components/produit-edit/produit-edit.component';
import { NavBarComponent } from "./components/nav-bar/nav-bar.component";
import { FileUploadModule } from "ng2-file-upload";
import { NgStringPipesModule, NgMathPipesModule } from "angular-pipes";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [
    AppComponent,
    ProduitListeComponent,
    ProduitEditComponent,
    NavBarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    PaginationModule.forRoot(),
    ModalModule.forRoot(),
    ProgressbarModule.forRoot(),
    HttpClientModule,
    FormsModule,
    FileUploadModule,
    NgStringPipesModule,
    NgMathPipesModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
