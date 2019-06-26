import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class ProduitRepositoryService {

  constructor(private http: HttpClient) { }

  public getAllProduits() : Promise<any> {
    // executer la requete vers le serveur
    // et renvoyer la reponse a l'appelant
    return this.http.get<any>('http://localhost:8080/api/produits')
              .toPromise();
  }
}
