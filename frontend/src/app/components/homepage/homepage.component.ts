import { Component, OnInit } from '@angular/core';
import { HomepageService } from 'src/app/services/homepage.service';
import { NGXLogger } from 'ngx-logger';
import { ClientLoggerService } from 'src/app/services/client-logger.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  targetDate: Date = new Date('2023-08-30');
  // date: Date = new Date('2023-04-30');
  
  
  days: number = 0;
  hours: number = 0;
  minutes: number = 0;
  seconds: number = 0;

  constructor(private homepageservice: HomepageService, private logger: NGXLogger, private clientLoggerService: ClientLoggerService) { this.getDate(); }

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
        next: (data:any) => {
          this.logger.info("Recieved the start date");
          this.clientLoggerService.log("Recieved the start date");
          this.targetDate = new Date(data.date);
        },
        error: (e) => {
          this.logger.error("Error inreceiving the start date");
          this.clientLoggerService.log("Error inreceiving the start date");
          console.error(e);}
      });
  }

}
