import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardGuard{
  constructor(private router: Router) { }
  canActivate(){
    const token = localStorage.getItem('token');
    if (token) {
      return true;
    }
    alert('Please Login First');
    this.router.navigate(['login']);
    return false;
  }
}
