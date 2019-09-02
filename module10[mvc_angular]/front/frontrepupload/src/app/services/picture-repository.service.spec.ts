import { TestBed } from '@angular/core/testing';

import { PictureRepositoryService } from './picture-repository.service';

describe('PictureRepositoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PictureRepositoryService = TestBed.get(PictureRepositoryService);
    expect(service).toBeTruthy();
  });
});
