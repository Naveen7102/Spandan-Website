import { Component, OnInit  } from '@angular/core';
import { Fixture } from 'src/app/models/fixture.model';
import { User } from 'src/app/models/user.model';
import { DataexchangeService } from 'src/app/services/dataexchange.service';
import { FixturesService } from 'src/app/services/fixtures.service';

@Component({
  selector: 'app-fixtures',
  templateUrl: './fixtures.component.html',
  styleUrls: ['./fixtures.component.css']
})
export class FixturesComponent implements OnInit  {

  sport_id: number;
  user_details: User;
  fixtures: Array<Fixture>;
  team1: string = '';
  team2: string = '';
  fixtureNumber: any;
  winner: string = '';
  result: string = '';
  date: string = '';
  time: string = '';

  constructor(private dataservice: DataexchangeService, private fixtureService: FixturesService) {
    this.sport_id = -1;
    this.fixtureNumber = null;
    this.user_details = {};
    this.fixtures = new Array<Fixture>;
    this.dataservice.id.subscribe(data => {
      this.user_details = data;
    });
    this.dataservice.name.subscribe(data=>{
      this.sport_id = data;
    });
    this.getFixtures();
    console.log(this.sport_id);
  }

  ngOnInit(): void {

  }

  getFixtures(){
    this.fixtureService.getFixtures(this.sport_id)
    .subscribe({
      next: (data: Array<Fixture>) => {
        this.fixtures = data;
      },
      error: (e) => {
        alert("Failed to get fixtures for this sport");
        console.log(e);
      }
    });
  }

  addFixture(){
    const data = {
      sport_id: this.sport_id,
      team1: this.team1,
      team2: this.team2,
      time: this.date + " " + this.time,
    }
    console.log(data);
    this.fixtureService.addFixture(data)
      .subscribe({
        next: (data:any) => {
          console.log(data.message);
          this.getFixtures();
          alert("Success");
        },
        error: (e) => {
          alert("Failed");
          console.error(e);
        }
      });
  }

  addResult(){
    const data = {
      id: this.fixtures[this.fixtureNumber].id,
      sport_id: this.sport_id,
      winner: this.winner,
      result: this.result
    }
    console.log(data);
    this.fixtureService.updateFixture(data)
      .subscribe({
        next: (data:any) => {
          console.log(data.message);
          this.getFixtures();
          alert("Success");
        },
        error: (e) => {
          alert("Failed");
          console.error(e);
        }
      });
  }

  deleteFixture(fixture_id: any){
    this.fixtureService.deleteFixture(fixture_id)
    .subscribe({
      next: (data: any) => {
        console.log(data);
        alert("Success");
      },
      error: (e) => {
        alert("Failed");
        console.log(e);
      }
    });
  }

}
