import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Dashboard } from '../model/dashboard.model';

@Injectable()
export class DashboardService {

  private baseUrl = 'http://localhost:8080/api/dashboards';

  constructor(private http: HttpClient) { }
  
  getDashboard(id : number): Observable<Dashboard> {
    return this.http.get<Dashboard>(`${this.baseUrl}/${id}`);
  }
  update(id : number, dashboard): Observable<Dashboard> { 
    return this.http.put<Dashboard>(`${this.baseUrl}/${id}`, dashboard);
  }
  changeDashboard(id : number, dashboard): Observable<Dashboard> { 
    return this.http.put<Dashboard>(`${this.baseUrl}/${id}/change`, dashboard);
  }
  search(id : number, search: string): Observable<Dashboard> {
    let params = new HttpParams()
      .set('search', search);
    return this.http.get<Dashboard>(`${this.baseUrl}/${id}/search`, { params: params });
  }
  delete(id : number): Observable<Dashboard> {
    return this.http.delete<Dashboard>(`${this.baseUrl}/${id}`);
  }

}
