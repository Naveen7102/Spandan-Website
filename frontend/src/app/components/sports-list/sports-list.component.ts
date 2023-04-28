import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Sport } from 'src/app/models/sport.model';
import { User } from 'src/app/models/user.model';
import { DataexchangeService } from 'src/app/services/dataexchange.service';
import { SportslistService } from 'src/app/services/sportslist.service';

@Component({
  selector: 'app-sports-list',
  templateUrl: './sports-list.component.html',
  styleUrls: ['./sports-list.component.css']
})
export class SportsListComponent {

  sport: string = '';
  sports: Array<Sport>;
  sportsArr: Array<Array<Sport>>;
  email: string = '';
  user_details: User;
  date : string = '';

  constructor(private router: Router, private dataservice: DataexchangeService, private sportsService: SportslistService) {
    this.sports = new Array<Sport>;
    this.sportsArr = new Array<Array<Sport>>;
    this.sportsArr.push(new Array<Sport>);
    this.user_details = {};
    this.dataservice.id.subscribe(data=>{
      this.user_details = data;
    });
    console.log("sfdz");
    console.log(this.user_details);
    this.getSports();
    console.log(this.sportsArr);
  }

  ngOnInit(): void {
    
  }

  isAdmin() :boolean{
    return this.user_details.user_type === "ADMIN";
  }

  onMailChange(UpdatedValue: string):void
	{
		this.email = UpdatedValue;
	}

  onDateChange(UpdatedValue: string):void
	{
		this.date = UpdatedValue;
	}

  getSports(){
    this.sportsService.getSports()
    .subscribe({
      next: (data: Array<Sport>) => {
        this.sports = data;
        this.sportsArr = new Array<Array<Sport>>;
        this.sportsArr.push(new Array<Sport>);
        for( var i = 0; i < this.sports.length; i+=3)
        {
          var sublist = new Array<Sport>;
          for(var j = 0; j < 3 && i+j < this.sports.length ; j+=1)
          {
            sublist.push(this.sports[i+j]);
          }
          this.sportsArr.push(sublist);
        }
      },
      error: (e) => console.error(e)
    });
  }

  redirectToFixturesPage(sport:number) {
    this.dataservice.changeUserId(this.user_details);
    this.dataservice.changeSport(sport);
    this.router.navigate(['fixtures']);
  }

  redirectToTeamsPage(sport:any) {
    this.dataservice.changeUserId(this.user_details);
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
        this.sport = '';
        this.getSports();
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

  
  SetDate(date: string): void{
    const data = {
      date : date
    }
    this.sportsService.setStartDate(data)
      .subscribe({
        next: (data:string) => {

        },
        error: (e) => console.error(e)
      });
  }

  logout(){
    localStorage.removeItem('token');
    this.router.navigate(['login']);
  }

}
