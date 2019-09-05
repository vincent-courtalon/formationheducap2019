import { Injectable } from '@angular/core';
import { Categorie } from '../metier/categorie';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CategorieRepositoryService {

  private serviceUrl : string = "http://localhost:8080/categories"
  
  constructor(private http : HttpClient) { }


  public getListeCategorie() : Promise<Categorie[]> {
    return this.http.get<Categorie[]>(this.serviceUrl).toPromise();
  }
}
