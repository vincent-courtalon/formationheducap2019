import { TestBed } from '@angular/core/testing';

import { LivreRepositoryService } from './livre-repository.service';

describe('LivreRepositoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LivreRepositoryService = TestBed.get(LivreRepositoryService);
    expect(service).toBeTruthy();
  });
});
