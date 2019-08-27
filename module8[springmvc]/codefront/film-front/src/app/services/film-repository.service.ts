import { Injectable } from '@angular/core';
import { Film } from '../metier/film';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Page } from '../metier/page';


@Injectable({
  providedIn: 'root'
})
export class FilmRepositoryService {
  private serviceUrl: string = "http://localhost:8080/films";



  private filmsSubject : BehaviorSubject<Page<Film>>;
  // informations de pagination
  private noPage: number;
  private taillePage: number;

  public setNoPage(noPage: number) : void {
    this.noPage = noPage;
    this.refreshListe();
  }

  private films: Film[];
  constructor(private http: HttpClient) {
    this.filmsSubject = new BehaviorSubject(Page.emptyPage<Film>());
    this.noPage = 0;
    this.taillePage = 5;
  }

  // cette fonction permet d'observer les sujet des pages de films
  public getPageFilmsAsObservable() : Observable<Page<Film>> {
    return this.filmsSubject.asObservable();
  }
  // quand je demande un rafraichissement de la liste
  // j'envoie une requette get, puis je republie la page recue dans le sujet des films
  public refreshListe() : void {
    let urlParams : HttpParams = new HttpParams();
    urlParams = urlParams.set("page", "" +this.noPage)
                         .set("size", "" + this.taillePage);

    this.http.get<Page<Film>>(this.serviceUrl, { params : urlParams })
             .toPromise()
             .then( p => this.filmsSubject.next(p));
  }

  public getListeFilms() : Promise<Film[]> {
    return this.http.get<Film[]>(this.serviceUrl).toPromise();
  }

  public getFilmById(id: number) : Promise<Film> {
    // le ` en typescript définit une chaine multiligne dans laquelle
    // on peut injecter des variable directement avec ${variable}
    // on appele ca l'interpolation de chaine
    return this.http.get<Film>(`${this.serviceUrl}/${id}`).toPromise();
  }

  public saveFilm(film : Film) : Promise<Film> {
    if (film.id !=  0) {
      return this.http
                  .put<Film>(this.serviceUrl, film)
                  .toPromise();
    }
    else {
      return this.http
                  .post<Film>(this.serviceUrl, film)
                  .toPromise();
    }
  }

  public deleteFilm(id : number) : Promise<any> {
    return this.http.delete<any>(`${this.serviceUrl}/${id}`).toPromise();
  }
}
