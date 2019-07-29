import { Component, OnInit } from '@angular/core';
import { PanierService } from 'src/app/services/panier.service';
import { Produit } from 'src/app/metier/Produit';

@Component({
  selector: 'app-panier-summary',
  templateUrl: './panier-summary.component.html',
  styleUrls: ['./panier-summary.component.css']
})
export class PanierSummaryComponent implements OnInit {
  public totalPanier : number;

  constructor(private panierService : PanierService) { }

  ngOnInit() {
    this.totalPanier = 0;
    this.panierService
        .getProduitsAsObservable()
        .subscribe((data) => {
          this.totalPanier = 0;
          for( let p of data) {
            this.totalPanier += Number(p.prix);
          }
        });
  }

}
