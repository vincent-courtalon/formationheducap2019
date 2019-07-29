import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProduitFormComponent } from './components/produit-form/produit-form.component';


const routes: Routes = [
  {path: "home", component: ProduitFormComponent},
  {path: "", pathMatch: "full", component: ProduitFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
