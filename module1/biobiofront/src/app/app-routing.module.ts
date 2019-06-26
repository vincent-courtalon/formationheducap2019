import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListeProduitsComponent } from './components/liste-produits/liste-produits.component';

const routes: Routes = [
  { path: 'liste', component: ListeProduitsComponent},
  { path: '', redirectTo: '/liste', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
