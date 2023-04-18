import { Component, OnInit } from '@angular/core';
import { HomepageService } from 'src/app/services/homepage.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  targetDate: Date = new Date('2023-04-30');
  date: string = '';
  email: string = '';
  days: number = 0;
  hours: number = 0;
  minutes: number = 0;
  seconds: number = 0;

  constructor(private homepageservice: HomepageService) {  }

  ngOnInit(): void {
    this.getDate();
    setInterval(() => {
      this.calculateCountdown();
    }, 1000);
  }

  calculateCountdown(): void {
    const now = new Date().getTime();
    const distance = this.targetDate.getTime() - now;

    this.days = Math.floor(distance / (1000 * 60 * 60 * 24));
    this.hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    this.minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    this.seconds = Math.floor((distance % (1000 * 60)) / 1000);
  }

  getDate(): void {
    this.homepageservice.getStartDate()
      .subscribe({
        next: (data:string) => {
          this.targetDate = new Date(data);
        },
        error: (e) => console.error(e)
      });
  }

  onDateChange(UpdatedValue: string):void
	{
		this.date = UpdatedValue;
	}

  onMailChange(UpdatedValue: string):void
	{
		this.email = UpdatedValue;
	}

  SetDate(date: string): void{
    this.homepageservice.setStartDate(date)
      .subscribe({
        next: (data:string) => {
          this.targetDate = new Date(date);
        },
        error: (e) => console.error(e)
      });
  }

  AddSpocs(email: string): void{
    this.homepageservice.addSpoc(email)
      .subscribe({
        next: (data:string) => {
        },
        error: (e) => console.error(e)
      });
  }

}
