import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/models/user';
import { GenericService } from './genericService';

@Injectable({
  providedIn: 'root'
})
export class UserService extends GenericService<User, Number> {
  constructor(http: HttpClient) {
    super(http, 'http://localhost:8080');
  }

  signIn(email: string, password: string): Observable<User> {
    return this.http.get<User>(this.url + '/login/' + email + '/' + password, {
      responseType: 'event-stream' as 'json',
    });
  }

  signUp(user: User): Observable<User> {
    return this.http.post<User>(this.url + '/subscribe', user, {
      responseType: 'application/json' as 'json',
    });
  }

}
