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
    this.taillePage = 8;
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
             .subscribe(p => this.picturesSubject.next(p),
                        err => this.picturesSubject.next(Page.emptyPage<Picture>()));
  }

  public findById(id: number) : Promise<Picture> {
    return this.http.get<Picture>(`${this.serviceUrl}/${id}`).toPromise();
  }

  public updatePicture(id: number, titre: string) : void {
    let urlParams : HttpParams = new HttpParams().set('titre', titre);
    // put d'angular me force a mattre un objet dans le corp de la requette
    // comme je n'en ai pas ici (juste un titre), je passe un objet vide
    this.http.put(`${this.serviceUrl}/${id}`, {}, {params: urlParams})
             .subscribe(r => this.refreshListe());
  }

  public deletePicture(id: number) : void {
    this.http.delete<any>(`${this.serviceUrl}/${id}`)
             .subscribe(r => this.refreshListe());
  }

  public setNopage(noPage : number) : void {
    this.noPage = noPage;
    this.refreshListe();
  }

  // genere l'url correcte pour afficher une image
  public getImageUrl(id : number) : string {
    return `${this.serviceUrl}/${id}/data`;
  }
  // genere l'url correcte pour afficher la miniature d'une image
  public getThumbnailUrl(id : number) : string {
      return `${this.serviceUrl}/${id}/thumbdata`;
  }
  // url pour l'upload
  public getUploadUrl() : string {
    return `${this.serviceUrl}/upload`;
  }
}
