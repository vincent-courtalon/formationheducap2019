import { Component, OnInit } from '@angular/core';
import { FilmRepositoryService } from 'src/app/services/film-repository.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Film } from 'src/app/metier/film';

@Component({
  selector: 'app-film-edit',
  templateUrl: './film-edit.component.html',
  styleUrls: ['./film-edit.component.css']
})
export class FilmEditComponent implements OnInit {

  public editFilm: Film;

  constructor(private filmRepository: FilmRepositoryService,
    private activeRoute: ActivatedRoute,
    private router: Router) {

  }

  ngOnInit() {
    this.editFilm = new Film(0, "", 0, new Date());
    this.activeRoute.params.subscribe(params => {
      let id = Number(params['id']);
      console.log("recu id film -> " + id);
      if (id != 0) {
        this.filmRepository.getFilmById(id)
          .then(f => this.editFilm = f);
      }
    });
  }

  public sauverFilm(): void {
    console.log("sauvegarde de ");
    console.log(this.editFilm);

    // appeler le filmRepository pour la sauvegarde
    this.filmRepository.saveFilm(this.editFilm)
      .then(f => {
        console.log("sauvegarde ok");
        console.log(f);
        this.router.navigateByUrl('/liste');
      });
  }
}
