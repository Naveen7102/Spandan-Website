import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class DataexchangeService {
  private nameSource = new BehaviorSubject<string>('');
  name = this.nameSource.asObservable();

  private userIdSource = new BehaviorSubject<User>({});
  id = this.userIdSource.asObservable();

  changeSport(name: string) {
    this.nameSource.next(name);
  }

  changeUserId(id: User) {
    this.userIdSource.next(id);
  }

  constructor() { }
}
