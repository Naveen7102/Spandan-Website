import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const baseUrl = 'http://localhost:8101';

@Injectable({
  providedIn: 'root'
})
export class HomepageService {

  constructor(private http: HttpClient) { }

  getStartDate(): Observable<string>{
    return this.http.get<string>(`${baseUrl}/GetDoctorsList`);
  }

}
