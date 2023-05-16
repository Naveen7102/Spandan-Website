import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { CreateOrJointTeamComponent } from './create-or-joint-team.component';
import { DataexchangeService } from 'src/app/services/dataexchange.service';
import { CreateJoinTeamService } from 'src/app/services/create-join-team.service';
import { NGXLogger } from 'ngx-logger';
import { ClientLoggerService } from 'src/app/services/client-logger.service';
import { of } from 'rxjs';

describe('CreateOrJointTeamComponent', () => {
  let component: CreateOrJointTeamComponent;
  let fixture: ComponentFixture<CreateOrJointTeamComponent>;
  let dataexchangeService: jasmine.SpyObj<DataexchangeService>;
  let createJoinService: jasmine.SpyObj<CreateJoinTeamService>;
  let logger: jasmine.SpyObj<NGXLogger>;
  let clientLoggerService: jasmine.SpyObj<ClientLoggerService>;

 beforeEach(async () => {
    dataexchangeService = jasmine.createSpyObj('DataexchangeService', ['id', 'name']);
    createJoinService = jasmine.createSpyObj('CreateJoinTeamService', ['searchTeam', 'createTeam', 'joinTeam', 'getTeams', 'getTeamPlayers', 'getRules', 'addRule']);
    logger = jasmine.createSpyObj('NGXLogger', ['info', 'error']);
    clientLoggerService = jasmine.createSpyObj('ClientLoggerService', ['log', 'saveLogsToFile']);

    await TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      declarations: [CreateOrJointTeamComponent],
      providers: [
        { provide: DataexchangeService, useValue: dataexchangeService },
        { provide: CreateJoinTeamService, useValue: createJoinService },
        { provide: NGXLogger, useValue: logger },
        { provide: ClientLoggerService, useValue: clientLoggerService }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CreateOrJointTeamComponent);
    component = fixture.componentInstance;
   
    fixture.detectChanges();
  });
  


  it('should create', () => {
    expect(component).toBeTruthy();
  });
  
  it('should update the team name', () => {
    const updatedValue = 'Updated Team Name';
    component.onTeamChange(updatedValue);
    expect(component.teamName).toEqual(updatedValue);
  });

  it('should update the rule', () => {
    const updatedValue = 'Updated Rule';
    component.onRuleChange(updatedValue);
    expect(component.rule).toEqual(updatedValue);
  });

  it('should search for a team', () => {
    const teamName = 'Test Team';
    const data = {
      sport_id: component.sport_details.id,
      team: component.teamName
    };
    component.searchTeam(teamName);
    expect(createJoinService.searchTeam).toHaveBeenCalledWith(data);
  });
  
  
});
