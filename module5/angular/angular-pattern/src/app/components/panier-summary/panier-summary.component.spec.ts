import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PanierSummaryComponent } from './panier-summary.component';

describe('PanierSummaryComponent', () => {
  let component: PanierSummaryComponent;
  let fixture: ComponentFixture<PanierSummaryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PanierSummaryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PanierSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
