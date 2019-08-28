import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Auteur } from '../metier/auteur';

@Injectable({
  providedIn: 'root'
})
export class AuteurRepositoryService {
  private serviceUrl : string;

  constructor(private http: HttpClient) {
    this.serviceUrl="http://localhost:8080/auteurs";
   }
  
  public getListeAuteur() : Promise<Auteur[]> {
    return this.http.get<Auteur[]>(this.serviceUrl).toPromise();
  }

}
