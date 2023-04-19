import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { CreateJoinTeamService } from 'src/app/services/create-join-team.service';
import { DataexchangeService } from 'src/app/services/dataexchange.service';

@Component({
  selector: 'app-create-or-joint-team',
  templateUrl: './create-or-joint-team.component.html',
  styleUrls: ['./create-or-joint-team.component.css']
})
export class CreateOrJointTeamComponent implements OnInit {
  sport_id: number;
  user_details: User;
  displayTeamPlayers: boolean;
  teamName: string;
  players: Array<string>;
  getTeams:boolean;

  constructor(private dataservice: DataexchangeService, private createJoinService: CreateJoinTeamService) {
    this.displayTeamPlayers = false;
    this.teamName = '';
    this.getTeams = true;
    this.players = new Array<string>;
    this.sport_id = -1;
    this.user_details = {};
    this.dataservice.id.subscribe(data => {
      this.user_details = data;
    });
    this.dataservice.name.subscribe(data=>{
      this.sport_id = data;
    });
    console.log(this.sport_id);
  }

  ngOnInit(): void {

  }

  onTeamChange(UpdatedValue: string):void{
		this.teamName = UpdatedValue;
	}

  searchTeam(name: string){

    const data = {
      sport_id: this.sport_id,
      team: this.teamName
    };
    console.log(data);
    this.createJoinService.searchTeam(data)
      .subscribe({
        next: (data:Array<string>) => {
          this.players = data;
          this.displayTeamPlayers = true;
        },
        error: (e) => {
          alert("invalid login");
          console.error(e);
        }
      });
    this.teamName = '';
  }

  createTeam(name: string){
    const data = {
      sport_id: this.sport_id,
      name: this.teamName
    };
    console.log(data);
    this.createJoinService.createTeam(data)
    .subscribe({
      next: (data: string) => {
        this.displayTeamPlayers = false;
        alert(data);
      },
      error: (e) => {
        alert("Team not Found");
        console.error(e);
      }
    });
    this.teamName = '';
  }

  getTeamslist(){

  }

  // changeSport(name: string) {
  //   this.dataservice.changeSport(this.sport_id);
  // }
}
