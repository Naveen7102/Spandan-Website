import { Component, OnInit  } from '@angular/core';
import { DataexchangeService } from 'src/app/services/dataexchange.service';

@Component({
  selector: 'app-fixtures',
  templateUrl: './fixtures.component.html',
  styleUrls: ['./fixtures.component.css']
})
export class FixturesComponent implements OnInit  {

  sport: string;

  constructor(private dataservice: DataexchangeService) {
    this.sport = '';
    this.dataservice.name.subscribe(data=>{
      this.sport = data;
    });
    console.log(this.sport);
  }

  ngOnInit(): void {

  }

  // changeSport(name: string) {
  //   this.dataservice.changeSport(this.sport);
  // }

}
