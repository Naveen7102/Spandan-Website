import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from '../models/user.model';
import { Sport } from '../models/sport.model';

@Injectable({
  providedIn: 'root'
})
export class DataexchangeService {
  private nameSource = new BehaviorSubject<Sport>({});
  name = this.nameSource.asObservable();

  private userIdSource = new BehaviorSubject<User>({});
  id = this.userIdSource.asObservable();

  changeSport(sport: Sport) {
    this.nameSource.next(sport);
  }

  changeUserId(id: User) {
    this.userIdSource.next(id);
  }

  constructor() { }
}
