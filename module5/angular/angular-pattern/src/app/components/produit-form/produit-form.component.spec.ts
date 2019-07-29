import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProduitFormComponent } from './produit-form.component';

describe('ProduitFormComponent', () => {
  let component: ProduitFormComponent;
  let fixture: ComponentFixture<ProduitFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProduitFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProduitFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
