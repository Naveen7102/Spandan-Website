import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  targetDate: Date = new Date('2023-04-30T00:00:00');
  days: number = 0;
  hours: number = 0;
  minutes: number = 0;
  seconds: number = 0;

  constructor() { }

  ngOnInit(): void {
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
}
