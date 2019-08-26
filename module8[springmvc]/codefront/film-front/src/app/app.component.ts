import { Component, OnInit } from '@angular/core';
import { Film } from './metier/film';
import { FilmRepositoryService } from './services/film-repository.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  // quand vous ajoutez un argument au constructeur d'un component
  // angular considere que c'est une dépendace à vous injecter
  constructor(private filmRepository: FilmRepositoryService) {

  }

  title = 'application film-front';

  films: Film[] = [];

  ngOnInit(): void {
      this.films = this.filmRepository.getListeFilms();
  }
}
