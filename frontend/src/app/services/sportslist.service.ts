import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Sport } from '../models/sport.model';

const baseUrl = 'http://localhost:8101';

@Injectable({
  providedIn: 'root'
})
export class SportslistService {

  constructor(private http: HttpClient) { }

  addSport(sport: string): Observable<string>{
    return this.http.post<string>(`${baseUrl}/sport/addSport`,sport);
  }

  getSports(): Observable<Array<Sport>>{
    return this.http.get<Array<Sport>>(`${baseUrl}/sport/getSports`);
  }

  addSpoc(email: string): Observable<string>{
    return this.http.post<string>(`${baseUrl}/user/updateSPOC`, email);
  }

}
