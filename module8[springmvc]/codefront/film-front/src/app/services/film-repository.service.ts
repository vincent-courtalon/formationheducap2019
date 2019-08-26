import { Injectable } from '@angular/core';
import { Film } from '../metier/film';

@Injectable({
  providedIn: 'root'
})
export class FilmRepositoryService {
  private films: Film[];
  constructor() {
    this.films = [
      new Film(1,
        "l'atttaque des courgettes tueuses",
        115,
        new Date()),
      new Film(2,
        "les Titans a la plage",
        135,
        new Date()),
      new Film(3,
        "batman contre les courgettes tueuses",
        145,
        new Date())
    ];
  }

  public getListeFilms() : Film[] {
    return this.films;
  }
}
