import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LivreListeComponent } from './livre-liste.component';

describe('LivreListeComponent', () => {
  let component: LivreListeComponent;
  let fixture: ComponentFixture<LivreListeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LivreListeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LivreListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
