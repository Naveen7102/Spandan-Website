import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const baseUrl = 'http://localhost:8101';

@Injectable({
  providedIn: 'root'
})
export class FixturesService {

  constructor(private http: HttpClient) { }

  getFixtures(sport_id: number): Observable<any>{
    const token = localStorage.getItem('token');
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    return this.http.get<any>(`${baseUrl}/fixtures/getFixtures/${sport_id}`,{headers});
  }

  addFixture(data: any){
    const token = localStorage.getItem('token');
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    return this.http.post<any>(`${baseUrl}/fixtures/addFixture`, data,{headers});
  }

  updateFixture(data: any){
    const token = localStorage.getItem('token');
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    return this.http.put<any>(`${baseUrl}/fixtures/updateResult`, data,{headers});
  }

  deleteFixture(fixture_id: any): Observable<any>{
    const token = localStorage.getItem('token');
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    return this.http.delete<any>(`${baseUrl}/fixtures/deleteFixture/${fixture_id}`,{headers});
  }

  getTeams(data: number): Observable<any>{
    const token = localStorage.getItem('token');
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    return this.http.get<any>(`${baseUrl}/teams/getTeams/${data}`,{headers});
  }

}
