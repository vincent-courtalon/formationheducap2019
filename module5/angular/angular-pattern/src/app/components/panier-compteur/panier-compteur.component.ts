import { Component, OnInit } from '@angular/core';
import { PanierService } from 'src/app/services/panier.service';

@Component({
  selector: 'app-panier-compteur',
  templateUrl: './panier-compteur.component.html',
  styleUrls: ['./panier-compteur.component.css']
})
export class PanierCompteurComponent implements OnInit {
  public compteurPanier: number;

  constructor(private panierService: PanierService) { }

  ngOnInit() {
    this.compteurPanier = 0;
    this.panierService
        .getProduitsAsObservable()
        .subscribe((data) => this.compteurPanier = data.length);
  }

}
