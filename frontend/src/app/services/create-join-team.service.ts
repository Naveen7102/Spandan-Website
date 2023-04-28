import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const baseUrl = 'http://localhost:8101';

@Injectable({
  providedIn: 'root'
})
export class CreateJoinTeamService {

  constructor(private http: HttpClient) { }

  searchTeam(data: any): Observable<any>{
    const token = localStorage.getItem('token');
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    return this.http.get<any>(`${baseUrl}/teamMembers/getPlayers?sport_id=${data.sport_id}&team=${data.team}`, {headers});
  }

  createTeam(data: any): Observable<string>{
    const token = localStorage.getItem('token');
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    return this.http.post<string>(`${baseUrl}/teams/addTeam`,data,{headers});
  }

  joinTeam(data: any): Observable<string>{
    const token = localStorage.getItem('token');
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    console.log(data);
    return this.http.post<string>(`${baseUrl}/teamMembers/addMember`,data,{headers});
  }

  getTeams(data: number): Observable<any>{
    const token = localStorage.getItem('token');
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    return this.http.get<any>(`${baseUrl}/teams/getTeams/${data}`, {headers});
  }

  getTeamPlayers(data: any): Observable<any>{
    const token = localStorage.getItem('token');
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    console.log(data);
    return this.http.get<any>(`${baseUrl}/teamMembers/getPlayersDetails?sport_id=${data.sport_id}&team=${data.name}`, {headers});
  }

  getRules(data: number): Observable<any>{
    const token = localStorage.getItem('token');
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    return this.http.get<any>(`${baseUrl}/rules/getRule/${data}`, {headers});
  }

  addRule(data: any): Observable<any>{
    const token = localStorage.getItem('token');
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    return this.http.post<any>(`${baseUrl}/rules/addRule`, data, {headers});
  }

}
