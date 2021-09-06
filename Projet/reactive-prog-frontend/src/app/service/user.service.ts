import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from 'src/models/customer';
import { GenericService } from './genericService';

@Injectable({
  providedIn: 'root'
})
export class UserService extends GenericService<Customer, Number> {
  constructor(http: HttpClient) {
    super(http, 'http://localhost:8080');
  }

  signIn(username: string, password: string): Observable<Customer> {
    return this.http.get<Customer>(this.url + '/login?username=' + username + "&password=" + password);
  }

  signUp(customer: Customer): Observable<Customer> {
    console.log(customer)
    return this.http.post<Customer>(this.url + '/subscribe', customer);
  }

  userIsLogIn(): boolean {
    let email = sessionStorage.getItem('username');
    return email != null;
  }

  public logout(): void {
    sessionStorage.clear();
  }

}
