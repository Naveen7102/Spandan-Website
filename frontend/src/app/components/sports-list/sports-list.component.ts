import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { DataexchangeService } from 'src/app/services/dataexchange.service';

@Component({
  selector: 'app-sports-list',
  templateUrl: './sports-list.component.html',
  styleUrls: ['./sports-list.component.css']
})
export class SportsListComponent {

  sports: Array<string>;
  sportsArr: Array<Array<string>>;

  constructor(private router: Router, private dataservice: DataexchangeService) {
    this.sports = new Array<string>;
    this.sportsArr = new Array<Array<string>>;
    this.sportsArr.push(new Array<string>);
  }

  ngOnInit(): void {
    this.getSports();
    for( var i = 0; i<this.sports.length; i+=3)
    {
      var sublist = new Array<string>;
      for(var j = 0; j < 3 ; j+=1)
      {
        sublist.push(this.sports[i+j]);
      }
      this.sportsArr.push(sublist);
    }
    console.log(this.sportsArr);
  }

  getSports(){
    this.sports = ['Cricket','Football','Badminton', 'Table Tennis', 'Basketball', 'Volleyball']
  }

  redirectToFixturesPage(sport:string) { 
    this.dataservice.changeSport(sport);
    this.router.navigate(['fixtures']);
  }

  redirectToTeamsPage(sport:string) {
    this.dataservice.changeSport(sport);
    this.router.navigate(['create-join-team']);
  }

}
