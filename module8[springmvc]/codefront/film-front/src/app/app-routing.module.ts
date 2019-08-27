import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FilmListeComponent } from './components/film-liste/film-liste.component';
import { FilmEditComponent } from './components/film-edit/film-edit.component';


const routes: Routes = [
  { path: 'liste', component: FilmListeComponent},
  { path: 'edit/:id', component: FilmEditComponent},
  { path: '', redirectTo: '/liste', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
