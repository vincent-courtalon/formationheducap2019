import { Component, OnInit } from '@angular/core';
import { PictureRepositoryService } from 'src/app/services/picture-repository.service';
import { Page } from 'src/app/metier/page';
import { Picture } from 'src/app/metier/picture';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-image-liste',
  templateUrl: './image-liste.component.html',
  styleUrls: ['./image-liste.component.css']
})
export class ImageListeComponent implements OnInit {
  pictures : Page<Picture>;
  // pagination
  noPage : number;
  taillePage: number;
  totalItems: number;
  private pictureSubscription: Subscription;

  constructor(private pictureRepository : PictureRepositoryService) { 
  }

  ngOnInit() {
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
}
