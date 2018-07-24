import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardTrashComponent } from './dashboard-trash.component';

describe('DashboardTrashComponent', () => {
  let component: DashboardTrashComponent;
  let fixture: ComponentFixture<DashboardTrashComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DashboardTrashComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardTrashComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
