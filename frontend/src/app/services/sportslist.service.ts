import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const baseUrl = 'http://localhost:8101';

@Injectable({
  providedIn: 'root'
})
export class SportslistService {

  constructor(private http: HttpClient) { }

  addSport(sport: string): Observable<string>{
    return this.http.post<string>(`${baseUrl}/sport/addSport`,sport);
  }

  addSpoc(email: string): Observable<string>{
    return this.http.post<string>(`${baseUrl}/SetSpoc`, email);
  }

}
