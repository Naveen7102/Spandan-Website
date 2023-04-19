import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const baseUrl = 'http://localhost:8101';

@Injectable({
  providedIn: 'root'
})
export class HomepageService {

  constructor(private http: HttpClient) { }

  getStartDate(): Observable<any>{
    return this.http.get<any>(`${baseUrl}/date/getDate`);
  }

  setStartDate(date: any): Observable<string>{
    console.log(date);
    return this.http.post<string>(`${baseUrl}/date/addStartDate`, date);
  }

  

}
