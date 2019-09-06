import { Component, OnInit } from '@angular/core';
import { AuthManagerService } from 'src/app/services/auth-manager.service';
import { Router } from '@angular/router';
import { User } from 'src/app/metier/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authManager: AuthManagerService,
              private router: Router) { }

  userlogin : User;
  ngOnInit() {
    this.userlogin = new User("", "");
  }

  public logMeIn() : void {
    this.authManager.setCurrentUser(this.userlogin);
    // normalement, je devrais requeter l'utilisateur du backend
    this.router.navigateByUrl("");
  }
}
