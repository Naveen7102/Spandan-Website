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
  user_details: User;

  constructor(private router: Router, private dataservice: DataexchangeService, private loginService: LoginService) {
    this.email = '';
    this.password = '';
    this.user_details = {};
  }

  ngOnInit(): void {
    
  }

  emailForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email])
  });

  onSubmit(): void{

    const data = {
      email: this.email,
      password: this.password
    };
    console.log(data);
    

    this.loginService.login(data)
      .subscribe({
        next: (data:User) => {
          this.user_details = data;
          console.log(this.user_details);
          
          localStorage.setItem('token', this.user_details.token);
          
          this.dataservice.changeUserId(this.user_details);
          console.log(this.user_details);
          this.router.navigate(['sports']);
        },
        error: (e) => {
          alert("invalid login");
          console.error(e);
        }
      });

  }

}
