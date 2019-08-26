import { TestBed } from '@angular/core/testing';

import { FilmRepositoryService } from './film-repository.service';

describe('FilmRepositoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FilmRepositoryService = TestBed.get(FilmRepositoryService);
    expect(service).toBeTruthy();
  });
});
