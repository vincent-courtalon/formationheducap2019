import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PanierCompteurComponent } from './panier-compteur.component';

describe('PanierCompteurComponent', () => {
  let component: PanierCompteurComponent;
  let fixture: ComponentFixture<PanierCompteurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PanierCompteurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PanierCompteurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
