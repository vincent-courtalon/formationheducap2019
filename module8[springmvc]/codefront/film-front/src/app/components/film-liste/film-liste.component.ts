import { Component, OnInit, OnDestroy } from '@angular/core';
import { FilmRepositoryService } from 'src/app/services/film-repository.service';
import { Film } from 'src/app/metier/film';
import { Page } from 'src/app/metier/page';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-film-liste',
  templateUrl: './film-liste.component.html',
  styleUrls: ['./film-liste.component.css']
})
export class FilmListeComponent implements OnInit, OnDestroy {


  // pagination
  public totalItems: number;
  public currentPage: number;
  public taillePage: number;
  // les films
  films: Page<Film>;
  // la souscription au films de Filmrepository
  // nous permettra de nous "déenregistrer" comme observateur des films
  // quand le composant listeFilm sera détruit
  filmSouscription : Subscription;

   // quand vous ajoutez un argument au constructeur d'un component
  // angular considere que c'est une dépendace à vous injecter
  constructor(private filmRepository: FilmRepositoryService) {
    this.totalItems=30;
    this.currentPage=1;
    this.taillePage=5;
  }
  
  ngOnInit(): void {
      this.films = Page.emptyPage<Film>();
      this.filmSouscription = this.filmRepository
                                  .getPageFilmsAsObservable()
                                  .subscribe( p => {
                                    this.films = p;
                                    this.totalItems = p.totalElements;
                                    this.taillePage = p.size;
                                    this.currentPage = p.number + 1;
                                  });
      this.filmRepository.refreshListe();
  }
  ngOnDestroy(): void {
    this.filmSouscription.unsubscribe();
  }


  public effacerFilm(id : number) : void {
    console.log("effacement de film no " + id + " demandé");
    this.filmRepository.deleteFilm(id).then(
      data => this.filmRepository.refreshListe());
  }


  public pageChanged(event: any) {
    console.log(event);
    this.filmRepository.setNoPage(event.page - 1);
  }
}
