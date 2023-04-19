import { Component, OnInit   } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DataexchangeService } from 'src/app/services/dataexchange.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string;
  password: string;
  user_id: number;

  constructor(private router: Router, private dataservice: DataexchangeService) {
    this.email = '';
    this.password = '';
    this.user_id = -1;
  }

  ngOnInit(): void {
    
  }

  emailForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email])
  });

  onSubmit(): void{
    console.log(this.email);
    console.log(this.password);

    this.dataservice.changeUserId(2);
    this.router.navigate(['sports']);

  }

}
