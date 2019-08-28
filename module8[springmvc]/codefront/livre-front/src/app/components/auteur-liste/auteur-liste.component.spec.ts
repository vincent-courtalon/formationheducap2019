import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AuteurListeComponent } from './auteur-liste.component';

describe('AuteurListeComponent', () => {
  let component: AuteurListeComponent;
  let fixture: ComponentFixture<AuteurListeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AuteurListeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuteurListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
