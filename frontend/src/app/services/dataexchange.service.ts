import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataexchangeService {
  private nameSource = new BehaviorSubject<string>('');
  name = this.nameSource.asObservable()

  changeSport(name: string) {
    this.nameSource.next(name);
  }

  constructor() { }
}
