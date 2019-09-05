import { TestBed } from '@angular/core/testing';

import { ImageRepositoryService } from './image-repository.service';

describe('ImageRepositoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ImageRepositoryService = TestBed.get(ImageRepositoryService);
    expect(service).toBeTruthy();
  });
});
