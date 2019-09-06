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

import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { FormsModule } from '@angular/forms';
import { ProgressbarModule } from 'ngx-bootstrap/progressbar';
import { NgStringPipesModule } from "angular-pipes";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ModalModule } from 'ngx-bootstrap/modal';
import { LoginComponent } from './components/login/login.component';
import { AuthInterceptorService } from './services/auth-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    ImageListeComponent,
    ImageUploadComponent,
    ImageDetailComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    PaginationModule.forRoot(),
    FileUploadModule,
    HttpClientModule,
    FormsModule,
    ProgressbarModule.forRoot(),
    NgStringPipesModule,
    FontAwesomeModule,
    ModalModule.forRoot()
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
