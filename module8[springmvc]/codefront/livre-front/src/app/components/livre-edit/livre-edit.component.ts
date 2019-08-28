import { Component, OnInit } from '@angular/core';
import { LivreRepositoryService } from 'src/app/services/livre-repository.service';
import { AuteurRepositoryService } from 'src/app/services/auteur-repository.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Livre } from 'src/app/metier/livre';
import { Auteur } from 'src/app/metier/auteur';

@Component({
  selector: 'app-livre-edit',
  templateUrl: './livre-edit.component.html',
  styleUrls: ['./livre-edit.component.css']
})
export class LivreEditComponent implements OnInit {

  public editLivre: Livre;
  public livreAuteurId: number;
  public auteurs: Promise<Auteur[]>;

  constructor(private livreRepository: LivreRepositoryService,
    private auteurRepository: AuteurRepositoryService,
    private activeRoute: ActivatedRoute,
    private router: Router) {

  }

  ngOnInit() {
    this.editLivre = new Livre(0, "", 0, "", new Date());
    this.livreAuteurId = 0;
    this.activeRoute.params.subscribe(params => {
      let id: number = Number(params["id"]);
      if (id != 0) {
        this.livreRepository.getLivreById(id)
          .then(li => {
            this.editLivre = li;
            this.livreAuteurId = li.auteur.id;
            console.log('auteurId = ' + this.livreAuteurId);
          });
      }
    });
    // en parrallele de celle du livre
    this.auteurs = this.auteurRepository.getListeAuteur();
  }

  public sauverLivre(): void {
    this.livreRepository.save(this.editLivre, this.livreAuteurId)
      .then(li => {
        console.log(li);
        this.router.navigateByUrl("/livres");
      });
  }
}
