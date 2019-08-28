import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LivreListeComponent } from './components/livre-liste/livre-liste.component';
import { AuteurListeComponent } from './components/auteur-liste/auteur-liste.component';
import { LivreEditComponent } from './components/livre-edit/livre-edit.component';
import { AuteurEditComponent } from './components/auteur-edit/auteur-edit.component';


const routes: Routes = [
  {path: 'livres' , component: LivreListeComponent},
  {path: 'auteurs' , component: AuteurListeComponent},
  {path: 'livres/edit/:id', component: LivreEditComponent},
  {path: 'auteurs/edit/:id', component: AuteurEditComponent},
  {path: '', redirectTo: '/livres', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
