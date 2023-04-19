import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataexchangeService {
  private nameSource = new BehaviorSubject<string>('');
  name = this.nameSource.asObservable();

  private userIdSource = new BehaviorSubject<number>(1);
  id = this.userIdSource.asObservable();

  changeSport(name: string) {
    this.nameSource.next(name);
  }

  changeUserId(id: number) {
    this.userIdSource.next(id);
  }

  constructor() { }
}
