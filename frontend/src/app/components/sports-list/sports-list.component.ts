import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { DataexchangeService } from 'src/app/services/dataexchange.service';
import { SportslistService } from 'src/app/services/sportslist.service';

@Component({
  selector: 'app-sports-list',
  templateUrl: './sports-list.component.html',
  styleUrls: ['./sports-list.component.css']
})
export class SportsListComponent {

  sport: string = '';
  sports: Array<string>;
  sportsArr: Array<Array<string>>;
  email: string = '';
  user_id: number;

  constructor(private router: Router, private dataservice: DataexchangeService, private sportsService: SportslistService) {
    this.sports = new Array<string>;
    this.sportsArr = new Array<Array<string>>;
    this.sportsArr.push(new Array<string>);
    this.user_id = -1;
    this.dataservice.id.subscribe(data=>{
      this.user_id = data;
    });
    console.log("sfdz");
    console.log(this.user_id);
  }

  ngOnInit(): void {
    this.getSports();
    for( var i = 0; i<this.sports.length; i+=3)
    {
      var sublist = new Array<string>;
      for(var j = 0; j < 3 && i+j < this.sports.length ; j+=1)
      {
        sublist.push(this.sports[i+j]);
      }
      this.sportsArr.push(sublist);
    }
    console.log(this.sportsArr);
  }

  onMailChange(UpdatedValue: string):void
	{
		this.email = UpdatedValue;
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

  onSportChange(UpdatedValue: string):void{
		this.sport = UpdatedValue;
	}

  AddSport(sport: string){
    this.sportsService.addSport(sport)
    .subscribe({
      next: (data:string) => {
      },
      error: (e) => console.error(e)
    });
  }

  AddSpocs(email: string): void{
    this.sportsService.addSpoc(email)
      .subscribe({
        next: (data:string) => {
        },
        error: (e) => console.error(e)
      });
  }

}
