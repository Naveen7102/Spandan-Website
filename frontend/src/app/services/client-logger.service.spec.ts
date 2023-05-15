import { TestBed } from '@angular/core/testing';

import { ClientLoggerService } from './client-logger.service';

describe('ClientLoggerService', () => {
  let service: ClientLoggerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientLoggerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
