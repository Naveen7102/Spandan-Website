import { Component, OnInit  } from '@angular/core';
import { Fixture } from 'src/app/models/fixture.model';
import { Teams } from 'src/app/models/teams.model';
import { User } from 'src/app/models/user.model';
import { DataexchangeService } from 'src/app/services/dataexchange.service';
import { FixturesService } from 'src/app/services/fixtures.service';
import { Router } from '@angular/router';
import { Sport } from 'src/app/models/sport.model';
import { NGXLogger } from 'ngx-logger';
import { ClientLoggerService } from 'src/app/services/client-logger.service';

@Component({
  selector: 'app-fixtures',
  templateUrl: './fixtures.component.html',
  styleUrls: ['./fixtures.component.css']
})
export class FixturesComponent implements OnInit  {

  sport_details: Sport;
  user_details: any;
  fixtures: Array<Fixture>;
  team1: string = '';
  team2: string = '';
  fixtureNumber: any;
  winner: string = '';
  result: string = '';
  date: string = '';
  time: string = '';
  getTeams:boolean;
  teamsList: Array<Teams>;

  constructor(private router: Router,private dataservice: DataexchangeService, private fixtureService: FixturesService,private logger: NGXLogger, private clientLoggerService: ClientLoggerService) {
    this.sport_details = {};
    this.fixtureNumber = null;
    this.user_details = {};
    this.teamsList = new Array<Teams>;
    this.getTeams = true;
    this.fixtures = new Array<Fixture>;
    this.dataservice.id.subscribe(data => {
      this.user_details = data;
    });
    this.dataservice.name.subscribe(data=>{
      this.sport_details = data;
    });
    this.user_details = JSON.parse(localStorage.getItem("user_details")!);
    this.sport_details = JSON.parse(localStorage.getItem("sport_details")!);
    // console.log(this.user_details);
    // this.isAdminSpoc();
    this.getFixtures();
    console.log(this.sport_details.id);
  }

  ngOnInit(): void {

  }

  isAdminSpoc():boolean{
    // console.log(this.user_details.userType);
    return this.user_details.userType === "Admin" || this.user_details.userType === "SPOC";
  }

  getFixtures(){
    this.fixtureService.getFixtures(this.sport_details.id)
    .subscribe({
      next: (data: any) => {
        this.logger.info("Sucessfully received the fixtures");
        this.clientLoggerService.log("Sucessfully received the fixtures");
        this.fixtures = data;
        console.log(this.fixtures)
      },
      error: (e) => {
        this.logger.error("Faileed to get the fixtures result");
        this.clientLoggerService.log("Faileed to get the fixtures result");
        alert("Failed to get fixtures for this sport");
        console.log(e);
      }
    });
  }

  addFixture(){
    const data = {
      sport_id: this.sport_details.id,
      team1: this.team1,
      team2: this.team2,
      time: this.date + " " + this.time + ":00",
    }
    console.log(data);
    this.fixtureService.addFixture(data)
      .subscribe({
        next: (data:any) => {
          this.logger.info("Sucessfully added the fixture");
          this.clientLoggerService.log("Sucessfully added the fixture");
          console.log(data.message);
          this.getFixtures();
          alert("Success");
        },
        error: (e) => {
          this.logger.error("Faileed to add the fixtures");
          this.clientLoggerService.log("Faileed to add the fixtures");
          alert("Failed");
          console.error(e);
        }
      });
  }

  addResult(){
    console.log(this.fixtures);
    console.log(this.fixtureNumber);
    console.log(this.fixtures[this.fixtureNumber-1]);
    const data = {
      id: this.fixtures[this.fixtureNumber-1].id,
      sport_id: this.sport_details.id,
      winner: this.winner,
      result: this.result
    }
    console.log(data);
    this.fixtureService.updateFixture(data)
      .subscribe({
        next: (data:any) => {
          this.logger.info("Sucessfully added the fixture result");
          this.clientLoggerService.log("Sucessfully added the fixture result");
          console.log(data.message);
          this.getFixtures();
          alert("Success");
        },
        error: (e) => {
          this.logger.error("Failed to add the fixture result");
          this.clientLoggerService.log("Failed to add the fixture result");
          alert("Failed");
          console.error(e);
        }
      });
  }

  getTeamslist(){
    this.fixtureService.getTeams(this.sport_details.id)
    .subscribe({
      next: (data: any) => {
        this.logger.info("Sucessfully received teams list");
        this.clientLoggerService.log("Sucessfully received teams list");
        console.log(data);
        this.getTeams = false;
        this.teamsList = data;
      },
      error: (e) => {
        this.logger.error("Team not found");
        this.clientLoggerService.log("Team not found");
        alert("Team not Found");
        console.error(e);
      }
    });
  }

  deleteFixture(fixture_id: any){
    this.fixtureService.deleteFixture(fixture_id)
    .subscribe({
      next: (data: any) => {
        console.log(data);
        this.getFixtures();
        alert("Success");
        this.logger.info("Fixture deleted succesfully");
        this.clientLoggerService.log("Fixture deleted succesfully");
      },
      error: (e) => {
        this.logger.error("Error in deleting the fixture");
        this.clientLoggerService.log("Error in deleting the fixture");
        alert("Failed");
        console.log(e);
      }
    });
  }

  logout(){
    this.logger.info("Logout Successful");
    this.clientLoggerService.log("Logout Successful");
    this.clientLoggerService.saveLogsToFile();
    localStorage.removeItem('token');
    this.router.navigate(['login']);
  }

}
