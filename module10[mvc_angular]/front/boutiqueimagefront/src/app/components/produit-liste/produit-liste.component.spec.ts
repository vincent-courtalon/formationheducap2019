import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProduitListeComponent } from './produit-liste.component';

describe('ProduitListeComponent', () => {
  let component: ProduitListeComponent;
  let fixture: ComponentFixture<ProduitListeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProduitListeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProduitListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
