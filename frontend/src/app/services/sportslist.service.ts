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
    const token = localStorage.getItem('token');
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    return this.http.post<string>(`${baseUrl}/sport/addSport`,sport,{headers});
  }

  getSports(): Observable<Array<Sport>>{
    
    const token = localStorage.getItem('token');
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    return this.http.get<Array<Sport>>(`${baseUrl}/sport/getSports`,{headers});
  }

  addSpoc(email: string): Observable<string>{
    const token = localStorage.getItem('token');
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    return this.http.post<string>(`${baseUrl}/user/updateSPOC`, email,{headers});
  }

  setStartDate(date: any): Observable<string>{
    const token = localStorage.getItem('token');
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    console.log(date);
    return this.http.post<string>(`${baseUrl}/date/addStartDate`, date,{headers});
  }

}
