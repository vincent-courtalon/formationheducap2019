import { Component, OnInit } from '@angular/core';
import { FileUploader } from 'ng2-file-upload';
import { PictureRepositoryService } from 'src/app/services/picture-repository.service';

@Component({
  selector: 'app-image-upload',
  templateUrl: './image-upload.component.html',
  styleUrls: ['./image-upload.component.css']
})
export class ImageUploadComponent implements OnInit {

  // objet qui gere l'upload de fichier en ajax
  public uploader: FileUploader;
  public hasBaseDropZoneOver : boolean = false;

  constructor(private pictureRepository: PictureRepositoryService) { 
    // objet responsable de l'upload
    // il va surveiller tous les ng2FileDrop du html
    // auquel il est lié
    this.uploader = new FileUploader({
      autoUpload : true,
      url: this.pictureRepository.getUploadUrl()
    });
    //this.uploader.queue
    // j'empeche l'uloader de mettre 'withCredential' dans
    // la requette ajax, car cela pose probleme avec cors
    this.uploader.onBeforeUploadItem = item => {
      item.withCredentials = false;
    };
  }

  ngOnInit() {
  }

  public fileOverDrop(event) {
    console.log("file over");
    console.log(event);
    this.hasBaseDropZoneOver = event;
  }
}
