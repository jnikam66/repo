import { TestBed, inject } from '@angular/core/testing';

import { Login.ServiceService } from './login.service.service';

describe('Login.ServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [Login.ServiceService]
    });
  });

  it('should be created', inject([Login.ServiceService], (service: Login.ServiceService) => {
    expect(service).toBeTruthy();
  }));
});
