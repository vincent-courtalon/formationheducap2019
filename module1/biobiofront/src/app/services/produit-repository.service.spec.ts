import { TestBed } from '@angular/core/testing';

import { ProduitRepositoryService } from './produit-repository.service';

describe('ProduitRepositoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProduitRepositoryService = TestBed.get(ProduitRepositoryService);
    expect(service).toBeTruthy();
  });
});
