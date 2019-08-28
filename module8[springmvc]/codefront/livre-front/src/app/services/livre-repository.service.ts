import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Page } from '../metier/page';
import { Livre } from '../metier/livre';

@Injectable({
  providedIn: 'root'
})
export class LivreRepositoryService {

  private serviceUrl : string;
  // pagination
  private noPage: number;
  private taillePage: number;
  //recherche
  private searchTerm : string;


  // le canal de communication qui va etre "observé" par les components
  // qui veulent afficher les livres
  private livresSubject : BehaviorSubject<Page<Livre>>;

  constructor(private http: HttpClient) { 
    this.livresSubject = new BehaviorSubject(Page.emptyPage<Livre>());
    this.noPage = 0;
    this.taillePage = 5;
    this.serviceUrl = "http://localhost:8080/livres";
  }

  public getLivresSubjectAsObservable() : Observable<Page<Livre>> {
    return this.livresSubject.asObservable();
  }

  public refreshListe() : void {
    let urlParams : HttpParams = new HttpParams().set('page', "" + this.noPage)
                                                 .set('size', "" + this.taillePage);
    // si searchTerm non vide, l'ajouter a la requette 
    if (this.searchTerm != null && this.searchTerm.length > 0) {
      urlParams = urlParams.set('searchTerm' , this.searchTerm);
    }                                             
    this.http.get<Page<Livre>>(this.serviceUrl, {params: urlParams})
             .toPromise()
             .then(p => this.livresSubject.next(p));                                                 
  }

  public setNoPage(noPage : number) : void {
    this.noPage = noPage;
    this.refreshListe();
  }
  public setSearchTerm(searchTerm : string) : void {
    this.searchTerm = searchTerm;
    this.refreshListe();
  }
  
  public getLivreById(id : number) : Promise<Livre> {
    return this.http.get<Livre>(`${this.serviceUrl}/${id}`).toPromise();
  }

  public save(livre: Livre, auteurId : number) : Promise<Livre> {
    let urlParams : HttpParams = new HttpParams().set("auteurId", "" + auteurId);
    if (livre.id == 0)
      return this.http.post<Livre>(this.serviceUrl, livre, {params: urlParams})
                      .toPromise();
    else
      return this.http.put<Livre>(this.serviceUrl, livre, {params: urlParams})
                      .toPromise();
  }

  public delete(id : number) : Promise<any> {
    return this.http.delete<any>(`${this.serviceUrl}/${id}`).toPromise();
  }

}
