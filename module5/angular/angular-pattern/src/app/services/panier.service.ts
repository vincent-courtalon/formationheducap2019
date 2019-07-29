import { Injectable } from '@angular/core';
import { Produit } from '../metier/Produit';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PanierService {
  private produits : Produit[];
  // le sujet a observer
  // c'est à travers lui que je préviendrais les observer
  private produitsSubject: BehaviorSubject<Produit[]>;

  constructor() { 
    this.produits = [];
    // on initialise notre sujet, pour commencer
    // avec un panier vide (tableau vide)
    this.produitsSubject = new BehaviorSubject(this.produits);
  }

  public getProduitsAsObservable() : Observable<Produit[]> {
    // un observable permet à un observateur de s'abonner
    // pour etre prévenu des modifications/nouvelles données
    // l'avantage de l'observable, c'est qu'il ne permet que
    // de s'abonner, et aucune autre operations
    return this.produitsSubject.asObservable();
  }

  public ajouterProduit(produit : Produit) : void {
    // j'ajoute le nouveau produit au panier
    this.produits.push(produit);
    // je préviens ceux qui sont abonné à mon produitsSubject
    this.produitsSubject.next(this.produits);
  }

  public viderPanier() : void {
    this.produits = [];
    this.produitsSubject.next(this.produits);
  }
}
