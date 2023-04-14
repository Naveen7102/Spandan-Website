import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateOrJointTeamComponent } from './create-or-joint-team.component';

describe('CreateOrJointTeamComponent', () => {
  let component: CreateOrJointTeamComponent;
  let fixture: ComponentFixture<CreateOrJointTeamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateOrJointTeamComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateOrJointTeamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
