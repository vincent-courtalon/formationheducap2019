import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PanierListeComponent } from './panier-liste.component';

describe('PanierListeComponent', () => {
  let component: PanierListeComponent;
  let fixture: ComponentFixture<PanierListeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PanierListeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PanierListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
