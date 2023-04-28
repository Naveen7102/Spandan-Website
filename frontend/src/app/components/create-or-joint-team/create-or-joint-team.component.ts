import { Component, OnInit } from '@angular/core';
import { Teams } from 'src/app/models/teams.model';
import { User } from 'src/app/models/user.model';
import { CreateJoinTeamService } from 'src/app/services/create-join-team.service';
import { DataexchangeService } from 'src/app/services/dataexchange.service';
import { Router } from '@angular/router';

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
  joinTeamName: string;
  teamsList: Array<Teams>;
  playersList: Array<User>;
  rulesList: Array<string>;
  rule: string = '';

  constructor(private router: Router, private dataservice: DataexchangeService, private createJoinService: CreateJoinTeamService) {
    this.displayTeamPlayers = false;
    this.teamName = '';
    this.joinTeamName = '';
    this.playersList = new Array<User>;
    this.rulesList = new Array<string>;
    this.getTeams = true;
    this.players = new Array<string>;
    this.teamsList = new Array<Teams>;
    this.sport_id = -1;
    this.user_details = {};
    this.dataservice.id.subscribe(data => {
      this.user_details = data;
    });
    this.dataservice.name.subscribe(data=>{
      this.sport_id = data;
    });
    console.log(this.sport_id);
    this.getRulesList();
  }

  ngOnInit(): void {

  }

  isAdminSpoc() :boolean{
    return this.user_details.user_type === "Admin" || this.user_details.user_type === "SPOC";
  }

  onTeamChange(UpdatedValue: string):void{
		this.teamName = UpdatedValue;
	}

  onRuleChange(UpdatedValue: string):void{
		this.rule = UpdatedValue;
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
          this.joinTeamName = name;
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

  joinTeam(){
    const data = {
      sport_id: this.sport_id,
      team: this.joinTeamName,
      participant_id: this.user_details.id
    };
    console.log(data);
    this.createJoinService.joinTeam(data)
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
    this.joinTeamName = '';
  }

  getTeamslist(){
    this.createJoinService.getTeams(this.sport_id)
    .subscribe({
      next: (data: any) => {
        console.log(data);
        this.getTeams = false;
        this.teamsList = data;
      },
      error: (e) => {
        alert("Team not Found");
        console.error(e);
      }
    });
  }

  getTeamPlayers(teamname: any){
    const data = {
      sport_id: this.sport_id,
      name: teamname
    };
    this.createJoinService.getTeamPlayers(data)
    .subscribe({
      next: (data: Array<User>) => {
        console.log(data);
        this.playersList = data;
      },
      error: (e) => {
        alert("Team not Found");
        console.error(e);
      }
    });
  }

  getRulesList(){
    this.createJoinService.getRules(this.sport_id)
    .subscribe({
      next: (data: any) => {
        console.log(data);
        this.rulesList = data;
      },
      error: (e) => {
        alert("Rules not Found");
        console.error(e);
      }
    });
  }

  addRule(rule: string){
    const data = {
      sport_id: this.sport_id,
      rule: rule
    };
    this.createJoinService.addRule(data)
    .subscribe({
      next: (data: any) => {
        console.log(data);
        this.rulesList = data;
        alert("rule added");
      },
      error: (e) => {
        alert("Rules not Found");
        console.error(e);
      }
    });
  }

  logout(){
    localStorage.removeItem('token');
    this.router.navigate(['login']);
  }

  // changeSport(name: string) {
  //   this.dataservice.changeSport(this.sport_id);
  // }
}
