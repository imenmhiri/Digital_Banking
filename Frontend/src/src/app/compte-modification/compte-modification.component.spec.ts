import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompteModificationComponent } from './compte-modification.component';

describe('CompteModificationComponent', () => {
  let component: CompteModificationComponent;
  let fixture: ComponentFixture<CompteModificationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CompteModificationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CompteModificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
