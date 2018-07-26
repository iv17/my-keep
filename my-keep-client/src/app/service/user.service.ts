import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginResponse } from '../model/loginResponse.model';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) { }

  register(user): Observable<User> {
    const options = {
      headers: new HttpHeaders()
    };
    return this.http.post<User>(`${this.baseUrl}/register`, user, options);
  }

  login(user): Observable<LoginResponse> {
    const options = {
      headers: new HttpHeaders()
    };
    return this.http.post<LoginResponse>(`${this.baseUrl}/login`, user, options);
  }
  changePassword(id: number, user): Observable<User> {
    const options = {
      headers: new HttpHeaders()
    };
    return this.http.patch<User>(`${this.baseUrl}/${id}`, user, options);
  }
  getMe(): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/me`);
  }
  update(id: number, user): Observable<User> {
    const options = {
      headers: new HttpHeaders()
    };
    return this.http.put<User>(`${this.baseUrl}/${id}`, user, options);
  }

  logout(): Observable<any> {
    console.log('logout');
    return this.http.get(`${this.baseUrl}/logout`);
  }

  getToken(): string {
    return localStorage.getItem('token');
  }

  removeToken() {
    localStorage.removeItem('token');
  }
  
  removeAll() {
    localStorage.removeItem('token');

    localStorage.removeItem('notes');
    localStorage.removeItem('archive');
    localStorage.removeItem('trash');
    localStorage.removeItem('notesID');
    localStorage.removeItem('archiveID');
    localStorage.removeItem('trashID');
  }
  
}
