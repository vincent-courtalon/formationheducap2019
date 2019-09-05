import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProduitRepositoryService } from 'src/app/services/produit-repository.service';
import { CategorieRepositoryService } from 'src/app/services/categorie-repository.service';
import { FileUploader } from 'ng2-file-upload';
import { Produit } from 'src/app/metier/produit';
import { Categorie } from 'src/app/metier/categorie';
import { ImageRepositoryService } from 'src/app/services/image-repository.service';

@Component({
  selector: 'app-produit-edit',
  templateUrl: './produit-edit.component.html',
  styleUrls: ['./produit-edit.component.css']
})
export class ProduitEditComponent implements OnInit {

  constructor(private activeRoute : ActivatedRoute,
              private router: Router,
              private produitRepository: ProduitRepositoryService,
              private categorieRepository: CategorieRepositoryService,
              private imageRepository: ImageRepositoryService) { }

  uploader : FileUploader;
  editProduit : Produit;
  categorieId : number = 0;
  categories : Promise<Categorie[]>;
  uploadActive : boolean = false;
  hasBaseDropZoneOver : boolean = false;

  ngOnInit() {
    this.editProduit = null;
    this.activeRoute.params.subscribe(params => {
      let id : number = Number(params["id"]);
      if( id == 0) {
        this.uploadActive = false;
        //creation
        this.editProduit = new Produit(0, "", 0, 0, null, []);
        this.categorieId = 0;
      } 
      else {
        this.produitRepository.getProduitById(id)
                              .then( p => {
                                this.editProduit = p;
                                this.categorieId = p.categorie.id;
                                this.initUploader();
                                this.uploadActive = true;
                              });
      }
    });
    this.categories= this.categorieRepository.getListeCategorie();
  }

  private initUploader() : void {
    this.uploader = new FileUploader({
      url: this.imageRepository.getUploadUrl(),
      autoUpload: true,
      additionalParameter: {"produitId": this.editProduit.id}
    });
    this.uploader.onBeforeUploadItem = item => {
      item.withCredentials = false;
    };
  }

  public saveProduit() {
    this.produitRepository.saveProduit(this.editProduit, this.categorieId)
                          .then( p => {
                            console.log(p);
                            this.router.navigateByUrl("");
                          })
  }
  public fileOverDrop(event) {
      this.hasBaseDropZoneOver = event;
  }
}
