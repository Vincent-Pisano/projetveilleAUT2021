import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardGuard implements CanActivate {
  constructor(private service: UserService, private router: Router){}

  canActivate(
    route: ActivatedRouteSnapshot, state: RouterStateSnapshot){
      if (this.service.userIsLogIn()) 
      return true;
      this.router.navigate(['/login'])
    return false;
  }

  
  
}
