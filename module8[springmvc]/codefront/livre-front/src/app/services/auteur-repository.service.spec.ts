import { TestBed } from '@angular/core/testing';

import { AuteurRepositoryService } from './auteur-repository.service';

describe('AuteurRepositoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuteurRepositoryService = TestBed.get(AuteurRepositoryService);
    expect(service).toBeTruthy();
  });
});
