import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageRepositoryService {

  private serviceUrl : string = "http://localhost:8080/images";
  constructor() { }

  public getUploadUrl() : string {
    return `${this.serviceUrl}/upload`;
  }
  public getThumbNailUrl(id: number) {
    return `${this.serviceUrl}/${id}/thumbdata`;
  }
  public getImageUrl(id: number) {
    return `${this.serviceUrl}/${id}/data`;
  }

}
