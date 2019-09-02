import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PaginationModule } from 'ngx-bootstrap/pagination';
import { FileUploadModule } from "ng2-file-upload";
import { NavBarComponent } from "./components/nav-bar/nav-bar.component";
import { ImageListeComponent } from './components/image-liste/image-liste.component';
import { ImageUploadComponent } from './components/image-upload/image-upload.component';
import { ImageDetailComponent } from './components/image-detail/image-detail.component';

import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from '@angular/forms';
import { ProgressbarModule } from 'ngx-bootstrap/progressbar';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    ImageListeComponent,
    ImageUploadComponent,
    ImageDetailComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    PaginationModule.forRoot(),
    FileUploadModule,
    HttpClientModule,
    FormsModule,
    ProgressbarModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
