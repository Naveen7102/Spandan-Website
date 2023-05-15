import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Sport } from 'src/app/models/sport.model';
import { User } from 'src/app/models/user.model';
import { DataexchangeService } from 'src/app/services/dataexchange.service';
import { SportslistService } from 'src/app/services/sportslist.service';
import { NGXLogger } from 'ngx-logger';
import { ClientLoggerService } from 'src/app/services/client-logger.service';

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
  user_details: any;
  date : string = '';

  constructor(private router: Router, private dataservice: DataexchangeService, private sportsService: SportslistService, private logger: NGXLogger, private clientLoggerService: ClientLoggerService) {
    this.sports = new Array<Sport>;
    this.sportsArr = new Array<Array<Sport>>;
    this.sportsArr.push(new Array<Sport>);
    this.user_details = {};
    this.dataservice.id.subscribe(data=>{
      this.user_details = data;
    });
    this.user_details = JSON.parse(localStorage.getItem("user_details")!);
    console.log("sfdz");
    console.log(this.user_details);
    this.getSports();
    console.log(this.sportsArr);
  }

  ngOnInit(): void {
    
  }

  isAdmin() :boolean{
    return this.user_details.userType === "Admin";
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
        this.logger.info("Successfully received the sports present");
        this.clientLoggerService.log("Successfully received the sports present");
      },
      error: (e) => {
        this.logger.error("Error in receiving the sports present");
        this.clientLoggerService.log("Error in receiving the sports present");
        console.error(e);}
    });
  }

  redirectToFixturesPage(sport_id:number, sport_name: string) {
    this.dataservice.changeUserId(this.user_details);
    const sport_details:Sport = {
      id: sport_id,
      name: sport_name
    };
    this.dataservice.changeSport(sport_details);
    localStorage.setItem("sport_details",JSON.stringify(sport_details));
    this.logger.info("Redirecting to " + sport_name + " fixtures page");
    this.clientLoggerService.log("Redirecting to " + sport_name + " fixtures page");
    this.router.navigate(['fixtures']);
  }

  redirectToTeamsPage(sport_id:number, sport_name: string) {
    this.dataservice.changeUserId(this.user_details);
    const sport_details:Sport = {
      id: sport_id,
      name: sport_name
    };
    this.dataservice.changeSport(sport_details);
    localStorage.setItem("sport_details",JSON.stringify(sport_details));
    this.logger.info("Redirecting to " + sport_name + " teams page");
    this.clientLoggerService.log("Redirecting to " + sport_name + " teams page");
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
        this.logger.info("Successfully added sport");
        this.clientLoggerService.log('Successfully added sport');
        this.getSports();
      },
      error: (e) => {
        this.clientLoggerService.log('Failed to add sport');
        this.logger.error("Failed to add sport");
        console.error(e);}
    });
  }

  AddSpocs(email: string): void{
    this.sportsService.addSpoc(email)
      .subscribe({
        next: (data:string) => {
          this.email = '';
          this.logger.info("Successfully added spoc");
          this.clientLoggerService.log('Successfully added spoc');
          alert("Succesfully added spoc");
        },
        error: (e) => {
          this.logger.error("Failed to add spoc");
          this.clientLoggerService.log('Failed to add spoc');
          console.error(e);}
      });
  }

  
  SetDate(date: string): void{
    const data = {
      date : date
    }
    this.sportsService.setStartDate(data)
      .subscribe({
        next: (data:string) => {
          this.date = '';
          this.logger.info("Succesfully changed the start date");
          this.clientLoggerService.log('Succesfully changed the start date');
          alert("Succesfully Changed Date");
        },
        error: (e) => {
          this.logger.error("Error in changing the start date");
          this.clientLoggerService.log('Error in changing the start date');
          console.error(e);}
      });
  }

  logout(){
    this.logger.info("Logout Successful");
    this.clientLoggerService.log('Logout Successful');
    this.clientLoggerService.saveLogsToFile();
    localStorage.removeItem('token');
    this.router.navigate(['login']);
  }

}
