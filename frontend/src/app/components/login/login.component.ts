import { Component, OnInit   } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { DataexchangeService } from 'src/app/services/dataexchange.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string;
  password: string;
  user_id: number;

  constructor(private router: Router, private dataservice: DataexchangeService, private loginService: LoginService) {
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
    const data:User = {};

    this.dataservice.changeUserId(2);

    this.loginService.login(data)
      .subscribe({
        next: (data:User) => {
          // User data line
        },
        error: (e) => console.error(e)
      });

    this.router.navigate(['sports']);

  }

}
