import { Component, OnInit, TemplateRef } from '@angular/core';
import { PictureRepositoryService } from 'src/app/services/picture-repository.service';
import { Page } from 'src/app/metier/page';
import { Picture } from 'src/app/metier/picture';
import { Subscription } from 'rxjs';
import { faEdit, faDownload, faTrash } from '@fortawesome/free-solid-svg-icons';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-image-liste',
  templateUrl: './image-liste.component.html',
  styleUrls: ['./image-liste.component.css']
})
export class ImageListeComponent implements OnInit {
  // icons
  faEdit = faEdit;
  faDownload = faDownload;
  faTrash = faTrash;

  pictures : Page<Picture>;
  // pagination
  noPage : number;
  taillePage: number;
  totalItems: number;
  private pictureSubscription: Subscription;

  // modal
  modalref : BsModalRef; // reference du popup une fois ouvert
  toDelete : Picture; // l'image a supprimer si popup confirmé

  constructor(private pictureRepository : PictureRepositoryService,
              private modalService: BsModalService) { 
  }

  ngOnInit() {
    this.toDelete = null;
    this.noPage = 1;
    this.taillePage = 6;
    this.totalItems = 0;
    this.pictures = Page.emptyPage<Picture>();
    this.pictureSubscription = this.pictureRepository.getPicturesAsObservable()
                                   .subscribe(p => {
                                      this.noPage = p.number + 1;
                                      this.taillePage = p.size;
                                      this.totalItems = p.totalElements;
                                      this.pictures = p;
                                   });
    // requetage initial des images
    this.pictureRepository.refreshListe();
  }

  public pageChanged(event) : void {
    this.pictureRepository.setNopage(event.page - 1);
  }

  public getImageUrl(id: number) : string {
    return this.pictureRepository.getImageUrl(id);
  }
  public getThumbnailUrl(id: number) : string {
    return this.pictureRepository.getThumbnailUrl(id);
  }

  public openDeleteModal(template : TemplateRef<any>, p : Picture) {
    this.toDelete = p; // selection de l'image a effacer
    // affichage du modal
    this.modalref = this.modalService.show(template, {class: 'modal-sm'});
  }

  public confirmDelete() {
    console.log("effacement de " + this.toDelete.titre);
    this.modalref.hide();
    this.pictureRepository.deletePicture(this.toDelete.id);
    this.toDelete = null;
  }

  public declineDelete() {
    console.log("annulation effacement de " + this.toDelete.titre);
    this.modalref.hide();
    this.toDelete = null;
  }

}
