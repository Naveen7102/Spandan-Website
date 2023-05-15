import { Component, OnInit } from '@angular/core';
import { Teams } from 'src/app/models/teams.model';
import { User } from 'src/app/models/user.model';
import { CreateJoinTeamService } from 'src/app/services/create-join-team.service';
import { DataexchangeService } from 'src/app/services/dataexchange.service';
import { Router } from '@angular/router';
import { Sport } from 'src/app/models/sport.model';
import { NGXLogger } from 'ngx-logger';
import { ClientLoggerService } from 'src/app/services/client-logger.service';

@Component({
  selector: 'app-create-or-joint-team',
  templateUrl: './create-or-joint-team.component.html',
  styleUrls: ['./create-or-joint-team.component.css']
})
export class CreateOrJointTeamComponent implements OnInit {
  sport_details: Sport;
  user_details: any;
  displayTeamPlayers: boolean;
  teamName: string;
  players: Array<string>;
  getTeams:boolean;
  joinTeamName: string;
  teamsList: Array<Teams>;
  playersList: Array<User>;
  rulesList: Array<string>;
  rule: string = '';

  constructor(private router: Router, private dataservice: DataexchangeService, private createJoinService: CreateJoinTeamService,private logger: NGXLogger, private clientLoggerService: ClientLoggerService) {
    this.displayTeamPlayers = false;
    this.teamName = '';
    this.joinTeamName = '';
    this.playersList = new Array<User>;
    this.rulesList = new Array<string>;
    this.getTeams = true;
    this.players = new Array<string>;
    this.teamsList = new Array<Teams>;
    this.sport_details = {};
    this.user_details = {};
    this.dataservice.id.subscribe(data => {
      this.user_details = data;
    });
    this.dataservice.name.subscribe(data=>{
      this.sport_details = data;
    });
    this.user_details = JSON.parse(localStorage.getItem("user_details")!);
    this.sport_details = JSON.parse(localStorage.getItem("sport_details")!);
    console.log(this.sport_details);
    this.getRulesList();
  }

  ngOnInit(): void {

  }

  isAdminSpoc() :boolean{
    return this.user_details.userType === "Admin" || this.user_details.userType === "SPOC";
  }

  onTeamChange(UpdatedValue: string):void{
		this.teamName = UpdatedValue;
	}

  onRuleChange(UpdatedValue: string):void{
		this.rule = UpdatedValue;
	}

  searchTeam(name: string){

    const data = {
      sport_id: this.sport_details.id,
      team: this.teamName
    };
    console.log(data);
    this.createJoinService.searchTeam(data)
      .subscribe({
        next: (data:Array<string>) => {
          this.logger.info("Succesfully received the team");
          this.clientLoggerService.log("Succesfully received the team");
          this.players = data;
          this.displayTeamPlayers = true;
          this.joinTeamName = name;
        },
        error: (e) => {
          this.logger.error("Team not found");
          this.clientLoggerService.log("Team not found");
          alert("Team Not found");
          console.error(e);
        }
      });
    this.teamName = '';
  }

  createTeam(name: string){
    const data = {
      sport_id: this.sport_details.id,
      name: this.teamName
    };
    console.log(data);
    this.createJoinService.createTeam(data)
    .subscribe({
      next: (data: string) => {
        this.logger.info("Succesfully created a team");
        this.clientLoggerService.log("Succesfully created a team");
        this.displayTeamPlayers = false;
        alert(data);
      },
      error: (e) => {
        this.logger.error("Failed to create team");
        this.clientLoggerService.log("Failed to create team");
        alert(e.message);
        console.error(e);
      }
    });
    this.teamName = '';
  }

  joinTeam(){
    const data = {
      sport_id: this.sport_details.id,
      team: this.joinTeamName,
      participant_id: this.user_details.id
    };
    console.log(data);
    this.createJoinService.joinTeam(data)
    .subscribe({
      next: (data: string) => {
        this.logger.info("Succesfully added the user to a team");
        this.clientLoggerService.log("Succesfully added the user to a team");
        this.displayTeamPlayers = false;
        alert(data);
      },
      error: (e) => {
        this.logger.error("Failed to join team");
        this.clientLoggerService.log("Failed to join team");
        alert(e.error.message);
        console.error(e);
      }
    });
    this.joinTeamName = '';
  }

  getTeamslist(){
    this.createJoinService.getTeams(this.sport_details.id)
    .subscribe({
      next: (data: any) => {
        this.logger.info("Succesfully received the teams list");
        this.clientLoggerService.log("Succesfully received the teams list");
        console.log(data);
        this.getTeams = false;
        this.teamsList = data;
      },
      error: (e) => {
        this.logger.error("Error in getting teams list");
        this.clientLoggerService.log("Error in getting teams list");
        alert("Teams not Found");
        console.error(e);
      }
    });
  }

  getTeamPlayers(teamname: any){
    const data = {
      sport_id: this.sport_details.id,
      name: teamname
    };
    this.createJoinService.getTeamPlayers(data)
    .subscribe({
      next: (data: Array<User>) => {
        this.logger.info("Succesfully received the players of the team");
        this.clientLoggerService.log("Succesfully received the players of the team");
        console.log(data);
        this.playersList = data;
      },
      error: (e) => {
        this.logger.error("Error in getting Team players");
        this.clientLoggerService.log("Error in getting Team players");
        alert("players not Found");
        console.error(e);
      }
    });
  }

  getRulesList(){
    this.createJoinService.getRules(this.sport_details.id,)
    .subscribe({
      next: (data: any) => {
        this.logger.info("Sucessfuly received the rules");
        this.clientLoggerService.log("Sucessfuly received the rules");
        console.log(data);
        this.rulesList = data;
      },
      error: (e) => {
        this.logger.error("Rules not found");
        this.clientLoggerService.log("Rules not found");
        alert("Rules not Found");
        console.error(e);
      }
    });
  }

  addRule(rule: string){
    const data = {
      sport_id: this.sport_details.id,
      rule: rule
    };
    this.createJoinService.addRule(data)
    .subscribe({
      next: (data: any) => {
        this.logger.info("Rule added succesfully");
        this.clientLoggerService.log("Rule added succesfully");
        console.log(data);
        this.rulesList = data;
        alert("rule added");
        this.getRulesList();
        this.rule = '';
      },
      error: (e) => {
        this.logger.error("Rule not added");
        this.clientLoggerService.log("Rule not added");
        alert("Rule not Added");
        console.error(e);
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

  // changeSport(name: string) {
  //   this.dataservice.changeSport(this.sport_id);
  // }
}
