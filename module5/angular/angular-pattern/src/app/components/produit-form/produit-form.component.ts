import { Component, OnInit } from '@angular/core';
import { PanierService } from 'src/app/services/panier.service';
import { Produit } from 'src/app/metier/Produit';

@Component({
  selector: 'app-produit-form',
  templateUrl: './produit-form.component.html',
  styleUrls: ['./produit-form.component.css']
})
export class ProduitFormComponent implements OnInit {
  public id : number;
  public nom: string;
  public prix: number;


  constructor(private panierService: PanierService) { }

  ngOnInit() {
    this.id = 0;
    this.nom = "";
    this.prix = 0.0;
    
  }

  public clickProduit() {
    console.log("nom = " + this.nom + " id= " + this.id + " prix " + this.prix);
    // je publie l'ajout d'un nouveau produit dans le panier
    this.panierService.ajouterProduit(new Produit(this.id, this.nom, this.prix));
  }
}
