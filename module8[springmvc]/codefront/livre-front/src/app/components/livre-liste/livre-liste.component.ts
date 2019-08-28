import { Component, OnInit, OnDestroy } from '@angular/core';
import { LivreRepositoryService } from 'src/app/services/livre-repository.service';
import { Page } from 'src/app/metier/page';
import { Livre } from 'src/app/metier/livre';
import { Subscription, Subject } from 'rxjs';
import { debounceTime } from "rxjs/operators";

@Component({
  selector: 'app-livre-liste',
  templateUrl: './livre-liste.component.html',
  styleUrls: ['./livre-liste.component.css']
})
export class LivreListeComponent implements OnInit, OnDestroy {

  public livres : Page<Livre>;
  public noPage : number;
  public taillePage: number;
  public totalCount: number;
  private searchSubject: Subject<string>;

  private livresSouscription : Subscription;

  constructor(private livreRepository: LivreRepositoryService) { }

  ngOnInit() {
    this.livres = Page.emptyPage<Livre>();
    this.noPage = 1;
    this.taillePage = 5;
    this.totalCount = 0;
    this.livresSouscription = this.livreRepository.getLivresSubjectAsObservable()
                                                  .subscribe( p => {
                                                    this.livres = p;
                                                    this.taillePage = p.size;
                                                    this.noPage = p.number + 1;
                                                    this.totalCount = p.totalElements;
                                                  });
    this.livreRepository.refreshListe();
    this.recherche="";
    this.searchSubject = new Subject();
    this.searchSubject.asObservable()
                      .pipe(debounceTime(500))
                      .subscribe( term => this.livreRepository.setSearchTerm(term));

  }

  ngOnDestroy(): void {
    this.livresSouscription.unsubscribe();
  }
  public pageChanged(event) {
    this.livreRepository.setNoPage(event.page - 1);
  }
  // ----------  recherche -----------
  public recherche : string;

  public searchTitre(event) {
    this.recherche = event;
   // console.log(event);
    this.searchSubject.next(event);
  }
}
