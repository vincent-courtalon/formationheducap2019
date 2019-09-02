import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageListeComponent } from './image-liste.component';

describe('ImageListeComponent', () => {
  let component: ImageListeComponent;
  let fixture: ComponentFixture<ImageListeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImageListeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImageListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
