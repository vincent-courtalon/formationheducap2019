import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AuteurEditComponent } from './auteur-edit.component';

describe('AuteurEditComponent', () => {
  let component: AuteurEditComponent;
  let fixture: ComponentFixture<AuteurEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AuteurEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuteurEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
