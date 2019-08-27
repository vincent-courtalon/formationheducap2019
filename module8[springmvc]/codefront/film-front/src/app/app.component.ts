import { Component, OnInit } from '@angular/core';
import { Film } from './metier/film';
import { FilmRepositoryService } from './services/film-repository.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
 
  ngOnInit(): void {
  }
 
}
