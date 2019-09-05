import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Page } from '../metier/page';
import { Produit } from '../metier/produit';

@Injectable({
  providedIn: 'root'
})
export class ProduitRepositoryService {


  private serviceUrl: string = "http://localhost:8080/produits";
  // pagination
  private noPage: number;
  private taillePage: number;

  private produitsSubject : BehaviorSubject<Page<Produit>>;

  constructor(private http: HttpClient) {
    this.noPage = 0;
    this.taillePage = 8;
    this.produitsSubject = new BehaviorSubject(Page.emptyPage<Produit>());
   }
  public getProduitsPageAsObservable() : Observable<Page<Produit>> {
    return this.produitsSubject.asObservable();
  }
  public setNoPage(noPage : number) : void {
    this.noPage = noPage;
    this.refreshList();
  }

  public refreshList() {
    let urlParams : HttpParams = 
            new HttpParams().set('page' , "" + this.noPage)
                            .set('size', "" + this.taillePage);
    this.http.get<Page<Produit>>(this.serviceUrl, {params: urlParams})
             .subscribe(p => this.produitsSubject.next(p));
  }

  public getProduitById(id: number) : Promise<Produit> {
    return this.http.get<Produit>(`${this.serviceUrl}/${id}`)
                    .toPromise();
  }

  public saveProduit(produit :Produit, categorieId: number) : Promise<Produit> {
    let urlParams :HttpParams = new HttpParams().set("categorieId", "" + categorieId);
    if (produit.id == 0)
      return this.http.post<Produit>(this.serviceUrl, produit, {params: urlParams})
                 .toPromise();
    else
      return this.http.put<Produit>(this.serviceUrl, produit, {params: urlParams})
                 .toPromise();
  }

  deleteProduit(id: number) : void {
    this.http.delete<any>(`${this.serviceUrl}/${id}`)
             .toPromise().then(r => this.refreshList());
  }

}
