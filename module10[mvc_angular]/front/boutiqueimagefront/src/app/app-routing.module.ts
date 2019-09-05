import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProduitListeComponent } from './components/produit-liste/produit-liste.component';
import { ProduitEditComponent } from './components/produit-edit/produit-edit.component';


const routes: Routes = [
  { path: 'produits', component: ProduitListeComponent},
  { path: 'edit/:id', component: ProduitEditComponent},
  { path: '' , redirectTo: 'produits', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
