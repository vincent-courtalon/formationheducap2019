import { Component, OnInit } from '@angular/core';
import { Produit } from 'src/app/metier/produit';
import { ProduitRepositoryService } from 'src/app/services/produit-repository.service';

@Component({
  selector: 'app-liste-produits',
  templateUrl: './liste-produits.component.html',
  styleUrls: ['./liste-produits.component.css']
})
export class ListeProduitsComponent implements OnInit {

  public produits : Produit[];
  
  constructor(private produitRepo: ProduitRepositoryService) { 
    this.produits = [];
   // this.produits.push(new Produit("steak de lama", 39.99, "steak des andes"));
   // this.produits.push(new Produit("biere oceania", 7.99, "biere aux algues"));
  }

  ngOnInit() {
    this.produitRepo.getAllProduits()
        .then(data => {
          // code executé quand les données sont recue du serveur
          console.log(data);
          this.produits = [];
          data._embedded.produits.forEach(p => {
            this.produits.push(p);
          });
        });
  }

}
