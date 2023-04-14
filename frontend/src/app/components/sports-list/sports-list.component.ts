import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sports-list',
  templateUrl: './sports-list.component.html',
  styleUrls: ['./sports-list.component.css']
})
export class SportsListComponent {
  constructor(private router: Router) {}

  redirectToFixturesPage() {
    this.router.navigate(['fixtures']);
  }

  redirectToTeamsPage() {
    this.router.navigate(['create-join-team']);
  }

}
