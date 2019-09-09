import { Component, OnInit } from '@angular/core';
import { AuthManagerService } from 'src/app/services/auth-manager.service';
import { User } from 'src/app/metier/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  title : string;
  currentUser: [boolean, User];

  constructor(private authManager: AuthManagerService,
              private router: Router) { 
    this.title = "ma super librairie";
    this.currentUser = [false, null];
  }
  
  public logout() : void {
    this.authManager.logout();
    // a ajouter suivant le site
    this.router.navigateByUrl("/login");
  }

  ngOnInit() {
    this.authManager.getUserSubjectAsObservable()
                    .subscribe( u => {
                      this.currentUser = u;
                    });
  }

}
