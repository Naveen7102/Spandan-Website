import { TestBed } from '@angular/core/testing';

import { CreateJoinTeamService } from './create-join-team.service';

describe('CreateJoinTeamService', () => {
  let service: CreateJoinTeamService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateJoinTeamService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
