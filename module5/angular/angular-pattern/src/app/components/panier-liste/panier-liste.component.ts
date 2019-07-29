import { Component, OnInit } from '@angular/core';
import { PanierService } from 'src/app/services/panier.service';
import { Produit } from 'src/app/metier/Produit';

@Component({
  selector: 'app-panier-liste',
  templateUrl: './panier-liste.component.html',
  styleUrls: ['./panier-liste.component.css']
})
export class PanierListeComponent implements OnInit {
  public produitsListe : Produit[];

  constructor(private panierService: PanierService) { }

  ngOnInit() {
    this.produitsListe = [];

    // ce composant souscris à l'observable/sujet du panier
    // le petit morceau de code (lambda) sera rappelé
    // a chaque nouvelle donnée publiée dans le panier
    this.panierService
        .getProduitsAsObservable()
        .subscribe((data) => {this.produitsListe = data;});
  }

}
