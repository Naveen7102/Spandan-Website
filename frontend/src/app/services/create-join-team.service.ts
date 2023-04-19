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
    return this.http.get<any>(`${baseUrl}/teamMembers/getPlayers?sport_id=${data.sport_id}&team=${data.team}`);
  }

  createTeam(data: any): Observable<string>{
    return this.http.post<string>(`${baseUrl}/teams/addTeam`,data);
  }

}
