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
    return this.http.get<any>(`${baseUrl}/fixtures/getFixtures/${sport_id}`);
  }

  addFixture(data: any){
    return this.http.post<any>(`${baseUrl}/fixtures/addFixture`, data);
  }

  updateFixture(data: any){
    return this.http.put<any>(`${baseUrl}/fixtures/updateResult`, data);
  }

  deleteFixture(fixture_id: any): Observable<any>{
    return this.http.delete<any>(`${baseUrl}/fixtures/deleteFixture/${fixture_id}`);
  }

  getTeams(data: number): Observable<any>{
    return this.http.get<any>(`${baseUrl}/teams/getTeams/${data}`);
  }

}
