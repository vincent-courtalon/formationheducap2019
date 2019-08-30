import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Page } from '../metier/page';
import { Picture } from '../metier/picture';

@Injectable({
  providedIn: 'root'
})
export class PictureRepositoryService {
  private serviceUrl : string = 'http://localhost:8080/pictures';
  // pagination
  private noPage: number;
  private taillePage: number;

  private picturesSubject : BehaviorSubject<Page<Picture>>;

  // injection du requetteur
  constructor(private http : HttpClient) {
    this.noPage = 0;
    this.taillePage = 6;
    this.picturesSubject = new BehaviorSubject(Page.emptyPage<Picture>());

  }

  public getPicturesAsObservable() : Observable<Page<Picture>> {
    return this.picturesSubject.asObservable();
  }

  public refreshListe() :void  {
    let urlParams : HttpParams = new HttpParams().set('page', "" + this.noPage)
                                                 .set('size', "" + this.taillePage);
    // j'envoie la requete ajax
    // quand j'ai la reponse, je republie dans picturesSubject
    this.http.get<Page<Picture>>(this.serviceUrl, {params: urlParams})
             .subscribe(p => this.picturesSubject.next(p));
  }

  public setNopage(noPage : number) : void {
    this.noPage = noPage;
    this.refreshListe();
  }

  // genere l'url correcte pour afficher une image
  public getImageUrl(id : number) : string {
    return `${this.serviceUrl}/${id}/content`;
  }
  // url pour l'upload
  public getUploadUrl() : string {
    return this.serviceUrl;
  }
}
