import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PictureRepositoryService } from 'src/app/services/picture-repository.service';
import { Picture } from 'src/app/metier/picture';

@Component({
  selector: 'app-image-detail',
  templateUrl: './image-detail.component.html',
  styleUrls: ['./image-detail.component.css']
})
export class ImageDetailComponent implements OnInit {
  // l'image en cours d'edition
  editPicture : Picture;

  constructor(private activeRoute : ActivatedRoute,
              private router: Router,
              private pictureRepository : PictureRepositoryService) { }

  ngOnInit() {
    this.editPicture = null;
    this.activeRoute.params.subscribe(params => {
      let id : number = Number(params['id']);
      this.pictureRepository.findById(id).then(p => {
        this.editPicture = p;
      });
    });
  }

  public savePicture() : void {
    this.pictureRepository.updatePicture(this.editPicture.id, this.editPicture.titre);
    this.router.navigateByUrl("/");
  }
  
  public getImageUrl(id: number) : string {
    return this.pictureRepository.getImageUrl(id);
  }
}
