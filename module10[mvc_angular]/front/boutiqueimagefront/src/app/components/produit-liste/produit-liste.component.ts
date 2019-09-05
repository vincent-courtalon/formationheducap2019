import { Component, OnInit } from '@angular/core';
import { ProduitRepositoryService } from 'src/app/services/produit-repository.service';
import { Page } from 'src/app/metier/page';
import { Produit } from 'src/app/metier/produit';
import { Subscription } from 'rxjs';
import { faEdit, faDownload, faTrash } from '@fortawesome/free-solid-svg-icons';
import { ImageRepositoryService } from 'src/app/services/image-repository.service';
@Component({
  selector: 'app-produit-liste',
  templateUrl: './produit-liste.component.html',
  styleUrls: ['./produit-liste.component.css']
})
export class ProduitListeComponent implements OnInit {
  // icons
  faEdit = faEdit;
  faDownload = faDownload;
  faTrash = faTrash;

  // pagination
  noPage: number;
  taillePage: number;
  totalItems: number;

  constructor(private produitRepository: ProduitRepositoryService,
    private imageRepository: ImageRepositoryService) { }

  produits: Page<Produit>;
  toDelete: Produit;
  private produitSubscription: Subscription;

  ngOnInit() {
    this.toDelete = null;
    this.noPage = 1;
    this.taillePage = 8;
    this.totalItems = 0;
    this.produits = Page.emptyPage<Produit>();
    this.produitSubscription = this.produitRepository
      .getProduitsPageAsObservable()
      .subscribe(p => {
        this.produits = p;
        this.noPage = p.number + 1;
        this.taillePage = p.size;
        this.totalItems = p.totalElements;
      });
    this.produitRepository.refreshList();
  }

  public pageChanged(event) : void {
    this.produitRepository.setNoPage(event.page - 1);
  }

  public getThumbnailProduit(produit: Produit) {
    if (produit.images == null || produit.images.length == 0) {
      return "assets/images/No_picture_available.png";
    }
    else {
      return this.imageRepository.getThumbNailUrl(produit.images[0].id);
    }
  }
}
