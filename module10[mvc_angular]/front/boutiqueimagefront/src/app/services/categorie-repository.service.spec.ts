import { TestBed } from '@angular/core/testing';

import { CategorieRepositoryService } from './categorie-repository.service';

describe('CategorieRepositoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CategorieRepositoryService = TestBed.get(CategorieRepositoryService);
    expect(service).toBeTruthy();
  });
});
