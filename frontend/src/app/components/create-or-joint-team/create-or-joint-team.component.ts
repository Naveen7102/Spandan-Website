import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { DataexchangeService } from 'src/app/services/dataexchange.service';

@Component({
  selector: 'app-create-or-joint-team',
  templateUrl: './create-or-joint-team.component.html',
  styleUrls: ['./create-or-joint-team.component.css']
})
export class CreateOrJointTeamComponent implements OnInit {
  sport_id: number;
  user_details: User;

  constructor(private dataservice: DataexchangeService) {
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

  // changeSport(name: string) {
  //   this.dataservice.changeSport(this.sport_id);
  // }
}
