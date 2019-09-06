import { Injectable } from '@angular/core';
import { User } from '../metier/user';

@Injectable({
  providedIn: 'root'
})
export class AuthManagerService {
  private currentUser : User;

  public getCurrentUser() : User {
    return this.currentUser;
  }

  public setCurrentUser(user: User) :void {
    this.currentUser = user;
  }

  public getCredentials() : string {
    return window.btoa(this.currentUser.username + ":" +this.currentUser.password);
  }
  public isLoggedIn() : boolean {
    if (this.currentUser == null)
      return false;
    else
      return true;
  }
  constructor() {
    this.currentUser = null;
  }
}
