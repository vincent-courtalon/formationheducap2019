import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ImageListeComponent } from './components/image-liste/image-liste.component';
import { ImageDetailComponent } from './components/image-detail/image-detail.component';
import { ImageUploadComponent } from './components/image-upload/image-upload.component';


const routes: Routes = [
  {path: 'images', component: ImageListeComponent},
  {path: 'details/:id', component: ImageDetailComponent},
  {path: 'upload', component: ImageUploadComponent},
  {path: '', redirectTo: 'images', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
