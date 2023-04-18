import { TestBed } from '@angular/core/testing';

import { SportslistService } from './sportslist.service';

describe('SportslistService', () => {
  let service: SportslistService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SportslistService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
